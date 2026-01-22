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
    public void testNullUserThrowsException() {
        
        assertThrows(NullUserException.class, () -> {
            service.addSubscriber(null);
        });
    }

    @Test
    public void testExistingUserThrowsException() throws Exception {
        User user = new User("test@upm.es", Delivery.DO_NOT_DELIVER);
        service.addSubscriber(user);
        
        
        assertThrows(ExistingUserException.class, () -> {
            service.addSubscriber(user);
        });
    }

    @Test
    public void testLocalUserWithEmailThrowsException() {
        
        User localUserWithEmail = new User("test@upm.es", Delivery.LOCAL);
        
        assertThrows(LocalUserDoesNotHaveNullEMailException.class, () -> {
            service.addSubscriber(localUserWithEmail);
        });
    }

    @Test
    public void testAddSubscriberSuccessfully() throws Exception {
        User validUser = new User(null, Delivery.LOCAL);
        service.addSubscriber(validUser);
        
        
        assertTrue(service.getSubscribers().contains(validUser));
    }
}