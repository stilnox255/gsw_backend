package de.ingoschindler.wild.members;

import java.io.Serializable;
import java.security.Principal;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.security.enterprise.SecurityContext;

@Named
@SessionScoped
public class Backing implements Serializable {
	private static final long serialVersionUID = 6859335348384757857L;

	@Inject
	private SecurityContext securityContext;

	public Principal getUserPrincipal() {

		return securityContext.getCallerPrincipal();
	}

}
