package it.caldesi.producer.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.log4j.Logger;

import it.caldesi.domain.EventBean;
import it.caldesi.producer.client.event.EventResourceClient;
import it.caldesi.producer.queue.EventQueueManager;

@Service
public class EventSenderService implements GenericService {

	private static final Logger logger = Logger.getLogger(EventSenderService.class);

	public static void sendEvent(EventBean event) throws Exception {
		if (event == null)
			return;

		EventResourceClient.sendEvent(event);
	}

	public void sendEvents(InputStream fileInputStream) {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
		String line = null;

		try {
			while ((line = bufferedReader.readLine()) != null) {
				line = line.trim();
				EventBean eventBean = EventBean.buildFromJsonString(line);
				logger.debug("EventBean read: " + eventBean);
				EventQueueManager.getInstance().queue(eventBean);
			}
		} catch (IOException e) {
			logger.error("Error reading file", e);
		}

	}

}