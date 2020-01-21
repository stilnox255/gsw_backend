package de.ingoschindler.wild.boundary;

import de.ingoschindler.wild.entity.Part;
import de.ingoschindler.wild.entity.UserPart;

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

@Path("/{pid}")
@Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
public class PartResource {

    @PersistenceContext(unitName = "wild")
    private EntityManager em;

    @PathParam("cid")
    private Long cid;

    @PathParam("ref")
    private String ref;

    @GET
    @Path("/{pid}")
    public Part getUsersCategories(@PathParam("pid") Long ref) {
        return em.find(Part.class, ref);
    }

    @GET
    public List<UserPart> getParts() {
        TypedQuery<Part> query = em.createNamedQuery(Part.BY_CATEGORY, Part.class);

        query.setParameter("cid", cid);
        query.setParameter("ref", ref);
        List<Part> resultList = query.getResultList();

        List<UserPart> collect = resultList.stream().map(UserPart::new).collect(Collectors.toList());

        return collect;
    }

}
