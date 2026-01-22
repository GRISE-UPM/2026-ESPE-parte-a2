package es.upm.grise.profundizacion.subscriptionService;

import java.util.Collection;

public class SubscriptionService {

	private Collection <User> subscribers;
	
	/*
	 * Constructor
	 */
	public SubscriptionService() {}
	
	

	/*
	 * Method to code / test
	 */
	public void addSubscriber(User user) {

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
	
	
	
	
	/*
	 * Other setters & getters
	 */
	public Collection <User> getSubscribers() {
		
		return subscribers;
		
	}

}
