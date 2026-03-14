package eu.verdelhan.ecoledirecte;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import eu.verdelhan.ecoledirecte.exceptions.EcoleDirecteAuthException;
import eu.verdelhan.ecoledirecte.exceptions.EcoleDirecteException;
import eu.verdelhan.ecoledirecte.exceptions.EcoleDirecteParseException;
import eu.verdelhan.ecoledirecte.v3.auth.GtkCookies;
import eu.verdelhan.ecoledirecte.v3.auth.doubleauth.DoubleAuthCnCv;
import eu.verdelhan.ecoledirecte.v3.auth.doubleauth.DoubleAuthQuestion;
import eu.verdelhan.ecoledirecte.v3.auth.login.Login;
import eu.verdelhan.ecoledirecte.v3.boutique.paiementsenligne.GroupeDePaiements;
import eu.verdelhan.ecoledirecte.v3.classes.Eleves;
import eu.verdelhan.ecoledirecte.v3.conseildeclasse.ConseilDeClasse;
import eu.verdelhan.ecoledirecte.v3.contactetablissement.ContactEtablissement;
import eu.verdelhan.ecoledirecte.v3.eleves.Eleve;
import eu.verdelhan.ecoledirecte.v3.eleves.coordonneesfamille.CoordonneesFamille;
import eu.verdelhan.ecoledirecte.v3.eleves.notes.Notes;
import eu.verdelhan.ecoledirecte.v3.eleves.viescolaire.VieScolaire;
import eu.verdelhan.ecoledirecte.v3.familledocuments.Documents;
import lombok.Getter;
import okhttp3.*;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.*;

/**
 * Client EcoleDirecte
 */
public class EcoleDirecteClient {

    /** Configuration */
    private final EcoleDirecteApiConfig config;

    /** Client HTTP */
    private final OkHttpClient httpClient;

    /** Instance GSON */
    private final Gson gson;

    /** Cookies GTK */
    @Getter
    private GtkCookies gtkCookies;

    /** Jeton d'authentification */
    @Getter
    private String authToken;
    /** True si le client est pleinement authentifie, false sinon */
    @Getter
    private boolean fullyAuthenticated = false;

    /** Nom de l'en-tete HTTP pour le jeton d'authentification */
    private static final String AUTH_TOKEN_HEADER_NAME = "X-Token";

    /** Request body vide */
    private static final RequestBody EMPTY_DATA_REQUEST_BODY = new FormBody.Builder().add("data", "{}").build();

    /**
     * @param baseUrl URL de la base de l'API EcoleDirecte (ex : https://api.ecoledirecte.com/v3)
     */
    public EcoleDirecteClient(String baseUrl) {
        this(new EcoleDirecteApiConfig(baseUrl), new OkHttpClient());
    }

    /**
     * @param config configuration du client EcoleDirecte
     * @param httpClient client HTTP
     */
    public EcoleDirecteClient(EcoleDirecteApiConfig config, OkHttpClient httpClient) {
        this(config, httpClient, new Gson());
    }

    /**
     * @param config configuration du client EcoleDirecte
     * @param httpClient client HTTP
     * @param gson instance GSON
     */
    public EcoleDirecteClient(EcoleDirecteApiConfig config, OkHttpClient httpClient, Gson gson) {
        this.config = config;
        this.httpClient = httpClient;
        this.gson = gson;
    }

    /**
     * Initialise le client avec les cookies GTK.
     * @return la paire de cookies GTK
     */
    public GtkCookies initLoginGtkCookies() throws EcoleDirecteException {

        // Execution de la requete GET de login pour recuperation des cookies GTK
        Request loginGtkCookieReq = new Request.Builder()
                .url(config.getBaseUrl() + "/login.awp?gtk=1&v=" + config.getVersionSiteEcoleDirecte())
                .get()
                .build();
        List<String> cookieStrings;
        try (Response response = httpClient.newCall(loginGtkCookieReq).execute()) {
            if (!response.isSuccessful()) {
                throw new EcoleDirecteException("Failed request - Response: " + response.message() + " (CODE="+response.code()+")");
            }
            cookieStrings = response.headers("set-cookie");
        } catch (IOException ioe) {
            throw new EcoleDirecteException("Failed request", ioe);
        }

        // Recuperation premier cookie GTK
        Optional<String> gtkCookieStrOpt = cookieStrings.stream()
                .filter(Objects::nonNull)
                .filter(c -> c.startsWith("GTK="))
                .findFirst()
                .map(c -> c.split(";", 2)[0].substring("GTK=".length()));
        if (gtkCookieStrOpt.isEmpty()) {
            throw new EcoleDirecteException("GTK cookie header not found");
        }
        String gtkCookieStr = gtkCookieStrOpt.get();

        // Recuperation second cookie GTK
        Optional<String> secondCookieStrOpt = cookieStrings.stream()
                .filter(Objects::nonNull)
                .filter(c -> !c.startsWith("GTK="))
                .filter(c -> c.contains(gtkCookieStr))
                .findFirst()
                .map(c -> c.split(";", 2)[0]);
        if (secondCookieStrOpt.isEmpty()) {
            throw new EcoleDirecteException("Second cookie header not found");
        }
        String secondCookieStr = secondCookieStrOpt.get();

        gtkCookies = new GtkCookies(
                gtkCookieStr,
                "GTK=" + gtkCookieStr + "; " + secondCookieStr
        );
        return gtkCookies;
    }

    /**
     * Authentification du client.
     * @param id identifiant utilisateur EcoleDirecte
     * @param password mot de passe EcoleDirecte
     * @return la reponse du login a l'API EcoleDirecte
     */
    public EcoleDirecteApiResponse<Login> authenticate(String id, String password) throws EcoleDirecteException {
        initLoginGtkCookies();
        return authenticate(id, password, null);
    }

    /**
     * Authentification du client.
     * @param id identifiant utilisateur EcoleDirecte
     * @param password mot de passe EcoleDirecte
     * @param cncv la validation de la double authentification
     * @return la reponse du login a l'API EcoleDirecte
     */
    public EcoleDirecteApiResponse<Login> authenticate(String id, String password, DoubleAuthCnCv cncv) throws EcoleDirecteException {
        checkGtkCookies();

        RequestBody body = buildLoginRequestBody(id, password, cncv);
        Request loginReq = new Request.Builder()
                .url(config.getBaseUrl() + "/login.awp")
                .addHeader("X-GTK", gtkCookies.getGtkCookieString())
                .addHeader("Cookie", gtkCookies.getSecondCookieString())
                .post(body)
                .build();

        Type responseType = new TypeToken<EcoleDirecteApiResponse<Login>>(){}.getType();
        EcoleDirecteApiResponse<Login> loginResponse = executeRequest(loginReq, responseType);
        if (loginResponse == null) {
            throw new EcoleDirecteAuthException("Failed login (null response)");
        }

        if (loginResponse.getCode() == 200) {
            // Authentification complete OK
            authToken = loginResponse.getToken();
            fullyAuthenticated = true;
        } else if (loginResponse.getCode() == 250) {
            // Recuperation du jeton mais besoin d'une double authentification
            authToken = loginResponse.getToken();
            fullyAuthenticated = false;
        } else {
            // Authentification echouee
            authToken = null;
            fullyAuthenticated = false;
            throw new EcoleDirecteAuthException("Failed login - Response: " + loginResponse);
        }

        return loginResponse;
    }

    /**
     * @return la reponse complete contenant la question de double authentification
     */
    public EcoleDirecteApiResponse<DoubleAuthQuestion> getDoubleAuthQuestionResponse() throws EcoleDirecteException {
        checkAuthenticationToken();

        Request request = buildAuthenticatedPostRequest("/connexion/doubleauth.awp?verbe=get&");
        Type responseType = new TypeToken<EcoleDirecteApiResponse<DoubleAuthQuestion>>(){}.getType();
        return executeRequest(request, responseType);
    }

    /**
     * @return la question de double authentification
     */
    public DoubleAuthQuestion getDoubleAuthQuestion() throws EcoleDirecteException {
        return getDoubleAuthQuestionResponse().getData();
    }

    /**
     * Poste la reponse a la question de double authentification
     * @param reponse la reponse (en base64) de la question de double authentification
     * @return la reponse complete contenant l'objet de validation (cncv) de la double authentification
     */
    public EcoleDirecteApiResponse<DoubleAuthCnCv> postDoubleAuthReponseResponse(String reponse) throws EcoleDirecteException {
        checkAuthenticationToken();

        RequestBody choiceBody = new FormBody.Builder()
                .add("data", "{ \"choix\": \"" + reponse + "\" }")
                .build();
        Request request = buildAuthenticatedPostRequest(
                "/connexion/doubleauth.awp?verbe=post&",
                choiceBody
        );

        Type responseType = new TypeToken<EcoleDirecteApiResponse<DoubleAuthCnCv>>(){}.getType();
        return executeRequest(request, responseType);
    }

    /**
     * Poste la reponse a la question de double authentification
     * @param reponse la reponse (en base64) de la question de double authentification
     * @return l'objet de validation (cncv) de la double authentification
     */
    public DoubleAuthCnCv postDoubleAuthReponse(String reponse) throws EcoleDirecteException {
        return postDoubleAuthReponseResponse(reponse).getData();
    }

    /**
     * @param eleveId un identifiant d'eleve (ex : 42)
     * @return la reponse complete contenant l'eleve correspondant a eleveId
     */
    public EcoleDirecteApiResponse<Eleve> getEleveResponse(String eleveId) throws EcoleDirecteException {
        checkFullyAuthenticated();

        Request request = buildAuthenticatedPostRequest("/eleves/" + eleveId + ".awp?verbe=get&");
        Type responseType = new TypeToken<EcoleDirecteApiResponse<Eleve>>(){}.getType();
        return executeRequest(request, responseType);
    }

    /**
     * @param eleveId un identifiant d'eleve (ex : 42)
     * @return l'eleve correspondant a eleveId
     */
    public Eleve getEleve(String eleveId) throws EcoleDirecteException {
        return getEleveResponse(eleveId).getData();
    }

    /**
     * @param eleveId un identifiant d'eleve (ex : 42)
     * @return la reponse complete contenant les notes de l'eleve correspondant a eleveId
     */
    public EcoleDirecteApiResponse<Notes> getEleveNotesResponse(String eleveId) throws EcoleDirecteException {
        checkFullyAuthenticated();

        Request request = buildAuthenticatedPostRequest("/eleves/" + eleveId + "/notes.awp?verbe=get&");
        Type responseType = new TypeToken<EcoleDirecteApiResponse<Notes>>(){}.getType();
        return executeRequest(request, responseType);
    }

    /**
     * @param eleveId un identifiant d'eleve (ex : 42)
     * @return les notes de l'eleve correspondant a eleveId
     */
    public Notes getEleveNotes(String eleveId) throws EcoleDirecteException {
        return getEleveNotesResponse(eleveId).getData();
    }

    /**
     * @param eleveId un identifiant d'eleve (ex : 42)
     * @return la reponse complete contenant la vie scolaire (absences, etc.) de l'eleve correspondant a eleveId
     */
    public EcoleDirecteApiResponse<VieScolaire> getEleveVieScolaireResponse(String eleveId) throws EcoleDirecteException {
        checkFullyAuthenticated();

        Request request = buildAuthenticatedPostRequest("/eleves/" + eleveId + "/viescolaire.awp?verbe=get&");
        Type responseType = new TypeToken<EcoleDirecteApiResponse<VieScolaire>>(){}.getType();
        return executeRequest(request, responseType);
    }

    /**
     * @param eleveId un identifiant d'eleve (ex : 42)
     * @return la vie scolaire (absences, etc.) de l'eleve correspondant a eleveId
     */
    public VieScolaire getEleveVieScolaire(String eleveId) throws EcoleDirecteException {
        return getEleveVieScolaireResponse(eleveId).getData();
    }

    /**
     * @param eleveId un identifiant d'eleve (ex : 42)
     * @return la reponse complete contenant les coordonnees des familles de l'eleve correspondant a eleveId
     */
    public EcoleDirecteApiResponse<List<CoordonneesFamille>> getEleveCoordonneesFamilleResponse(String eleveId) throws EcoleDirecteException {
        checkFullyAuthenticated();

        Request request = buildAuthenticatedPostRequest("/eleves/" + eleveId + "/coordonneesfamille.awp?verbe=get&");
        Type responseType = new TypeToken<EcoleDirecteApiResponse<List<CoordonneesFamille>>>(){}.getType();
        return executeRequest(request, responseType);
    }

    /**
     * @param eleveId un identifiant d'eleve (ex : 42)
     * @return les coordonnees des familles de l'eleve correspondant a eleveId
     */
    public List<CoordonneesFamille> getEleveCoordonneesFamille(String eleveId) throws EcoleDirecteException {
        return getEleveCoordonneesFamilleResponse(eleveId).getData();
    }

    /**
     * @param classeId un identifiant de classe (ex : 10)
     * @return la reponse complete contenant les eleves de la classe correspondant a classeId
     */
    public EcoleDirecteApiResponse<Eleves> getClasseElevesResponse(String classeId) throws EcoleDirecteException {
        checkFullyAuthenticated();

        Request request = buildAuthenticatedPostRequest("/classes/" + classeId + "/eleves.awp?verbe=get&");
        Type responseType = new TypeToken<EcoleDirecteApiResponse<Eleves>>(){}.getType();
        return executeRequest(request, responseType);
    }

    /**
     * @param classeId un identifiant de classe (ex : 10)
     * @return les eleves de la classe correspondant a classeId
     */
    public Eleves getClasseEleves(String classeId) throws EcoleDirecteException {
        return getClasseElevesResponse(classeId).getData();
    }

    /**
     * @param enseignantId un identifiant d'enseignant (ex : 50)
     * @param classeId un identifiant de classe (ex : 10)
     * @param periodeId un identifiant de periode (ex : A001)
     * @return la reponse complete contenant le conseil de la classe classeId, de l'enseignant enseignantId, pour la periode periodeId
     */
    public EcoleDirecteApiResponse<ConseilDeClasse> getConseilDeClasseResponse(String enseignantId, String classeId, String periodeId) throws EcoleDirecteException {
        checkFullyAuthenticated();

        String endpoint = "/enseignants/" + enseignantId
                + "/C/" + classeId + "/periodes/" + periodeId + "/conseilDeClasse.awp?verbe=get&";
        Request request = buildAuthenticatedPostRequest(endpoint);
        Type responseType = new TypeToken<EcoleDirecteApiResponse<ConseilDeClasse>>(){}.getType();
        return executeRequest(request, responseType);
    }

    /**
     * @param enseignantId un identifiant d'enseignant (ex : 50)
     * @param classeId un identifiant de classe (ex : 10)
     * @param periodeId un identifiant de periode (ex : A001)
     * @return le conseil de la classe classeId, de l'enseignant enseignantId, pour la periode periodeId
     */
    public ConseilDeClasse getConseilDeClasse(String enseignantId, String classeId, String periodeId) throws EcoleDirecteException {
        return getConseilDeClasseResponse(enseignantId, classeId, periodeId).getData();
    }

    /**
     * @return la reponse complete contenant les coordonnees de l'etablissement
     */
    public EcoleDirecteApiResponse<List<ContactEtablissement>> getContactEtablissementResponse() throws EcoleDirecteException {
        checkFullyAuthenticated();

        Request request = buildAuthenticatedPostRequest("/contactetablissement.awp?verbe=get&");
        Type responseType = new TypeToken<EcoleDirecteApiResponse<List<ContactEtablissement>>>(){}.getType();
        return executeRequest(request, responseType);
    }

    /**
     * @return les coordonnees de l'etablissement
     */
    public List<ContactEtablissement> getContactEtablissement() throws EcoleDirecteException {
        return getContactEtablissementResponse().getData();
    }

    /**
     * @return la reponse complete contenant les documents pour la famille
     */
    public EcoleDirecteApiResponse<Documents> getFamilleDocumentsResponse() throws EcoleDirecteException {
        checkFullyAuthenticated();

        Request request = buildAuthenticatedPostRequest("/familledocuments.awp?verbe=get&");
        Type responseType = new TypeToken<EcoleDirecteApiResponse<Documents>>(){}.getType();
        return executeRequest(request, responseType);
    }

    /**
     * @return les documents pour la famille
     */
    public Documents getFamilleDocuments() throws EcoleDirecteException {
        return getFamilleDocumentsResponse().getData();
    }

    /**
     * @return la reponse complete contenant les paiements en ligne pour la famille
     */
    public EcoleDirecteApiResponse<List<GroupeDePaiements>> getPaiementsEnLigneResponse() throws EcoleDirecteException {
        checkFullyAuthenticated();

        Request request = buildAuthenticatedPostRequest("/boutique/paiementsenligne.awp?verbe=get&");
        Type responseType = new TypeToken<EcoleDirecteApiResponse<List<GroupeDePaiements>>>(){}.getType();
        return executeRequest(request, responseType);
    }

    /**
     * @return les paiements en ligne pour la famille
     */
    public List<GroupeDePaiements> getPaiementsEnLigne() throws EcoleDirecteException {
        return getPaiementsEnLigneResponse().getData();
    }

    /**
     * @param id l'identifiant de connexion
     * @param password le mot de passe de connexion
     * @param cncv la validation de la double authentification
     * @return le corps de la requete de login
     */
    protected RequestBody buildLoginRequestBody(String id, String password, DoubleAuthCnCv cncv) {

        Map<String, Object> rootMap = new HashMap<>();
        rootMap.put("identifiant", id);
        rootMap.put("motdepasse", password);
        // rootMap.put("isReLogin", false);
        // rootMap.put("uuid", "");

        if (cncv != null) {
            // cn & cn (double authentification)
            Map<String, String> cncvMap = new HashMap<>();
            if (cncv.getCn() != null && !cncv.getCn().isEmpty()) {
                cncvMap.put("cn", cncv.getCn());
            }
            if (cncv.getCv() != null && !cncv.getCv().isEmpty()) {
                cncvMap.put("cv", cncv.getCv());
            }
            if (!cncvMap.isEmpty()) {
                rootMap.put("fa", Set.of(cncvMap));
            }
        }

        String authString = gson.toJson(rootMap);
        return new FormBody.Builder().add("data", authString).build();
    }

    /**
     * Vérifie la présence des cookies GTK
     * @throws EcoleDirecteException en cas d'absence des cookies GTK
     */
    protected void checkGtkCookies() throws EcoleDirecteException {
        if (Objects.isNull(gtkCookies)) {
            throw new EcoleDirecteException("GTK cookies are missing");
        }
    }

    /**
     * Verifie la presence d'un jeton d'authentification
     * @throws EcoleDirecteException en cas d'absence du jeton d'authentification
     */
    protected void checkAuthenticationToken() throws EcoleDirecteException {
        if (Objects.isNull(authToken) || authToken.isBlank()) {
            throw new EcoleDirecteException("Unauthenticated");
        }
    }

    /**
     * Verifie que l'authentification complete du client a bien eu lieu.
     * @throws EcoleDirecteException en cas d'absence d'authentification
     */
    protected void checkFullyAuthenticated() throws EcoleDirecteException {
        checkAuthenticationToken();
        if (!fullyAuthenticated) {
            throw new EcoleDirecteException("Not fully authenticated");
        }
    }

    /**
     * Construit une requete HTTP POST a partir du endpoint fourni (body vide au sens d'EcoleDirecte).
     * @param endpoint le endpoint de l'API EcoleDirecte
     * @return une requete HTTP POST
     */
    protected Request buildAuthenticatedPostRequest(String endpoint) {
        return buildAuthenticatedPostRequest(endpoint, EMPTY_DATA_REQUEST_BODY);
    }

    /**
     * Construit une requete HTTP POST a partir du endpoint et du body fournis.
     * @param endpoint le endpoint de l'API EcoleDirecte
     * @param body le corps de la requete POST
     * @return une requete HTTP POST
     */
    protected Request buildAuthenticatedPostRequest(String endpoint, RequestBody body) {
        return new Request.Builder()
                .url(config.getBaseUrl() + endpoint)
                .header(AUTH_TOKEN_HEADER_NAME, authToken)
                .post(body)
                .build();
    }

    /**
     * Execute la requete httpRequest.
     * @param httpRequest une requete HTTP
     * @param responseType le type de retour de la reponse
     * @return le resultat de la requete httpRequest sous forme d'instance de responseType
     */
    protected <T> T executeRequest(Request httpRequest, Type responseType) throws EcoleDirecteException {
        try (Response response = httpClient.newCall(httpRequest).execute()) {
            if (!response.isSuccessful()) {
                throw new EcoleDirecteException("Failed request - Response: " + response.message() + " (CODE="+response.code()+")");
            }
            return parseResponse(response, responseType);
        } catch (IOException ioe) {
            throw new EcoleDirecteException("Failed request", ioe);
        }
    }

    /**
     * "Parse" la reponse httpResponse.
     * @param httpResponse une reponse HTTP
     * @param responseType le type de retour de la reponse
     * @return la reponse HTTP httpResponse sous forme d'instance de responseType
     * @throws EcoleDirecteParseException en cas d'erreur de parsing
     */
    protected <T> T parseResponse(Response httpResponse, Type responseType) throws EcoleDirecteParseException {
        if (httpResponse == null || httpResponse.body() == null) {
            throw new EcoleDirecteParseException("Cannot parse null response or null-body response");
        }
        try {
            String responseBody = httpResponse.body().string();
            JsonObject responseAsJsonObject = gson.fromJson(responseBody, JsonObject.class);
            T parsedResponse = gson.fromJson(responseAsJsonObject, responseType);

            if (parsedResponse instanceof EcoleDirecteApiResponse<?> apiResp) {
                apiResp.setRawData(responseAsJsonObject.get("data"));
            }

            return parsedResponse;
        } catch (IOException ioe) {
            throw new EcoleDirecteParseException("Error parsing HTTP response", ioe);
        }
    }
}
