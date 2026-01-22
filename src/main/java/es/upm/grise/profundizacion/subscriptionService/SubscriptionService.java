package es.upm.grise.profundizacion.subscriptionService;

import java.util.ArrayList;
import java.util.Collection;

public class SubscriptionService {

	private Collection<User> subscribers;
	
	/*
	 * Constructor
	 */
	public SubscriptionService() {
		this.subscribers = new ArrayList<>();
	}
	

	/*
	 * Method to code / test
	 */
	public void addSubscriber(User user) throws NullUserException, ExistingUserException, LocalUserDoesNotHaveNullEMailException {
		
		// Validar que el usuario no sea null
		if (user == null) {
			throw new NullUserException();
		}
		
		// Validar que el usuario no esté ya suscrito
		if (subscribers.contains(user)) {
			throw new ExistingUserException();
		}
		
		// Validar que si el delivery es LOCAL, el email debe ser null
		if (user.getDelivery() == Delivery.LOCAL && user.getEmail() != null) {
			throw new LocalUserDoesNotHaveNullEMailException();
		}
		
		// Añadir el usuario a la lista de suscriptores
		subscribers.add(user);
	}
	
	
	

	/*
	 * Other setters & getters
	 */
	public Collection<User> getSubscribers() {
		
		return subscribers;
		
	}

}
