package it.caldesi.producer.client.event;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import it.caldesi.domain.EventBean;
import it.caldesi.producer.client.RESTClient;

public class EventResourceClient {

	private static final Logger logger = Logger.getLogger(EventResourceClient.class);

	/**
	 * Fire and forget event sender
	 */
	public static void sendEvent(EventBean eventBean) {
		logger.debug("Sending event: " + eventBean.toString());
		RESTClient client = RESTClient.getInstance();
		WebTarget target = client.getTarget();

		Response response = target //
				.request(MediaType.APPLICATION_JSON_TYPE) //
				.post(Entity.entity(eventBean, MediaType.APPLICATION_JSON_TYPE));
		if (response.getStatus() != 200)
			logger.warn("Event " + eventBean + " not sended " + response.toString());
		else
			logger.debug("Event sent");
	}

}
