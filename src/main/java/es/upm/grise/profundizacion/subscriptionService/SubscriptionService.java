package es.upm.grise.profundizacion.subscriptionService;

import java.util.Collection;
import java.util.HashSet;

public class SubscriptionService {

	private Collection<User> subscribers;
	
	/*
	 * Constructor
	 */
	public SubscriptionService() {
		subscribers = new HashSet<>();
	}
	
	/*
	 * Method to code / test
	 */
	public void addSubscriber(User user) {
		
		// Regla 1: user null
		if (user == null) {
			throw new NullUserException();
		}
		
		// Regla 2: usuario ya suscrito
		if (subscribers.contains(user)) {
			throw new ExistingUserException();
		}
		
		// Regla 3: LOCAL → email debe ser null
		if (user.getDelivery() == Delivery.LOCAL && user.getEmail() != null) {
			throw new LocalUserDoesNotHaveNullEMailException();
		}
		
		// Añadir usuario
		subscribers.add(user);
	}
	
	/*
	 * Other setters & getters
	 */
	public Collection<User> getSubscribers() {
		return subscribers;
	}
}
