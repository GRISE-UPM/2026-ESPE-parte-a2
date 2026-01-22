package es.upm.grise.profundizacion.subscriptionService;

public class ExistingUserException extends Exception {
	
	public ExistingUserException() {
		super("User already exists in the subscription service");
	}
	
	public ExistingUserException(String message) {
		super(message);
	}
}
