package de.ingoschindler.wild.boundary;

import de.ingoschindler.wild.entity.Category;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/{cid}")
@Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
public class CategorieResource {

    @Inject
    private PartResource partResource;

    @PersistenceContext
    private EntityManager em;

    @GET
    public List<Category> getUsersCategories(@PathParam("ref") String ref) {
        TypedQuery<Category> query = em.createNamedQuery("Category.usersCategories", Category.class);
        query.setParameter("ref", ref);
        List<Category> resultList = query.getResultList();

        return resultList;
    }

    @GET
    @Path("/{cid}")
    public Category geCategory(@PathParam("cid") Long cid) {
        return em.find(Category.class, cid);
    }

    @Path("/{cid}/parts")
    public PartResource getPartResource() {
        return partResource;
    }

}
