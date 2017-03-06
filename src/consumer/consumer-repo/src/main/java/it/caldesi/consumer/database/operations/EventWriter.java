package it.caldesi.consumer.database.operations;

import org.apache.log4j.Logger;

import com.mongodb.DBObject;

import it.caldesi.consumer.database.connector.DatabaseConnector;
import it.caldesi.consumer.util.Utils;
import it.caldesi.domain.EventBean;

@DbOperations
public class EventWriter {

	private static final Logger logger = Logger.getLogger(EventWriter.class);

	private DatabaseConnector databaseConnector = DatabaseConnector.getInstance();

	public void write(EventBean eventBean) throws Exception {
		if (eventBean == null)
			throw new IllegalArgumentException("No event received");

		DBObject document = Utils.buildFromEventBean(eventBean);
		try {
			databaseConnector.getCollection().insert(document);
		} catch (Exception exception) {
			logger.error("Unable to write the event: " + eventBean, exception);
			throw exception;
		}
	}

}
