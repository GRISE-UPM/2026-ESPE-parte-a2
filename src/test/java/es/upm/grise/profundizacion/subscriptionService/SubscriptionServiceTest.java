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
	public void addSubscriber_ok() {
		SubscriptionService service = new SubscriptionService();
		User user = new User();
		user.setDelivery(Delivery.DO_NOT_DELIVER);
		user.setEmail("test@test.com");

		service.addSubscriber(user);

		assertEquals(1, service.getSubscribers().size());
	}

	@Test
	public void addSubscriber_nullUser_exception() {
		SubscriptionService service = new SubscriptionService();
		assertThrows(NullUserException.class, () -> service.addSubscriber(null));
	}

	@Test
	public void addSubscriber_existingUser_exception() {
		SubscriptionService service = new SubscriptionService();
		User user = new User();
		user.setDelivery(Delivery.DO_NOT_DELIVER);

		service.addSubscriber(user);

		assertThrows(ExistingUserException.class, () -> service.addSubscriber(user));
	}

	@Test
	public void addSubscriber_localWithEmail_exception() {
		SubscriptionService service = new SubscriptionService();
		User user = new User();
		user.setDelivery(Delivery.LOCAL);
		user.setEmail("local@test.com");

		assertThrows(LocalUserDoesNotHaveNullEMailException.class,
				() -> service.addSubscriber(user));
	}
}

