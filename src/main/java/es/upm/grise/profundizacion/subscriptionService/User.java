package es.upm.grise.profundizacion.subscriptionService;

public class User {
	
	// Some implementation details are not yet complete
	String email;
	Delivery delivery;
	
	public User(String email, Delivery delivery) {
        this.email = email;
        this.delivery = delivery;
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
