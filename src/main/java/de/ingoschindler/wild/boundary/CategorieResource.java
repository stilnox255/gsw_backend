package de.ingoschindler.wild.boundary;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import de.ingoschindler.wild.entity.Category;
import de.ingoschindler.wild.entity.Part;
import de.ingoschindler.wild.entity.UserPart;

@Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
public class CategorieResource {

	private Long id;
	private String ref;
	private EntityManager em;

	public CategorieResource(EntityManager em, String ref, Long id) {
		this.em = em;
		this.ref = ref;
		this.id = id;
	}

	@GET
	@Path("/parts")
	public List<UserPart> getParts() {
		TypedQuery<Part> query = em.createNamedQuery("Part.usersPartsByCategory", Part.class);

		query.setParameter("cid", id);
		query.setParameter("ref", ref);
		List<Part> resultList = query.getResultList();

		List<UserPart> collect = resultList.stream().map(UserPart::new).collect(Collectors.toList());

		return collect;
	}

	@GET
	public Category getUsersCategories() {
		return em.find(Category.class, id);
	}

}
