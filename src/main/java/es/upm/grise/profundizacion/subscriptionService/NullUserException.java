package es.upm.grise.profundizacion.subscriptionService;

public class NullUserException extends Exception {
    
    public NullUserException() {
        super("El usuario no puede ser null");
    }
    
    public NullUserException(String mensaje) {
        super(mensaje);
    }
}
