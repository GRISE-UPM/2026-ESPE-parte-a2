package es.upm.grise.profundizacion.subscriptionService;

import java.util.ArrayList;
import java.util.Collection;

public class SubscriptionService {

	private Collection <User> subscribers;
	
	public SubscriptionService() {
		this.subscribers = new ArrayList<>();
	}

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
	public Collection <User> getSubscribers() {
		
		return subscribers;
		
	}

}
