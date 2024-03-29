package eu.verdelhan.ecoledirecte.exceptions;

/**
 * Exception de parsing des reponses HTTP de l'API EcoleDirecte
 */
public class EcoleDirecteParseException extends EcoleDirecteException {

    public EcoleDirecteParseException(String message) {
        super(message);
    }

    public EcoleDirecteParseException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
