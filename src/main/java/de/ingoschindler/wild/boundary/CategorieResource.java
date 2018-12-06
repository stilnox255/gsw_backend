package de.ingoschindler.wild.boundary;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import de.ingoschindler.wild.entity.Category;

@Path("/categories")
@Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
public class CategorieResource {

	@PersistenceContext
	private EntityManager em;

	@GET
	@Path("{id}")
	public Category getUsersCategories(@PathParam("id") Long ref) {
		return em.find(Category.class, ref);
	}

	@GET
	public List<Category> getUsersCategories(@QueryParam("u") @DefaultValue("") String ref) {
		TypedQuery<Category> query = em.createNamedQuery("Category.usersCategories", Category.class);

		query.setParameter("ref", ref);

		return query.getResultList();
	}
}
