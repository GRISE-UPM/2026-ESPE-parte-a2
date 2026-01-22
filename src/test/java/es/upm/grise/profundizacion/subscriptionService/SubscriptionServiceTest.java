package es.upm.grise.profundizacion.subscriptionService;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class SubscriptionServiceTest {
	
	@Test
	public void smokeTest() {
		SubscriptionService service = new SubscriptionService();
		assertNotNull(service);
	}
	
	@Test
	public void addValidLocalUser() {
		SubscriptionService service = new SubscriptionService();
		User user = new User();
		user.setDelivery(Delivery.LOCAL);
		user.setEmail(null);
		
		assertDoesNotThrow(() -> service.addSubscriber(user));
	}
	
	@Test
	public void addNullUserThrowsException() {
		SubscriptionService service = new SubscriptionService();
		
		assertThrows(NullUserException.class, () -> {
			service.addSubscriber(null);
		});
	}
	
	@Test
	public void addExistingUserThrowsException() {
		SubscriptionService service = new SubscriptionService();
		User user = new User();
		user.setDelivery(Delivery.DO_NOT_DELIVER);
		user.setEmail("test@mail.com");
		
		service.addSubscriber(user);
		
		assertThrows(ExistingUserException.class, () -> {
			service.addSubscriber(user);
		});
	}
	
	@Test
	public void localUserWithEmailThrowsException() {
		SubscriptionService service = new SubscriptionService();
		User user = new User();
		user.setDelivery(Delivery.LOCAL);
		user.setEmail("test@mail.com");
		
		assertThrows(LocalUserDoesNotHaveNullEMailException.class, () -> {
			service.addSubscriber(user);
		});
	}
}


