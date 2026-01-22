package es.upm.grise.profundizacion.subscriptionService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

public class SubscriptionServiceTest {

	private SubscriptionService service;

	@BeforeEach
	public void setUp() {
		service = new SubscriptionService();
	}

	@Test
	public void smokeTest() {
		System.out.println("\n=== SMOKE TEST ===");
		System.out.println("Prueba: Verificar que el servicio se crea correctamente");
		
		SubscriptionService service = new SubscriptionService();
		assertNotNull(service.getSubscribers());
		
		System.out.println("✓ Resultado: El servicio se creó correctamente y la lista de suscriptores no es null");
	}

	// Test 1: Añadir un usuario correctamente con email y delivery DO_NOT_DELIVER
	@Test
	public void testAddSubscriberSuccessfully()
			throws NullUserException, ExistingUserException, LocalUserDoesNotHaveNullEMailException {
		System.out.println("\n=== TEST 1: Añadir usuario correctamente ===");
		
		User user = new User();
		user.setEmail("test@example.com");
		user.setDelivery(Delivery.DO_NOT_DELIVER);
		
		System.out.println("Datos ingresados:");
		System.out.println("  - Email: " + user.getEmail());
		System.out.println("  - Delivery: " + user.getDelivery());

		service.addSubscriber(user);

		boolean contains = service.getSubscribers().contains(user);
		int size = service.getSubscribers().size();
		
		System.out.println("Resultado esperado: Usuario añadido, tamaño = 1");
		System.out.println("Resultado obtenido: Usuario en lista = " + contains + ", tamaño = " + size);
		
		assertTrue(contains);
		assertEquals(1, size);
		
		System.out.println("✓ Prueba exitosa");
	}

	// Test 2: Añadir un usuario con delivery LOCAL y email null (válido)
	@Test
	public void testAddSubscriberWithLocalDeliveryAndNullEmail()
			throws NullUserException, ExistingUserException, LocalUserDoesNotHaveNullEMailException {
		System.out.println("\n=== TEST 2: Añadir usuario LOCAL con email null ===");
		
		User user = new User();
		user.setEmail(null);
		user.setDelivery(Delivery.LOCAL);
		
		System.out.println("Datos ingresados:");
		System.out.println("  - Email: " + user.getEmail());
		System.out.println("  - Delivery: " + user.getDelivery());

		service.addSubscriber(user);

		boolean contains = service.getSubscribers().contains(user);
		int size = service.getSubscribers().size();
		
		System.out.println("Resultado esperado: Usuario añadido, tamaño = 1");
		System.out.println("Resultado obtenido: Usuario en lista = " + contains + ", tamaño = " + size);
		
		assertTrue(contains);
		assertEquals(1, size);
		
		System.out.println("✓ Prueba exitosa");
	}

	// Test 3: Lanzar NullUserException cuando el usuario es null
	@Test
	public void testAddSubscriberThrowsNullUserException() {
		System.out.println("\n=== TEST 3: Excepción NullUserException ===");
		System.out.println("Datos ingresados: user = null");
		System.out.println("Excepción esperada: NullUserException");
		
		try {
			assertThrows(NullUserException.class, () -> {
				service.addSubscriber(null);
			});
			System.out.println("✓ Prueba exitosa: Se lanzó NullUserException correctamente");
		} catch (AssertionError e) {
			System.out.println("✗ Prueba fallida: No se lanzó la excepción esperada");
			throw e;
		}
	}

	// Test 4: Lanzar ExistingUserException cuando el usuario ya está suscrito
	@Test
	public void testAddSubscriberThrowsExistingUserException()
			throws NullUserException, ExistingUserException, LocalUserDoesNotHaveNullEMailException {
		System.out.println("\n=== TEST 4: Excepción ExistingUserException ===");
		
		User user = new User();
		user.setEmail("test@example.com");
		user.setDelivery(Delivery.DO_NOT_DELIVER);
		
		System.out.println("Datos ingresados:");
		System.out.println("  - Email: " + user.getEmail());
		System.out.println("  - Delivery: " + user.getDelivery());
		System.out.println("  - Acción: Añadir el mismo usuario dos veces");

		service.addSubscriber(user);
		System.out.println("Primera adición exitosa");
		System.out.println("Excepción esperada al segundo intento: ExistingUserException");

		try {
			assertThrows(ExistingUserException.class, () -> {
				service.addSubscriber(user);
			});
			System.out.println("✓ Prueba exitosa: Se lanzó ExistingUserException correctamente");
		} catch (AssertionError e) {
			System.out.println("✗ Prueba fallida: No se lanzó la excepción esperada");
			throw e;
		}
	}

	// Test 5: Lanzar LocalUserDoesNotHaveNullEMailException cuando delivery es
	// LOCAL y email no es null
	@Test
	public void testAddSubscriberThrowsLocalUserDoesNotHaveNullEMailException() {
		System.out.println("\n=== TEST 5: Excepción LocalUserDoesNotHaveNullEMailException ===");
		
		User user = new User();
		user.setEmail("test@example.com");
		user.setDelivery(Delivery.LOCAL);
		
		System.out.println("Datos ingresados:");
		System.out.println("  - Email: " + user.getEmail());
		System.out.println("  - Delivery: " + user.getDelivery());
		System.out.println("Excepción esperada: LocalUserDoesNotHaveNullEMailException");
		System.out.println("Razón: Usuario LOCAL debe tener email null");

		try {
			assertThrows(LocalUserDoesNotHaveNullEMailException.class, () -> {
				service.addSubscriber(user);
			});
			System.out.println("✓ Prueba exitosa: Se lanzó LocalUserDoesNotHaveNullEMailException correctamente");
		} catch (AssertionError e) {
			System.out.println("✗ Prueba fallida: No se lanzó la excepción esperada");
			throw e;
		}
	}

	// Test 6: Verificar que la lista de suscriptores está vacía inicialmente
	@Test
	public void testInitialSubscribersListIsEmpty() {
		System.out.println("\n=== TEST 6: Lista de suscriptores vacía inicialmente ===");
		System.out.println("Prueba: Verificar que al crear el servicio, no hay suscriptores");
		
		boolean isEmpty = service.getSubscribers().isEmpty();
		
		System.out.println("Resultado esperado: Lista vacía = true");
		System.out.println("Resultado obtenido: Lista vacía = " + isEmpty);
		
		assertTrue(isEmpty);
		
		System.out.println("✓ Prueba exitosa");
	}

	// Test 7: Añadir múltiples usuarios correctamente
	@Test
	public void testAddMultipleSubscribers()
			throws NullUserException, ExistingUserException, LocalUserDoesNotHaveNullEMailException {
		System.out.println("\n=== TEST 7: Añadir múltiples usuarios ===");
		
		User user1 = new User();
		user1.setEmail("user1@example.com");
		user1.setDelivery(Delivery.DO_NOT_DELIVER);

		User user2 = new User();
		user2.setEmail(null);
		user2.setDelivery(Delivery.LOCAL);
		
		System.out.println("Datos ingresados:");
		System.out.println("  Usuario 1:");
		System.out.println("    - Email: " + user1.getEmail());
		System.out.println("    - Delivery: " + user1.getDelivery());
		System.out.println("  Usuario 2:");
		System.out.println("    - Email: " + user2.getEmail());
		System.out.println("    - Delivery: " + user2.getDelivery());

		service.addSubscriber(user1);
		service.addSubscriber(user2);

		int size = service.getSubscribers().size();
		boolean containsUser1 = service.getSubscribers().contains(user1);
		boolean containsUser2 = service.getSubscribers().contains(user2);
		
		System.out.println("Resultado esperado: 2 usuarios, ambos en la lista");
		System.out.println("Resultado obtenido: Tamaño = " + size + ", User1 en lista = " + containsUser1 + ", User2 en lista = " + containsUser2);
		
		assertEquals(2, size);
		assertTrue(containsUser1);
		assertTrue(containsUser2);
		
		System.out.println("✓ Prueba exitosa");
	}

}
