package de.ingoschindler.wild.boundary;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import de.ingoschindler.wild.entity.Part;

@Path("/part")
@Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
public class PartResource {

	@PersistenceContext(unitName = "wild")
	private EntityManager em;

	@GET
	@Path("{id}")
	public Part getUsersCategories(@PathParam("id") Long ref) {
		return em.find(Part.class, ref);
	}

}
