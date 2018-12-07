package de.ingoschindler.wild.boundary;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import de.ingoschindler.wild.entity.Category;
import de.ingoschindler.wild.entity.Part;
import de.ingoschindler.wild.entity.UserPart;

@Path("/users/{ref}")
@Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
public class UserResource {

	@PersistenceContext(unitName = "wild")
	private EntityManager em;

	@GET
	@Path("categories/")
	public List<Category> getUsersCategories(@PathParam("ref") String ref) {
		TypedQuery<Category> query = em.createNamedQuery("Category.usersCategories", Category.class);
		query.setParameter("ref", ref);
		List<Category> resultList = query.getResultList();

		return resultList;
	}

	@GET
	@Path(("categories/{cid}/parts"))
	public List<UserPart> getUsersParts(@PathParam("ref") String ref, @PathParam("cid") Long cid) {
		TypedQuery<Part> query = em.createNamedQuery("Part.usersPartsByCategory", Part.class);

		query.setParameter("cid", cid);
		query.setParameter("ref", ref);
		List<Part> resultList = query.getResultList();

		List<UserPart> collect = resultList.stream().map(UserPart::new).collect(Collectors.toList());

		return collect;
	}

}
