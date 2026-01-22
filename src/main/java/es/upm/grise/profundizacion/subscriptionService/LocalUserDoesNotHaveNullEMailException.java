package es.upm.grise.profundizacion.subscriptionService;

public class LocalUserDoesNotHaveNullEMailException extends Exception {
    
    public LocalUserDoesNotHaveNullEMailException() {
        super("Un usuario con delivery LOCAL debe tener email null");
    }
    
    public LocalUserDoesNotHaveNullEMailException(String mensaje) {
        super(mensaje);
    }
}
