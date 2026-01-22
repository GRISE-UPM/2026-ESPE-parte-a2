package es.upm.grise.profundizacion.subscriptionService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Pruebas unitarias para SubscriptionService
 */
public class SubscriptionServiceTest {
	
	private SubscriptionService service;
	
	@BeforeEach
	public void setUp() {
		service = new SubscriptionService();
	}
	
	// ========== TESTS DE INICIALIZACION ==========
	
	@Test
	public void testServiceInicializaSinSuscriptores() {
		// Al inicializar, no hay usuarios suscritos
		assertTrue(service.getSubscribers().isEmpty());
	}
	
	// ========== TESTS DE AÑADIR USUARIO CORRECTAMENTE ==========
	
	@Test
	public void testAddSubscriberUsuarioValido() throws Exception {
		User user = new User();
		user.setDelivery(Delivery.DO_NOT_DELIVER);
		user.setEmail("test@email.com");
		
		service.addSubscriber(user);
		
		assertEquals(1, service.getSubscribers().size());
		assertTrue(service.getSubscribers().contains(user));
	}
	
	@Test
	public void testAddSubscriberUsuarioLocalConEmailNull() throws Exception {
		User user = new User();
		user.setDelivery(Delivery.LOCAL);
		user.setEmail(null);
		
		service.addSubscriber(user);
		
		assertEquals(1, service.getSubscribers().size());
		assertTrue(service.getSubscribers().contains(user));
	}
	
	@Test
	public void testAddSubscriberVariosUsuarios() throws Exception {
		User user1 = new User();
		user1.setDelivery(Delivery.DO_NOT_DELIVER);
		user1.setEmail("user1@email.com");
		
		User user2 = new User();
		user2.setDelivery(Delivery.LOCAL);
		user2.setEmail(null);
		
		User user3 = new User();
		user3.setDelivery(Delivery.DO_NOT_DELIVER);
		user3.setEmail("user3@email.com");
		
		service.addSubscriber(user1);
		service.addSubscriber(user2);
		service.addSubscriber(user3);
		
		assertEquals(3, service.getSubscribers().size());
	}
	
	// ========== TESTS DE EXCEPCION NullUserException ==========
	
	@Test
	public void testAddSubscriberUsuarioNullLanzaExcepcion() {
		assertThrows(NullUserException.class, () -> {
			service.addSubscriber(null);
		});
	}
	
	@Test
	public void testAddSubscriberUsuarioNullNoModificaLista() {
		try {
			service.addSubscriber(null);
			fail("Debería haber lanzado NullUserException");
		} catch (NullUserException e) {
			// Verificar que la lista sigue vacía
			assertTrue(service.getSubscribers().isEmpty());
		} catch (Exception e) {
			fail("Lanzó excepción incorrecta: " + e.getClass().getName());
		}
	}
	
	// ========== TESTS DE EXCEPCION ExistingUserException ==========
	
	@Test
	public void testAddSubscriberUsuarioYaExistenteLanzaExcepcion() throws Exception {
		User user = new User();
		user.setDelivery(Delivery.DO_NOT_DELIVER);
		user.setEmail("test@email.com");
		
		service.addSubscriber(user);
		
		// Intentar añadir el mismo usuario de nuevo
		assertThrows(ExistingUserException.class, () -> {
			service.addSubscriber(user);
		});
	}
	
	@Test
	public void testAddSubscriberUsuarioExistenteNoSeDuplica() throws Exception {
		User user = new User();
		user.setDelivery(Delivery.DO_NOT_DELIVER);
		user.setEmail("test@email.com");
		
		service.addSubscriber(user);
		
		try {
			service.addSubscriber(user);
			fail("Debería haber lanzado ExistingUserException");
		} catch (ExistingUserException e) {
			// Verificar que solo hay un usuario
			assertEquals(1, service.getSubscribers().size());
		}
	}
	
	// ========== TESTS DE EXCEPCION LocalUserDoesNotHaveNullEMailException ==========
	
	@Test
	public void testAddSubscriberLocalConEmailNoNullLanzaExcepcion() {
		User user = new User();
		user.setDelivery(Delivery.LOCAL);
		user.setEmail("test@email.com"); // Email NO es null, debería fallar
		
		assertThrows(LocalUserDoesNotHaveNullEMailException.class, () -> {
			service.addSubscriber(user);
		});
	}
	
	@Test
	public void testAddSubscriberLocalConEmailNoNullNoModificaLista() {
		User user = new User();
		user.setDelivery(Delivery.LOCAL);
		user.setEmail("test@email.com");
		
		try {
			service.addSubscriber(user);
			fail("Debería haber lanzado LocalUserDoesNotHaveNullEMailException");
		} catch (LocalUserDoesNotHaveNullEMailException e) {
			// Verificar que la lista sigue vacía
			assertTrue(service.getSubscribers().isEmpty());
		} catch (Exception e) {
			fail("Lanzó excepción incorrecta: " + e.getClass().getName());
		}
	}
	
	// ========== TESTS DE DELIVERY DO_NOT_DELIVER ==========
	
	@Test
	public void testAddSubscriberDoNotDeliverConEmailNull() throws Exception {
		User user = new User();
		user.setDelivery(Delivery.DO_NOT_DELIVER);
		user.setEmail(null);
		
		service.addSubscriber(user);
		
		assertEquals(1, service.getSubscribers().size());
	}
	
	@Test
	public void testAddSubscriberDoNotDeliverConEmail() throws Exception {
		User user = new User();
		user.setDelivery(Delivery.DO_NOT_DELIVER);
		user.setEmail("usuario@dominio.com");
		
		service.addSubscriber(user);
		
		assertEquals(1, service.getSubscribers().size());
	}
	
	// ========== TESTS DE CASOS LIMITE ==========
	
	@Test
	public void testAddSubscriberLocalConEmailVacio() {
		User user = new User();
		user.setDelivery(Delivery.LOCAL);
		user.setEmail(""); // Email vacío (no es null)
		
		// Un string vacío NO es null, por lo tanto debería lanzar excepción
		assertThrows(LocalUserDoesNotHaveNullEMailException.class, () -> {
			service.addSubscriber(user);
		});
	}
}
