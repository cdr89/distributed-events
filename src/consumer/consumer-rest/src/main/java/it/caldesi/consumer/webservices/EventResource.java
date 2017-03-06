package it.caldesi.consumer.webservices;

import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import it.caldesi.consumer.services.EventService;
import it.caldesi.domain.EventBean;

@Path("/")
public class EventResource {

	@Inject
	private EventService eventService;

	@Path("/event")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response writeEvent(EventBean eventBean) throws Exception {
		eventService.persistEvent(eventBean);

		return Response.ok().build();
	}

}
