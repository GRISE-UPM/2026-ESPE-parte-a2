package es.upm.grise.profundizacion.subscriptionService;

import java.util.ArrayList;
import java.util.Collection;

public class SubscriptionService {

	private Collection <User> subscribers;
	
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
		//excepción NullUserException
		if (user == null) {
			throw new NullUserException("User cannot be null");
		}
		
		//excepción ExistingUserException
		if (subscribers.contains(user)) {
			throw new ExistingUserException("User is already subscribed");
		}
		
		//excepción LocalUserDoesNotHaveNullEMailException
		if (user.getDelivery() == Delivery.LOCAL && user.getEmail() != null) {
			throw new LocalUserDoesNotHaveNullEMailException("Local user must have null email");
		}
		
		subscribers.add(user);
	}
	
	
	
	
	/*
	 * Other setters & getters
	 */
	public Collection <User> getSubscribers() {
		
		return subscribers;
		
	}

}
