package es.upm.grise.profundizacion.subscriptionService;

public class User {
    
    private String email;
    private Delivery delivery;

    
    public User(String email, Delivery delivery) {
        this.email = email;
        this.delivery = delivery;
    }

    public User() {
    }

    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public Delivery getDelivery() {
        return delivery;
    }
    
    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
    }
}
