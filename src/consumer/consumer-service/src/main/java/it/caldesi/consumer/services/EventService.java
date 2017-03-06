package it.caldesi.consumer.services;

import javax.inject.Inject;

import it.caldesi.consumer.database.operations.EventWriter;
import it.caldesi.domain.EventBean;

@Service
public class EventService implements GenericService {

	@Inject
	private EventWriter eventWriter;

	public void persistEvent(EventBean eventBean) throws Exception {
		eventWriter.write(eventBean);
	}

}