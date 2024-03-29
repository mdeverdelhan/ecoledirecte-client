package eu.verdelhan.ecoledirecte;

import java.util.Base64;

/**
 * Classe utilitaire pour l'utilisation du client EcoleDirecte.
 */
public class EcoleDirecteUtils {

    /**
     * @param encodedAppr une appreciation encodee en base 64
     * @return l'appreciation decodee
     */
    public static final String decodeAppreciation(String encodedAppr) {
        if (encodedAppr == null) {
            return null;
        }
        return new String(Base64.getMimeDecoder().decode(encodedAppr));
    }

    private EcoleDirecteUtils() {}
}
