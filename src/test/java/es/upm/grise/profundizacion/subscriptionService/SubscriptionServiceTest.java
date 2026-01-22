package es.upm.grise.profundizacion.subscriptionService;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SubscriptionServiceTest {

    private SubscriptionService service;

    @BeforeEach
    void setUp() {
        service = new SubscriptionService();
    }

    @Test
    void testAddNullUserThrowsException() {
        assertThrows(NullUserException.class, () -> {
            service.addSubscriber(null);
        });
    }

    @Test
    void testAddExistingUserThrowsException() throws Exception {
        User user = new User("test@example.com", Delivery.EMAIL);
        service.addSubscriber(user);

        assertThrows(ExistingUserException.class, () -> {
            service.addSubscriber(user);
        });
    }

    @Test
    void testLocalUserWithNonNullEmailThrowsException() {
        User user = new User("notNull@example.com", Delivery.LOCAL);

        assertThrows(LocalUserDoesNotHaveNullEMailException.class, () -> {
            service.addSubscriber(user);
        });
    }

    @Test
    void testAddValidLocalUser() throws Exception {
        User user = new User(null, Delivery.LOCAL);
        service.addSubscriber(user);

        assertTrue(service.getSubscribers().contains(user));
    }

    @Test
    void testAddValidEmailUser() throws Exception {
        User user = new User("valid@example.com", Delivery.EMAIL);
        service.addSubscriber(user);

        assertTrue(service.getSubscribers().contains(user));
    }

    @Test
    void testAddValidDoNotDeliverUser() throws Exception {
        User user = new User("optional@example.com", Delivery.DO_NOT_DELIVER);
        service.addSubscriber(user);

        assertTrue(service.getSubscribers().contains(user));
    }
}
