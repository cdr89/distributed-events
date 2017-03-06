package it.caldesi.producer.exceptions;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

/**
 * Exception mapper for all kind of exceptions, it should be only for unchecked!
 */
public class GenericExceptionMapper implements ExceptionMapper<Throwable> {

	@Override
	public Response toResponse(Throwable throwable) {
		ThrowableBean throwableBean = new ThrowableBean(throwable);

		return Response.status(500) // generic server error
				.entity(throwableBean).type(MediaType.APPLICATION_JSON).build();

	}

}
