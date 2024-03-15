package eu.verdelhan.ecoledirecte;

import eu.verdelhan.ecoledirecte.v3.classes.Eleves;
import eu.verdelhan.ecoledirecte.v3.conseildeclasse.ConseilDeClasse;
import eu.verdelhan.ecoledirecte.v3.eleves.Eleve;
import eu.verdelhan.ecoledirecte.v3.eleves.notes.Notes;
import eu.verdelhan.ecoledirecte.v3.eleves.viescolaire.VieScolaire;
import okhttp3.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.Base64;

public class EcoleDirecteClientTest {

    @Test
    public void all() throws Exception {

        EcoleDirecteApiConfig config = new EcoleDirecteApiConfig("https://api.ecoledirecte.com/v3");

        Authenticator proxyAuthenticator = new Authenticator() {
            @Override public Request authenticate(Route route, Response response) throws IOException {
                String credential = Credentials.basic("xxxxxxxx", "pppppppppppppp");
                return response.request().newBuilder()
                        .header("Proxy-Authorization", credential)
                        .build();
            }
        };

        OkHttpClient httpClient = new OkHttpClient.Builder()
                .proxy(new Proxy(Proxy.Type.HTTP, new InetSocketAddress("uuuuuuuuuuuuuuuuuu", 8080)))
                .proxyAuthenticator(proxyAuthenticator)
                .build();

        EcoleDirecteClient client = new EcoleDirecteClient(config, httpClient);
        System.out.println(client.authenticate("foo", "bar").getToken());

        Eleve e = client.getEleve("1100");
        System.out.println(e.getDateDeNaissance());

        Notes n = client.getEleveNotes("376");
        System.out.println(n.getNotes().size());
        System.out.println(n.getLSUN().get("A001").get(0).getLibelleMatiere());

        VieScolaire vs = client.getEleveVieScolaire("376");
        System.out.println(vs.getAbsencesRetards().size());

        Eleves elvs = client.getClasseEleves("1");
        System.out.println(elvs.getEntity().getLibelle());

        ConseilDeClasse cc = client.getConseilDeClasse("66", "19", "A001");
        cc.getEleves().forEach(el -> {
            System.out.println(
                    new String(Base64.getMimeDecoder().decode(el.getAppreciationPP().getText()))
            );
        });
    }
}
