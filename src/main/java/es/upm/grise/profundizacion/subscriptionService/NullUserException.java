package es.upm.grise.profundizacion.subscriptionService;
//null exception
public class NullUserException extends RuntimeException {
	
	public NullUserException() {
		super();
	}
	
	public NullUserException(String message) {
		super(message);
	}
	
}
