package de.ingoschindler.wild.boundary;

import de.ingoschindler.wild.entity.Category;
import de.ingoschindler.wild.entity.Part;
import de.ingoschindler.wild.entity.UserPart;

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
import java.util.stream.Collectors;

@Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
public class CategorieResource {

    @PathParam("ref")
    private String ref;

    @PathParam("id")
    private Long id;

    @PersistenceContext
    private EntityManager em;

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
