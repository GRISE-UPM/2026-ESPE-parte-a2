package es.upm.grise.profundizacion.subscriptionService;

public class NullUserException extends Exception {

	private static final long serialVersionUID = 1L;

	public NullUserException() {
		super("El usuario no puede ser nulo");
	}
}
