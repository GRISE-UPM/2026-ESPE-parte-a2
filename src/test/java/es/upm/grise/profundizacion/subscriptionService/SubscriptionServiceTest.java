package es.upm.grise.profundizacion.subscriptionService;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class SubscriptionServiceTest {
	
	@Test
	void userNull_addSubscriber_Excepcion() {
		SubscriptionService service = new SubscriptionService();
		
		assertThrows(IllegalArgumentException.class,
				() -> service.addSubscriber(null));
	}

	@Test
	void deliveryLocalAndEmailNotNull_addSubscriber_Excepcion() {
		SubscriptionService service = new SubscriptionService();
		User user = new User("user@example.com", Delivery.LOCAL);
		assertThrows(IllegalArgumentException.class,
				() -> service.addSubscriber(user));
		}

	@Test
	void userExisting_addSubscriber_Excepcion() {
		SubscriptionService service = new SubscriptionService();
		User user = new User(null, Delivery.DO_NOT_DELIVER);
		service.addSubscriber(user);
		
		assertThrows(IllegalArgumentException.class,
				() -> service.addSubscriber(user));
	}

	
}
