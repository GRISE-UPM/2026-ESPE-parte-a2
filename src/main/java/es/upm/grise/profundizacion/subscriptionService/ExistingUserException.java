package es.upm.grise.profundizacion.subscriptionService;

public class ExistingUserException extends Exception {
    
    public ExistingUserException() {
        super("El usuario ya está suscrito");
    }
    
    public ExistingUserException(String mensaje) {
        super(mensaje);
    }
}
