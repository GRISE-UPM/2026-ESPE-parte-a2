package es.upm.grise.profundizacion.subscriptionService;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class SubscriptionServiceTest {
	
	@Test
	public void smokeTest() {}

	@Test
	void addSubscriber_nullUser_throwsException() {
    SubscriptionService service = new SubscriptionService();
	assertThrows(
        SubscriptionService.NullUserException.class,
        () -> service.addSubscriber(null)
    );
}

@Test
void addSubscriber_existingUser_throwsException() throws Exception {
    SubscriptionService service = new SubscriptionService();
    User user = new User();

    service.addSubscriber(user);

    assertThrows(
        SubscriptionService.ExistingUserException.class,
        () -> service.addSubscriber(user)
    );
}


}
