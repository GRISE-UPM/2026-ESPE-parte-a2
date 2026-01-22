package es.upm.grise.profundizacion.subscriptionService;

import java.util.ArrayList;
import java.util.List;

public class User {
	
	private String email;
	private Delivery delivery;
	private List<String> messages;
	
	public User() {
		this.messages = new ArrayList<>();
		this.delivery = Delivery.EMAIL;
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
	
	public List<String> getMessages() {
		return messages;
	}
	
	public void addMessage(String message) {
		if (this.delivery == Delivery.LOCAL) {
			this.messages.add(message);
		}
	}
	
}
