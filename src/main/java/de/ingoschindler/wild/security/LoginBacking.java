package de.ingoschindler.wild.security;

import java.io.IOException;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.security.enterprise.AuthenticationStatus;
import javax.security.enterprise.SecurityContext;
import javax.security.enterprise.authentication.mechanism.http.AuthenticationParameters;
import javax.security.enterprise.credential.Credential;
import javax.security.enterprise.credential.Password;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Named
@RequestScoped
public class LoginBacking {

	@Inject
	private SecurityContext securityContext;

	@NotNull
	@Size(min = 3, max = 15, message = "Username must be between 3 and 15 characters")
	private String username;

	@NotNull
	@Size(min = 3, max = 50, message = "Password must be between 5 and 50 characters")
	private String password;

	@Inject
	private FacesContext facesContext;

	@Inject
	private ExternalContext externalContext;

	public void login() throws IOException {

		Credential credential = new UsernamePasswordCredential(username, new Password(password));

		AuthenticationStatus status = securityContext.authenticate(getRequest(facesContext), getResponse(facesContext),
				AuthenticationParameters.withParams().credential(credential));

		switch (status) {
		case SEND_CONTINUE:
			facesContext.responseComplete();
			break;
		case SEND_FAILURE:
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Login failed", null));
			break;
		case SUCCESS:
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Login succeed", null));
			externalContext.redirect(externalContext.getRequestContextPath() + "/members/index.xhtml");
			break;
		case NOT_DONE:
		}

	}

	private HttpServletResponse getResponse(FacesContext context) {
		return (HttpServletResponse) externalContext.getResponse();
	}

	private HttpServletRequest getRequest(FacesContext context) {
		return (HttpServletRequest) externalContext.getRequest();
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}