package de.ingoschindler.wild.parts;

import de.ingoschindler.wild.entity.Category;
import de.ingoschindler.wild.entity.Part;
import de.ingoschindler.wild.entity.User;

import javax.annotation.PostConstruct;
import javax.annotation.security.RolesAllowed;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.security.enterprise.SecurityContext;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
@RolesAllowed({"USERS"})
@Transactional
public class PartsBacking implements Serializable {
    private static final long serialVersionUID = 8820758723159191651L;

    @PersistenceContext(unitName = "wild")
    private EntityManager em;

    @Inject
    private SecurityContext securityContext;

    private Part newPart;

    private User user;

    private List<Part> parts;

    private List<Category> categories;

    @PostConstruct
    public void init() {
        String username = securityContext.getCallerPrincipal().getName();

        parts = em.createNamedQuery(Part.BY_USERNAME, Part.class)
                .setParameter("username", username)
                .getResultList();

        user = em.createNamedQuery(User.BY_USERNAME, User.class)
                .setParameter("username", username)
                .getSingleResult();

        categories = em.createNamedQuery(Category.FIND_ALL, Category.class).getResultList();

        initPart();
    }

    private void initPart() {
        newPart = new Part();
        newPart.setOwner(user);
    }

    public List<Part> getParts() {
        return parts;
    }


    public void savePart() {


        parts.add(newPart);
        em.flush();

        initPart();
    }

    public Part getNewPart() {
        return newPart;
    }

    public void setNewPart(Part newPart) {
        this.newPart = newPart;
    }

    public List<Category> getCategories() {
        return categories;
    }
}
