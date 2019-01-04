package de.ingoschindler.wild.members;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import de.ingoschindler.wild.entity.User;

@Stateless
public class CustomerManager {

	private static final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

	@PersistenceContext(unitName = "wild")
	private EntityManager em;

	public List<User> loadAllCustomers() {
		return this.em.createQuery("SELECT c FROM User c", User.class).getResultList();
	}

	public void delete(User customer) {
		if (em.contains(customer)) {
			em.remove(customer);
		} else {
			User managedCustomer = em.find(User.class, customer.getId());
			if (managedCustomer != null) {
				em.remove(managedCustomer);
			}
		}
	}

	public String generateRefLink() {
		int count = 15;
		StringBuilder builder = new StringBuilder();
		while (count-- != 0) {
			int character = (int) (Math.random() * ALPHA_NUMERIC_STRING.length());
			builder.append(ALPHA_NUMERIC_STRING.charAt(character));
		}
		return builder.toString();
	}

	public void addNewCustomer(User customer) {
		User newCustomer = new User();
		newCustomer.setUsername(customer.getUsername());
		newCustomer.setEmail(customer.getEmail());
		newCustomer.setPassword(customer.getPassword());
		newCustomer.setRealName(customer.getRealName());
		newCustomer.setRef(generateRefLink());
		this.em.merge(newCustomer);
	}

	public void update(List<User> customers) {
		customers.forEach(em::merge);
	}
}