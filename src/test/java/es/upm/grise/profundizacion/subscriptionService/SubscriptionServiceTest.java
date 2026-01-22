package es.upm.grise.profundizacion.subscriptionService;

import org.junit.jupiter.api.Test;

public class SubscriptionServiceTest {
	private SubscriptionService service = new SubscriptionService();
	private User mockUser = new User ("Juan", "juan@example.com");
	
	
	@Test
	public void smokeTest() {			
		SubscriptionService service = new SubscriptionService();
		assertNotNull(service);
	}
			
	@Test
	public void testAddSubscriberWithNullUser() {
		assertThrows(NullUserException.class, () -> {
		service.addSubscriber(null);
		});
	}
			
	@Test
	public void testAddSubscriberAlreadyExists() {
		service.addSubscriber(mockUser);
		assertThrows(ExistingUserException.class, () -> {
		service.addSubscriber(mockUser);
		});
	}	

	}
	
