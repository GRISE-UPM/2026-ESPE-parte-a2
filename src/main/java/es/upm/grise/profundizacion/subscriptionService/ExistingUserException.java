package es.upm.grise.profundizacion.subscriptionService;

public class ExistingUserException extends Exception {

	private static final long serialVersionUID = 1L;

	public ExistingUserException() {
		super("El usuario ya existe en la lista de suscriptores");
	}
}
