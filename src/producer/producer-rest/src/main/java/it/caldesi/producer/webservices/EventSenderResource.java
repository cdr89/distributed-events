package it.caldesi.producer.webservices;

import java.io.InputStream;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import it.caldesi.producer.services.EventSenderService;

@Path("/")
public class EventSenderResource {

	@Inject
	private EventSenderService eventSenderService;

	@Path("/event/send")
	@POST
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.APPLICATION_JSON)
	public Response sendEvent(@FormDataParam("file") InputStream fileInputStream,
			@FormDataParam("file") FormDataContentDisposition fileDetail) throws Exception {
		eventSenderService.sendEvents(fileInputStream);

		return Response.ok().build();
	}

}
