package de.ingoschindler.wild.security;

import com.github.adminfaces.template.session.AdminSession;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class LogoutBacking {

    @Inject
    AdminSession adminSession;

    public String logout() {
        adminSession.setIsLoggedIn(false);
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/login.xhtml?faces-redirect=true";
    }
}