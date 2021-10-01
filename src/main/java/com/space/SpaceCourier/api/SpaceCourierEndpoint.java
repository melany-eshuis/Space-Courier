package com.space.SpaceCourier.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.space.SpaceCourier.domain.SpaceCourier;
import com.space.SpaceCourier.persistence.SpaceCourierService;

@Path("spacecourier")
@Component
public class SpaceCourierEndpoint {
	
	@Autowired
	private SpaceCourierService spaceCourierService;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response listGroup() {
		Iterable <SpaceCourier> spacecouriers = spaceCourierService.findAll();
		return Response.ok(spacecouriers).build();
	}

	
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public Response postSpaceCourier(SpaceCourier spacecourier) {
		SpaceCourier result = spaceCourierService.save(spacecourier);
		return Response.accepted(result.getId()).build();
		
	
	}
}
