package es.upm.grise.profundizacion.subscriptionService;

public class LocalUserDoesNotHaveNullEMailException extends Exception {

	private static final long serialVersionUID = 1L;

	public LocalUserDoesNotHaveNullEMailException() {
		super("El usuario con entrega local debe tener el email nulo");
	}
}
