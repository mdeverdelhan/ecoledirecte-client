package eu.verdelhan.ecoledirecte;

import com.google.gson.Gson;
import eu.verdelhan.ecoledirecte.exceptions.EcoleDirecteAuthException;
import eu.verdelhan.ecoledirecte.exceptions.EcoleDirecteException;
import eu.verdelhan.ecoledirecte.exceptions.EcoleDirecteParseException;
import eu.verdelhan.ecoledirecte.v3.classes.Eleves;
import eu.verdelhan.ecoledirecte.v3.classes.GetElevesResponse;
import eu.verdelhan.ecoledirecte.v3.conseildeclasse.ConseilDeClasse;
import eu.verdelhan.ecoledirecte.v3.conseildeclasse.GetConseilDeClasseResponse;
import eu.verdelhan.ecoledirecte.v3.contactetablissement.ContactEtablissement;
import eu.verdelhan.ecoledirecte.v3.contactetablissement.GetContactEtablissementResponse;
import eu.verdelhan.ecoledirecte.v3.eleves.Eleve;
import eu.verdelhan.ecoledirecte.v3.eleves.GetEleveResponse;
import eu.verdelhan.ecoledirecte.v3.eleves.coordonneesfamille.CoordonneesFamille;
import eu.verdelhan.ecoledirecte.v3.eleves.coordonneesfamille.GetCoordonneesFamilleResponse;
import eu.verdelhan.ecoledirecte.v3.eleves.notes.GetNotesResponse;
import eu.verdelhan.ecoledirecte.v3.eleves.notes.Notes;
import eu.verdelhan.ecoledirecte.v3.eleves.viescolaire.GetVieScolaireResponse;
import eu.verdelhan.ecoledirecte.v3.eleves.viescolaire.VieScolaire;
import eu.verdelhan.ecoledirecte.v3.login.LoginResponse;
import okhttp3.*;

import java.io.IOException;
import java.util.List;

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

    /** Request body comportant le jeton d'authentification */
    private RequestBody authTokenReqBody;

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
     * Authentification du client.
     * @param id identifiant utilisateur EcoleDirecte
     * @param password mot de passe EcoleDirecte
     * @return la reponse du login a l'API EcoleDirecte
     */
    public LoginResponse authenticate(String id, String password) throws EcoleDirecteException {

        String authStr = "{ \"identifiant\": \"" + id + "\" , \"motdepasse\": \"" + password + "\" }";
        RequestBody body = new FormBody.Builder().add("data", authStr).build();
        Request loginReq = new Request.Builder()
                .url(config.getBaseUrl() + "/login.awp")
                .post(body)
                .build();

        try {
            LoginResponse loginResponse = executeRequest(loginReq, LoginResponse.class);
            authTokenReqBody = new FormBody.Builder()
                    .add("data", "{ \"token\": \"" + loginResponse.getToken() + "\" }")
                    .build();
            return loginResponse;
        } catch (EcoleDirecteException ede) {
            throw new EcoleDirecteAuthException("Failed login", ede);
        }

    }

    /**
     * @param eleveId un identifiant d'eleve (ex : 42)
     * @return l'eleve correspondant a eleveId
     */
    public Eleve getEleve(String eleveId) throws EcoleDirecteException {
        checkAuthentication();

        Request eleveReq = new Request.Builder()
                .url(config.getBaseUrl() + "/eleves/" + eleveId + ".awp?verbe=get&")
                .post(authTokenReqBody)
                .build();

        GetEleveResponse eleveResponse = executeRequest(eleveReq, GetEleveResponse.class);
        return eleveResponse.getData();
    }

    /**
     * @param eleveId un identifiant d'eleve (ex : 42)
     * @return les notes de l'eleve correspondant a eleveId
     */
    public Notes getEleveNotes(String eleveId) throws EcoleDirecteException {
        checkAuthentication();

        Request eleveNotesReq = new Request.Builder()
                .url(config.getBaseUrl() + "/eleves/" + eleveId + "/notes.awp?verbe=get&")
                .post(authTokenReqBody)
                .build();

        GetNotesResponse eleveNotesResponse = executeRequest(eleveNotesReq, GetNotesResponse.class);
        return eleveNotesResponse.getData();
    }

    /**
     * @param eleveId un identifiant d'eleve (ex : 42)
     * @return la vie scolaire (absences, etc.) de l'eleve correspondant a eleveId
     */
    public VieScolaire getEleveVieScolaire(String eleveId) throws EcoleDirecteException {
        checkAuthentication();

        Request eleveVsReq = new Request.Builder()
                .url(config.getBaseUrl() + "/eleves/" + eleveId + "/viescolaire.awp?verbe=get&")
                .post(authTokenReqBody)
                .build();

        GetVieScolaireResponse eleveVsResponse = executeRequest(eleveVsReq, GetVieScolaireResponse.class);
        return eleveVsResponse.getData();
    }

    /**
     * @param eleveId un identifiant d'eleve (ex : 42)
     * @return les coordonnees des familles de l'eleve correspondant a eleveId
     */
    public List<CoordonneesFamille> getEleveCoordonneesFamille(String eleveId) throws EcoleDirecteException {
        checkAuthentication();

        Request eleveCfReq = new Request.Builder()
                .url(config.getBaseUrl() + "/eleves/" + eleveId + "/coordonneesfamille.awp?verbe=get&")
                .post(authTokenReqBody)
                .build();

        GetCoordonneesFamilleResponse eleveCfResponse = executeRequest(eleveCfReq, GetCoordonneesFamilleResponse.class);
        return eleveCfResponse.getData();
    }

    /**
     * @param classeId un identifiant de classe (ex : 10)
     * @return les eleves de la classe correspondant a classeId
     */
    public Eleves getClasseEleves(String classeId) throws EcoleDirecteException {
        checkAuthentication();

        Request classeElevesReq = new Request.Builder()
                .url(config.getBaseUrl() + "/classes/" + classeId + "/eleves.awp?verbe=get&")
                .post(authTokenReqBody)
                .build();

        GetElevesResponse classeElevesResponse = executeRequest(classeElevesReq, GetElevesResponse.class);
        return classeElevesResponse.getData();
    }

    /**
     * @param enseignantId un identifiant d'enseignant (ex : 50)
     * @param classeId un identifiant de classe (ex : 10)
     * @param periodeId un identifiant de periode (ex : A001)
     * @return le conseil de la classe classeId, de l'enseignant enseignantId, pour la periode periodeId
     */
    public ConseilDeClasse getConseilDeClasse(String enseignantId, String classeId, String periodeId) throws EcoleDirecteException {
        checkAuthentication();

        Request conseilReq = new Request.Builder()
                .url(config.getBaseUrl() + "/enseignants/" + enseignantId
                        + "/C/" + classeId + "/periodes/" + periodeId + "/conseilDeClasse.awp?verbe=get&")
                .post(authTokenReqBody)
                .build();

        GetConseilDeClasseResponse conseilResponse = executeRequest(conseilReq, GetConseilDeClasseResponse.class);
        return conseilResponse.getData();
    }

    /**
     * @return les coordonnees de l'etablissement
     */
    public List<ContactEtablissement> getContactEtablissement() throws EcoleDirecteException {
        checkAuthentication();

        Request ceReq = new Request.Builder()
                .url(config.getBaseUrl() + "/contactetablissement.awp?verbe=get&")
                .post(authTokenReqBody)
                .build();

        GetContactEtablissementResponse ceResponse = executeRequest(ceReq, GetContactEtablissementResponse.class);
        return ceResponse.getData();
    }

    /**
     * Verifie l'authentification du client.
     * @throws EcoleDirecteException en cas de client non authentifie
     */
    protected void checkAuthentication() throws EcoleDirecteException {
        if (authTokenReqBody == null) {
            throw new EcoleDirecteException("Unauthenticated");
        }
    }

    /**
     * Execute la requete httpRequest.
     * @param httpRequest une requete HTTP
     * @param clazz le type de retour
     * @return le resultat de la requete httpRequest sous forme d'instance de clazz
     */
    protected <T> T executeRequest(Request httpRequest, Class<T> clazz) throws EcoleDirecteException {
        try (Response response = httpClient.newCall(httpRequest).execute()) {
            if (!response.isSuccessful()) {
                throw new EcoleDirecteException("Failed request - Response: " + response.message() + " (CODE="+response.code()+")");
            }
            return parseResponse(response, clazz);
        } catch (IOException ioe) {
            throw new EcoleDirecteException("Failed request", ioe);
        }
    }

    /**
     * "Parse" la reponse httpResponse.
     * @param httpResponse une reponse HTTP
     * @param clazz le type de retour
     * @return la reponse HTTP httpResponse sous forme d'instance de clazz
     * @throws EcoleDirecteParseException en cas d'erreur de parsing
     */
    protected <T> T parseResponse(Response httpResponse, Class<T> clazz) throws EcoleDirecteParseException {
        if (httpResponse == null || httpResponse.body() == null) {
            throw new EcoleDirecteParseException("Cannot parse null response or null-body response");
        }
        try {
            return gson.fromJson(httpResponse.body().string(), clazz);
        } catch (IOException ioe) {
            throw new EcoleDirecteParseException("Error parsing HTTP response", ioe);
        }
    }
}
