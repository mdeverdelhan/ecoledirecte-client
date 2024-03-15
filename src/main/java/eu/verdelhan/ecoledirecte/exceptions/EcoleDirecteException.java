package eu.verdelhan.ecoledirecte.exceptions;

public class EcoleDirecteException extends Exception {

    public EcoleDirecteException(String message) {
        super(message);
    }

    public EcoleDirecteException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
