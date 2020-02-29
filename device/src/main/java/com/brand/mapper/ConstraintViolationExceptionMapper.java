/**
 *
 */
package com.brand.mapper;

import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Priority;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.glassfish.jersey.server.validation.ValidationError;

/**
 * @author Bozhidar
 *
 */
@Provider
public class ConstraintViolationExceptionMapper implements ExceptionMapper<ConstraintViolationException> {

	@Override
	public Response toResponse(final ConstraintViolationException exception) {
		return Response.status(Response.Status.BAD_REQUEST).entity(prepareMessage(exception))
				.type(MediaType.APPLICATION_JSON).build();
	}

	private String prepareMessage(ConstraintViolationException exception) {
		String msg = "";
		for (ConstraintViolation<?> cv : exception.getConstraintViolations()) {
			msg += cv.getMessage() + "\n";
		}
		return msg;
	}

}
