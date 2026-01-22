package es.upm.grise.profundizacion.subscriptionService;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SubscriptionServiceTest {

    private SubscriptionService service;

    @BeforeEach
    public void setUp() {
        service = new SubscriptionService();
    }

    @Test
    public void smokeTest() {
        assertNotNull(service);
    }

    @Test
    public void addSubscriber_ok_localDeliveryAndNullEmail() {
        User user = new User(null, Delivery.LOCAL);

        assertDoesNotThrow(() -> service.addSubscriber(user));
    }

    @Test
    public void addSubscriber_nullUser_throwsNullUserException() {
        assertThrows(
            NullUserException.class,
            () -> service.addSubscriber(null)
        );
    }

    @Test
    public void addSubscriber_existingUser_throwsExistingUserException() {
        User user = new User("test@email.com", Delivery.DO_NOT_DELIVER);

        service.addSubscriber(user);

        assertThrows(
            ExistingUserException.class,
            () -> service.addSubscriber(user)
        );
    }

    @Test
    public void addSubscriber_localDeliveryWithEmail_throwsException() {
        User user = new User("local@email.com", Delivery.LOCAL);

        assertThrows(
            LocalUserDoesNotHaveNullEMailException.class,
            () -> service.addSubscriber(user)
        );
    }

    @Test
    public void addSubscriber_doNotDeliverWithEmail_ok() {
        User user = new User("test@email.com", Delivery.DO_NOT_DELIVER);

        assertDoesNotThrow(() -> service.addSubscriber(user));
    }
}
