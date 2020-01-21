package de.ingoschindler.wild.boundary;

import de.ingoschindler.wild.entity.User;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/users/{ref}")
@Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
public class UserResource {

    @Inject
    private CategorieResource cr;

    @PersistenceContext(unitName = "wild")
    private EntityManager em;

    @GET
    public User getUser(@PathParam("ref") String ref) {
        TypedQuery<User> query = em.createNamedQuery(User.USER_BY_REF, User.class);
        query.setParameter("ref", ref);
        return query.getSingleResult();
    }

    @Path("/categories")
    public CategorieResource getCategorieResource() {
        return cr;
    }

}
