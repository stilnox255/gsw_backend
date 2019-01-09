package de.ingoschindler.wild.security;

import java.util.Arrays;
import java.util.HashSet;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.security.enterprise.credential.Credential;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.security.enterprise.identitystore.CredentialValidationResult;
import javax.security.enterprise.identitystore.IdentityStore;

import de.ingoschindler.wild.entity.User;
import de.rtner.security.auth.spi.SimplePBKDF2;

@ApplicationScoped
public class CustomInMemoryIdentityStore implements IdentityStore {

	@PersistenceContext(unitName = "wild")
	private EntityManager em;

	@PostConstruct
	private void init() {
		System.out.println("initialized CustomIdentityStore");
	}

	@Override
	public CredentialValidationResult validate(Credential credential) {
		UsernamePasswordCredential login = (UsernamePasswordCredential) credential;

		User singleResult = this.em.createNamedQuery("User.byUsername", User.class)
				.setParameter("username", login.getCaller()).getSingleResult();

		boolean valid = new SimplePBKDF2().verifyKeyFormatted(singleResult.getPassword(), login.getPasswordAsString());

		if (valid) {
			return new CredentialValidationResult(singleResult.getUsername(), new HashSet<>(Arrays.asList("USER")));
		} else {
			return CredentialValidationResult.NOT_VALIDATED_RESULT;
		}
	}
}