package com.brand.configuration;

import javax.ws.rs.ApplicationPath;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import com.brand.controller.DeviceController;
import com.brand.mapper.ConstraintViolationExceptionMapper;

@Component
@ApplicationPath("/api")
public class JerseyConfiguration extends ResourceConfig {

	/**
	 * register each controller in application path 
	 */
	public JerseyConfiguration() {
		this.register(DeviceController.class);
		this.register(ConstraintViolationExceptionMapper.class);
	}
}
