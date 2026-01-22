package es.upm.grise.profundizacion.subscriptionService;

import java.util.Collection;
import java.util.ArrayList;

public class SubscriptionService {

	private Collection<User> subscribers;
	
	
	public static class NullUserException extends Exception {
		public NullUserException() {
			super("El usuario no puede ser null");
		}
	}
	
	public static class ExistingUserException extends Exception {
		public ExistingUserException() {
			super("El usuario ya está suscrito al servicio");
		}
	}
	
	public static class LocalUserDoesNotHaveNullEMailException extends Exception {
		public LocalUserDoesNotHaveNullEMailException() {
			super("Un usuario con delivery LOCAL debe tener email null");
		}
	}
	
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
		
		if (user == null) {
			throw new NullUserException();
		}
		
		
		if (subscribers.contains(user)) {
			throw new ExistingUserException();
		}
		
		if (user.getDelivery() == Delivery.LOCAL && user.getEmail() != null) {
			throw new LocalUserDoesNotHaveNullEMailException();
		}
		
		subscribers.add(user);
	}
	
	public void sendMessage(String message) {
		for (User user : subscribers) {
			if (user.getDelivery() == Delivery.EMAIL) {
				
			} else if (user.getDelivery() == Delivery.LOCAL) {
				user.addMessage(message);
			}
			
		}
	}
	
	
	/*
	 * Other setters & getters
	 */
	public Collection<User> getSubscribers() {
		return subscribers;
	}

}
