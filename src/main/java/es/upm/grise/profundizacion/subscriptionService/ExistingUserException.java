package es.upm.grise.profundizacion.subscriptionService;
// Exception to be thrown when trying to register an existing user
public class ExistingUserException extends Exception {
    public ExistingUserException(String message) {
        super(message);
    }
}
