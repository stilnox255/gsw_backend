package de.ingoschindler.wild.boundary;

import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import de.ingoschindler.wild.entity.Ping;

@Path("/ping")
@Stateless
@Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
@RolesAllowed({ "USER" })
public class CatRes {

	@GET
	public Ping getPing() {
		return new Ping();
	}

}
