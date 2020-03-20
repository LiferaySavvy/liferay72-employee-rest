package com.liferaysavvy.employee.rest.application;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Application;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.jaxrs.whiteboard.JaxrsWhiteboardConstants;

/**
 * @author meera
 */
@Component(
	property = {
		JaxrsWhiteboardConstants.JAX_RS_APPLICATION_BASE + "=/liferaysavvy",
		JaxrsWhiteboardConstants.JAX_RS_NAME + "=LiferaySavvy.Rest"
	},
	service = Application.class
)
public class Liferay72EmployeeRestApplication extends Application {

	@Override
	public Set<Object> getSingletons() {
		
		Set<Object> singletons = new HashSet<Object>();
		
		//add the automated Jackson marshaller for JSON.
		singletons.add(new JacksonJsonProvider());
		
		// add Employee REST endpoints (resources)
		singletons.add(new EmployeeResource());
		
		return singletons;
	}

	@GET
	@Produces("text/plain")
	public String working() {
		return "It works!";
	}

	@GET
	@Path("/morning")
	@Produces("text/plain")
	public String hello() {
		return "Good morning!";
	}

	@GET
	@Path("/morning/{name}")
	@Produces("text/plain")
	public String morning(
		@PathParam("name") String name,
		@QueryParam("drink") String drink) {

		String greeting = "Good Morning " + name;

		if (drink != null) {
			greeting += ". Would you like some " + drink + "?";
		}

		return greeting;
	}

}