package es.upm.grise.profundizacion.subscriptionService;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import es.upm.grise.profundizacion.subscriptionService.Delivery;
import es.upm.grise.profundizacion.subscriptionService.ExistingUserException;
import es.upm.grise..profundizacion.subscriptionService.LocalUserDoesNotHaveNullEMailException;
import es.upm.grise.profundizacion.subscriptionService.NullUserException;
import es.upm.grise.profundizacion.subscriptionService.SubscriptionService;
import es.upm.grise.profundizacion.subscriptionService.User;

public class SubscriptionServiceTest {

    private SubscriptionService service;

    @BeforeEach
    public void setUp() {
        service = new SubscriptionService();
        service.setSubscribers(new ArrayList<>());
    }


    @Test
    public void testAddValidSubscriber() {
        User user = new User();
        user.setDelivery(Delivery.DO_NOT_DELIVER);
        user.setEmail("test@mail.com");

        service.addSubscriber(user);

        assertEquals(1, service.getSubscribers().size());
        assertTrue(service.getSubscribers().contains(user));
    }

    @Test
    public void testAddNullUserThrowsException() {
        assertThrows(NullUserException.class, () -> {
            service.addSubscriber(null);
        });
    }

    @Test
    public void testAddExistingUserThrowsException() {
        User user = new User();
        user.setDelivery(Delivery.DO_NOT_DELIVER);
        user.setEmail("test@mail.com");

        service.addSubscriber(user);

        assertThrows(ExistingUserException.class, () -> {
            service.addSubscriber(user);
        });
    }

    @Test
    public void testLocalUserWithEmailThrowsException() {
        User local = new User();
        local.setDelivery(Delivery.LOCAL);
        local.setEmail("algo@mail.com");  

        assertThrows(LocalUserDoesNotHaveNullEMailException.class, () -> {
            service.addSubscriber(local);
        });
    }

    @Test
    public void testLocalUserWithNullEmailIsAccepted() {
        User local = new User();
        local.setDelivery(Delivery.LOCAL);
        local.setEmail(null);

        service.addSubscriber(local);

        assertEquals(1, service.getSubscribers().size());
        assertTrue(service.getSubscribers().contains(local));
    }
}

