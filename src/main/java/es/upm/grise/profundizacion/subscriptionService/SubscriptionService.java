package es.upm.grise.profundizacion.subscriptionService;

import java.util.Collection;
import java.util.ArrayList;

public class SubscriptionService {

	private Collection <User> subscribers;
	
	/*
	 * Constructor
	 */
	public SubscriptionService() {
		subscribers = new ArrayList<User> ();
	}
	
	/*
	 * Method to code / test
	 */
	public void addSubscriber(User user) {
		subscribers.add(user);
		if (user == null ){
			throw new NullUserException("El usuario no se encuentra en el servicio de mensajeria")
		}
		
		if (user != null){			
			throw new ExistingUserException("El usuario ya se encuentra en el servicio de mensajeria")
		}
		
		if (user.getDelivery().getType() == DeliveryType.LOCAL && user.getEmail() != null){
			throw new LocalUserDoesNotHaveNullEmailException("Para usuarios con delivery local el email debe ser NULL")
		}
	}

	/*
	 * Other setters & getters
	 */
	public Collection <User> getSubscribers() {
		return subscribers;		
	}
	public Collection <User> setSubscribers(){
		return this.subscribers;
	}

}
