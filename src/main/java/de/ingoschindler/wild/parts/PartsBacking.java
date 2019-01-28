package de.ingoschindler.wild.parts;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.security.RolesAllowed;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.security.enterprise.SecurityContext;

import de.ingoschindler.wild.entity.Part;

@Named
@ViewScoped
@RolesAllowed({ "USERS" })
public class PartsBacking implements Serializable {
	private static final long serialVersionUID = 8820758723159191651L;

	@PersistenceContext(unitName = "wild")
	private EntityManager em;

	@Inject
	private SecurityContext securityContext;

	private List<Part> parts;

	@PostConstruct
	public void init() {
		String username = securityContext.getCallerPrincipal().getName();

		TypedQuery<Part> query = em.createNamedQuery(Part.BY_USERNAME, Part.class);
		query.setParameter("username", username);

		parts = query.getResultList();

	}

	public List<Part> getParts() {
		return parts;
	}

}
