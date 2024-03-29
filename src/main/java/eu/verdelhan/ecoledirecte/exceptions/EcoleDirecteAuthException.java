package eu.verdelhan.ecoledirecte.exceptions;

/**
 * Exception d'authentification (login) EcoleDirecte.
 */
public class EcoleDirecteAuthException extends EcoleDirecteException {

    public EcoleDirecteAuthException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
