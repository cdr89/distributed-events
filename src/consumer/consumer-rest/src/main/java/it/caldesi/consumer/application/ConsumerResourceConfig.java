package it.caldesi.consumer.application;

import java.net.UnknownHostException;

import javax.ws.rs.ApplicationPath;

import org.apache.log4j.Logger;
import org.glassfish.jersey.server.ResourceConfig;

import it.caldesi.consumer.conf.ConfigBean;
import it.caldesi.consumer.database.connector.DatabaseConnector;
import it.caldesi.consumer.exceptions.GenericExceptionMapper;
import it.caldesi.consumer.init.Initializer;

@ApplicationPath("/")
public class ConsumerResourceConfig extends ResourceConfig {

	private static final Logger logger = Logger.getLogger(ConsumerResourceConfig.class);

	private static final String RESOURCES_PACKAGE = "it.caldesi.consumer.webservices";

	public ConsumerResourceConfig() {
		packages(RESOURCES_PACKAGE);
		register(new Binder());
		register(Initializer.class);
		register(GenericExceptionMapper.class);

		ConfigBean configBean = ConfigBean.getInstance();
		DatabaseConnector databaseConnector = DatabaseConnector.getInstance();
		try {
			databaseConnector.connect(configBean);
			logger.info("Database connected successfully!");
		} catch (UnknownHostException e) {
			logger.error("Cannot connect to Database", e);
		}
	}

}
