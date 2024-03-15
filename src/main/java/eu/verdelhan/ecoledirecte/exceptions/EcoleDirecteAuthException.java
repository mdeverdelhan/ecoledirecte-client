package eu.verdelhan.ecoledirecte.exceptions;

public class EcoleDirecteAuthException extends EcoleDirecteException {

    public EcoleDirecteAuthException(String message) {
        super(message);
    }

    public EcoleDirecteAuthException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
