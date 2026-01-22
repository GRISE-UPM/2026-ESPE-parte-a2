package es.upm.grise.profundizacion.subscriptionService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SubscriptionServiceTest {
	
	private SubscriptionService subscriptionService;
	
	@BeforeEach
	public void setUp() {
		subscriptionService = new SubscriptionService();
	}
	
	// Test: Al inicializar, no hay ningún usuario suscrito
	@Test
	public void testInitialSubscribersIsEmpty() {
		assertTrue(subscriptionService.getSubscribers().isEmpty());
	}
	
	// Test: Si user es null, se lanza NullUserException
	@Test
	public void testAddNullUserThrowsNullUserException() {
		assertThrows(NullUserException.class, () -> {
			subscriptionService.addSubscriber(null);
		});
	}
	
	// Test: Si user ya está suscrito, se lanza ExistingUserException
	@Test
	public void testAddExistingUserThrowsExistingUserException() throws Exception {
		User user = new User();
		user.setDelivery(Delivery.DO_NOT_DELIVER);
		user.setEmail("test@example.com");
		
		subscriptionService.addSubscriber(user);
		
		assertThrows(ExistingUserException.class, () -> {
			subscriptionService.addSubscriber(user);
		});
	}
	
	// Test: Si delivery es LOCAL y email no es null, se lanza LocalUserDoesNotHaveNullEMailException
	@Test
	public void testLocalUserWithNonNullEmailThrowsException() {
		User user = new User();
		user.setDelivery(Delivery.LOCAL);
		user.setEmail("test@example.com");
		
		assertThrows(LocalUserDoesNotHaveNullEMailException.class, () -> {
			subscriptionService.addSubscriber(user);
		});
	}
	
	// Test: Usuario LOCAL con email null se añade correctamente
	@Test
	public void testLocalUserWithNullEmailIsAddedSuccessfully() throws Exception {
		User user = new User();
		user.setDelivery(Delivery.LOCAL);
		user.setEmail(null);
		
		subscriptionService.addSubscriber(user);
		
		assertTrue(subscriptionService.getSubscribers().contains(user));
		assertEquals(1, subscriptionService.getSubscribers().size());
	}
	
	// Test: Usuario DO_NOT_DELIVER se añade correctamente
	@Test
	public void testDoNotDeliverUserIsAddedSuccessfully() throws Exception {
		User user = new User();
		user.setDelivery(Delivery.DO_NOT_DELIVER);
		user.setEmail("test@example.com");
		
		subscriptionService.addSubscriber(user);
		
		assertTrue(subscriptionService.getSubscribers().contains(user));
		assertEquals(1, subscriptionService.getSubscribers().size());
	}
	
	// Test: Se pueden añadir múltiples usuarios diferentes
	@Test
	public void testMultipleUsersCanBeAdded() throws Exception {
		User user1 = new User();
		user1.setDelivery(Delivery.DO_NOT_DELIVER);
		user1.setEmail("user1@example.com");
		
		User user2 = new User();
		user2.setDelivery(Delivery.LOCAL);
		user2.setEmail(null);
		
		subscriptionService.addSubscriber(user1);
		subscriptionService.addSubscriber(user2);
		
		assertEquals(2, subscriptionService.getSubscribers().size());
		assertTrue(subscriptionService.getSubscribers().contains(user1));
		assertTrue(subscriptionService.getSubscribers().contains(user2));
	}
}
