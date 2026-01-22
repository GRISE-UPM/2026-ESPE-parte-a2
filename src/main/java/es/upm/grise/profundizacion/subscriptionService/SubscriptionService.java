package es.upm.grise.profundizacion.subscriptionService;

import java.util.Collection;
import java.util.ArrayList;

public class SubscriptionService {

	private Collection <User> subscribers;
	
	/*
	 * Constructor
	 */
	public SubscriptionService() {
		subscribers = new ArrayList<>();
	}
	
	/*
	 * Method to code / test
	 */
	public void addSubscriber(User user) throws NullUserException, ExistingUserException, LocalUserDoesNotHaveNullEMailException {
		// Verificar si el usuario es null
		if (user == null) {
			throw new NullUserException();
		}
		
		// Verificar si el usuario ya está suscrito
		if (subscribers.contains(user)) {
			throw new ExistingUserException();
		}
		
		// Verificar si el usuario tiene delivery LOCAL y email no null
		if (user.getDelivery() == Delivery.LOCAL && user.getEmail() != null) {
			throw new LocalUserDoesNotHaveNullEMailException();
		}
		
		// Añadir el usuario a la lista de suscriptores
		subscribers.add(user);
	}
		
	/*
	 * Other setters & getters
	 */
	public Collection <User> getSubscribers() {
		
		return subscribers;
		
	}
}
