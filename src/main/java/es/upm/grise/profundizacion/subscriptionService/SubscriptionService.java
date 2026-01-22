package es.upm.grise.profundizacion.subscriptionService;

import java.util.Collection;
import java.util.HashSet;

public class SubscriptionService {

    private final Collection<User> subscribers;

    /*
     * Constructor
     */
    public SubscriptionService() {
        subscribers = new HashSet<>(); // inicializamos correctamente
    }

    /*
     * Método principal
     */
    public void addSubscriber(User user) 
        throws NullUserException, ExistingUserException, LocalUserDoesNotHaveNullEMailException {

        if (user == null) {
            throw new NullUserException("El usuario no puede ser null.");
        }

        if (subscribers.contains(user)) {
            throw new ExistingUserException("El usuario ya está suscrito.");
        }

        if (user.getDelivery() == Delivery.LOCAL && user.getEmail() != null) {
            throw new LocalUserDoesNotHaveNullEMailException(
                "Un usuario con delivery LOCAL debe tener email = null."
            );
        }

        subscribers.add(user);
    }

    /*
     * Getter
     */
    public Collection<User> getSubscribers() {
        return subscribers;
    }
}
