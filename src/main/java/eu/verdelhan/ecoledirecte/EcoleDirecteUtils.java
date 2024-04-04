package eu.verdelhan.ecoledirecte;

import java.util.Base64;

/**
 * Classe utilitaire pour l'utilisation du client EcoleDirecte.
 */
public class EcoleDirecteUtils {

    /**
     * @param b64EncodedString une chaine de caractere encodee en base 64
     * @return la chaine de caracteres decodee
     */
    public static final String decodeBase64Text(String b64EncodedString) {
        if (b64EncodedString == null) {
            return null;
        }
        return new String(Base64.getMimeDecoder().decode(b64EncodedString));
    }

    private EcoleDirecteUtils() {}
}
