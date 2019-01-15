package de.ingoschindler.wild.members;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.security.RolesAllowed;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import de.ingoschindler.wild.entity.User;

@Named
@ViewScoped
@RolesAllowed({ "USERS" })
public class CustomersBacking implements Serializable {
	private static final long serialVersionUID = -3501023646103118488L;

	private List<User> customers;

	private User customer = new User();

	@Inject
	private CustomerManager customerManager;

	@PostConstruct
	public void init() {
		this.customers = customerManager.loadAllCustomers();
	}

	public void delete(User customer) {
		customerManager.delete(customer);
		customers.remove(customer);
	}

	public void add() {
		customerManager.addNewCustomer(customer);
		this.customers = customerManager.loadAllCustomers();
		this.customer = new User();
	}

	public void update() {
		customerManager.update(customers);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Update successful"));
	}

	public List<User> getCustomers() {
		return customers;
	}

	public void setCustomers(List<User> customers) {
		this.customers = customers;
	}

	public User getCustomer() {
		return customer;
	}

	public void setCustomer(User customer) {
		this.customer = customer;
	}

}