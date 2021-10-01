package com.space.SpaceCourier.config;


import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import com.space.SpaceCourier.api.SpaceCourierEndpoint;


@Component
@ApplicationPath("/api")
public class JerseyConfig extends ResourceConfig {
	public JerseyConfig() {
		register(SpaceCourierEndpoint.class);
	}
}
