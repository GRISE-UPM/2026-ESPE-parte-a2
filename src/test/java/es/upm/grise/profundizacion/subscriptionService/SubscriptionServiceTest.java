package es.upm.grise.profundizacion.subscriptionService;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SubscriptionServiceTest {
	
	private SubscriptionService servicioSuscripciones;
	
	@BeforeEach
	public void prepararCadaPrueba() {
		
		servicioSuscripciones = new SubscriptionService();
	}
	
	@Test
	public void alCrearElServicio_NoDebeHaberNingunSuscriptor() {

		int cantidadDeSuscriptores = servicioSuscripciones.getSubscribers().size();
		
		assertEquals(0, cantidadDeSuscriptores);
	}
	
	@Test
	public void cuandoAgregamosUnUsuarioValido_DebeQuedarSuscrito() throws NullUserException, ExistingUserException, LocalUserDoesNotHaveNullEMailException {
	
		User nuevoUsuario = new User();
		nuevoUsuario.setEmail("maria@ejemplo.com");
		nuevoUsuario.setDelivery(Delivery.DO_NOT_DELIVER);
		
	
		servicioSuscripciones.addSubscriber(nuevoUsuario);
		
		
		int totalSuscriptores = servicioSuscripciones.getSubscribers().size();
		boolean estaEnLaLista = servicioSuscripciones.getSubscribers().contains(nuevoUsuario);
		
		assertEquals(1, totalSuscriptores);
		assertTrue(estaEnLaLista);
	}
	
	@Test
	public void siIntentoAgregarUnUsuarioNulo_DebeRechazarlo() {
		
		try {
			servicioSuscripciones.addSubscriber(null);
			fail("Debería haber lanzado NullUserException");
		} catch (NullUserException error) {
		
			assertEquals("User cannot be null", error.getMessage());
		} catch (Exception e) {
			fail("Lanzó una excepción incorrecta: " + e.getClass().getName());
		}
	}
	
	@Test
	public void siIntentoAgregarUnUsuarioYaExistente_DebeRechazarlo() throws NullUserException, ExistingUserException, LocalUserDoesNotHaveNullEMailException {
	
		User usuarioExistente = new User();
		usuarioExistente.setEmail("juan@ejemplo.com");
		usuarioExistente.setDelivery(Delivery.DO_NOT_DELIVER);
		servicioSuscripciones.addSubscriber(usuarioExistente);
		
		
		try {
			servicioSuscripciones.addSubscriber(usuarioExistente);
			fail("Debería haber lanzado ExistingUserException");
		} catch (ExistingUserException error) {
			
			assertEquals("User is already subscribed", error.getMessage());
		}
	}
	
	@Test
	public void usuarioConEntregaLocal_PuedeNoTenerEmail() throws NullUserException, ExistingUserException, LocalUserDoesNotHaveNullEMailException {
	
		User usuarioLocal = new User();
		usuarioLocal.setEmail(null);
		usuarioLocal.setDelivery(Delivery.LOCAL);
		
	
		servicioSuscripciones.addSubscriber(usuarioLocal);
		
	
		int cantidadSuscriptores = servicioSuscripciones.getSubscribers().size();
		boolean seAgregoCorrectamente = servicioSuscripciones.getSubscribers().contains(usuarioLocal);
		
		assertEquals(1, cantidadSuscriptores);
		assertTrue(seAgregoCorrectamente);
	}
	
	@Test
	public void usuarioConEntregaLocal_NoDebePermitirEmail() {
	
		User usuarioLocalConEmail = new User();
		usuarioLocalConEmail.setEmail("esto.no.deberia.estar@aqui.com");
		usuarioLocalConEmail.setDelivery(Delivery.LOCAL);
		
		
		try {
			servicioSuscripciones.addSubscriber(usuarioLocalConEmail);
			fail("Debería haber lanzado LocalUserDoesNotHaveNullEMailException");
		} catch (LocalUserDoesNotHaveNullEMailException error) {
			
			assertEquals("Local user must have null email", error.getMessage());
		} catch (Exception e) {
			fail("Lanzó una excepción incorrecta");
		}
	}
	
	@Test
	public void puedoAgregarVariosUsuariosDiferentes() throws NullUserException, ExistingUserException, LocalUserDoesNotHaveNullEMailException {
		
		User primerUsuario = new User();
		primerUsuario.setEmail("ana@ejemplo.com");
		primerUsuario.setDelivery(Delivery.DO_NOT_DELIVER);
		
	
		User segundoUsuario = new User();
		segundoUsuario.setEmail("carlos@ejemplo.com");
		segundoUsuario.setDelivery(Delivery.DO_NOT_DELIVER);
		
		
		servicioSuscripciones.addSubscriber(primerUsuario);
		servicioSuscripciones.addSubscriber(segundoUsuario);
		
		
		int totalUsuarios = servicioSuscripciones.getSubscribers().size();
		boolean tieneAlPrimero = servicioSuscripciones.getSubscribers().contains(primerUsuario);
		boolean tieneAlSegundo = servicioSuscripciones.getSubscribers().contains(segundoUsuario);
		
		assertEquals(2, totalUsuarios);
		assertTrue(tieneAlPrimero);
		assertTrue(tieneAlSegundo);
	}
	
}
