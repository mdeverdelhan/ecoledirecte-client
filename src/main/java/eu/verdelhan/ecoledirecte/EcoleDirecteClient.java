package eu.verdelhan.ecoledirecte;

import com.google.gson.Gson;
import eu.verdelhan.ecoledirecte.exceptions.EcoleDirecteAuthException;
import eu.verdelhan.ecoledirecte.exceptions.EcoleDirecteException;
import eu.verdelhan.ecoledirecte.exceptions.EcoleDirecteParseException;
import eu.verdelhan.ecoledirecte.v3.classes.Eleves;
import eu.verdelhan.ecoledirecte.v3.classes.GetElevesResponse;
import eu.verdelhan.ecoledirecte.v3.conseildeclasse.ConseilDeClasse;
import eu.verdelhan.ecoledirecte.v3.conseildeclasse.GetConseilDeClasseResponse;
import eu.verdelhan.ecoledirecte.v3.eleves.Eleve;
import eu.verdelhan.ecoledirecte.v3.eleves.GetEleveResponse;
import eu.verdelhan.ecoledirecte.v3.eleves.notes.GetNotesResponse;
import eu.verdelhan.ecoledirecte.v3.eleves.notes.Notes;
import eu.verdelhan.ecoledirecte.v3.eleves.viescolaire.GetVieScolaireResponse;
import eu.verdelhan.ecoledirecte.v3.eleves.viescolaire.VieScolaire;
import eu.verdelhan.ecoledirecte.v3.login.LoginResponse;
import okhttp3.*;

import java.io.IOException;

public class EcoleDirecteClient {

    private final EcoleDirecteApiConfig config;
    private final OkHttpClient httpClient;
    private final Gson gson;

    private RequestBody authTokenReqBody;

    public EcoleDirecteClient(String baseUrl) {
        this(new EcoleDirecteApiConfig(baseUrl), new OkHttpClient());
    }

    public EcoleDirecteClient(EcoleDirecteApiConfig config, OkHttpClient httpClient) {
        this(config, httpClient, new Gson());
    }

    public EcoleDirecteClient(EcoleDirecteApiConfig config, OkHttpClient httpClient, Gson gson) {
        this.config = config;
        this.httpClient = httpClient;
        this.gson = gson;
    }

    public LoginResponse authenticate(String id, String password) throws EcoleDirecteException, IOException {

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

    public Eleve getEleve(String eleveId) throws EcoleDirecteException {
        checkAuthentication();

        Request eleveReq = new Request.Builder()
                .url(config.getBaseUrl() + "/eleves/" + eleveId + ".awp?verbe=get&")
                .post(authTokenReqBody)
                .build();

        GetEleveResponse eleveResponse = executeRequest(eleveReq, GetEleveResponse.class);
        return eleveResponse.getData();
    }

    public Notes getEleveNotes(String eleveId) throws EcoleDirecteException {
        checkAuthentication();

        Request eleveNotesReq = new Request.Builder()
                .url(config.getBaseUrl() + "/eleves/" + eleveId + "/notes.awp?verbe=get&")
                .post(authTokenReqBody)
                .build();

        GetNotesResponse eleveNotesResponse = executeRequest(eleveNotesReq, GetNotesResponse.class);
        return eleveNotesResponse.getData();
    }

    public VieScolaire getEleveVieScolaire(String eleveId) throws EcoleDirecteException {
        checkAuthentication();

        Request eleveVsReq = new Request.Builder()
                .url(config.getBaseUrl() + "/eleves/" + eleveId + "/viescolaire.awp?verbe=get&")
                .post(authTokenReqBody)
                .build();

        GetVieScolaireResponse eleveVsResponse = executeRequest(eleveVsReq, GetVieScolaireResponse.class);
        return eleveVsResponse.getData();
    }

    public Eleves getClasseEleves(String classeId) throws EcoleDirecteException {
        checkAuthentication();

        Request classeElevesReq = new Request.Builder()
                .url(config.getBaseUrl() + "/classes/" + classeId + "/eleves.awp?verbe=get&")
                .post(authTokenReqBody)
                .build();

        GetElevesResponse classeElevesResponse = executeRequest(classeElevesReq, GetElevesResponse.class);
        return classeElevesResponse.getData();
    }

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

    protected void checkAuthentication() throws EcoleDirecteException {
        if (authTokenReqBody == null) {
            throw new EcoleDirecteException("Unauthenticated");
        }
    }

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
