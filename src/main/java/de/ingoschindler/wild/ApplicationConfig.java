package de.ingoschindler.wild;

import javax.annotation.security.DeclareRoles;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.annotation.FacesConfig;
import javax.faces.annotation.FacesConfig.Version;
import javax.inject.Named;
import javax.security.enterprise.authentication.mechanism.http.BasicAuthenticationMechanismDefinition;
import javax.security.enterprise.authentication.mechanism.http.CustomFormAuthenticationMechanismDefinition;
import javax.security.enterprise.authentication.mechanism.http.LoginToContinue;
import javax.security.enterprise.identitystore.DatabaseIdentityStoreDefinition;
import javax.security.enterprise.identitystore.Pbkdf2PasswordHash;

@FacesConfig(version = Version.JSF_2_3)
@BasicAuthenticationMechanismDefinition
//@FormAuthenticationMechanismDefinition(loginToContinue = @LoginToContinue(loginPage = "/basiclogin.xhtml", errorPage = ""))
//@CustomFormAuthenticationMechanismDefinition(loginToContinue = @LoginToContinue(loginPage = "/login2.xhtml", errorPage = "", useForwardToLogin = true))
@CustomFormAuthenticationMechanismDefinition(loginToContinue = @LoginToContinue(loginPage = "/login.xhtml", errorPage = "", useForwardToLogin = true))
@DatabaseIdentityStoreDefinition(dataSourceLookup = "${'java:jboss/datasources/WildDS'}", //
        callerQuery = "#{'select password from USERS where username = ?'}", //
        groupsQuery = "select groupname from GROUPS g join USER_GROUPS ug on g.id = ug.group_id join users u on ug.users_id = u.id where u.username = ?", //
        hashAlgorithm = Pbkdf2PasswordHash.class, //
        priorityExpression = "#{100}", //
        hashAlgorithmParameters = {"Pbkdf2PasswordHash.Iterations=3072", "${applicationConfig.dyna}"} // just for test
)
@ApplicationScoped
@DeclareRoles({"${applicationConfig.roles}"})
@Named
public class ApplicationConfig {

    public String[] getDyna() {
        return new String[]{"Pbkdf2PasswordHash.Algorithm=PBKDF2WithHmacSHA512",
                "Pbkdf2PasswordHash.SaltSizeBytes=64"};
    }

    public String[] getRoles() {
        return new String[]{"USER", "ADMIN"};
    }
}