package it.caldesi.consumer.init;

import java.io.File;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.configuration2.XMLConfiguration;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import it.caldesi.consumer.conf.ConfigBean;
import it.caldesi.consumer.database.connector.DatabaseConnector;

public class Initializer implements ServletContextListener {

	private static String CONFIG_FILE_PATH = "/WEB-INF/conf/config.xml";
	private static final Logger logger = Logger.getLogger(Initializer.class);

	private ConfigBean configBean;

	@Override
	public void contextInitialized(ServletContextEvent contextEvent) {
		logger.info("Initializing context...");

		/*
		 * *********************************************************************
		 * *** Shut down logging from org.apache.commons.beanutils due a bug in
		 * library, see https://issues.apache.org/jira/browse/BEANUTILS-477
		 ***********************************************************************/
		Logger.getLogger("org.apache.commons.beanutils").setLevel(Level.OFF);
		/* ***********************************************************************/

		try {
			initializeConfiguration(contextEvent);
			// databaseConnector.connect(configBean);
		} catch (Exception e) {
			logger.error("Cannot initialize context", e);
		}
		logger.info("Context initialized");
	}

	private void initializeConfiguration(ServletContextEvent contextEvent) throws Exception {
		Configurations configs = new Configurations();
		File configFile = new File(contextEvent.getServletContext().getRealPath(CONFIG_FILE_PATH));
		logger.debug("Config file: " + configFile.getAbsolutePath());
		XMLConfiguration config = configs.xml(configFile.getAbsolutePath());

		configBean = ConfigBean.getInstance();
		configBean.host = config.getString("host");
		configBean.port = config.getInt("port");
		configBean.username = config.getString("username");
		configBean.password = config.getString("password");
		configBean.databaseName = config.getString("databaseName");
		configBean.collectionName = config.getString("collectionName");
	}

	@Override
	public void contextDestroyed(ServletContextEvent contextEvent) {
		logger.info("Destroying context...");
		try {
			DatabaseConnector.getInstance().destroyConnection();
		} catch (Exception e) {
			logger.error("Cannot destroy DatabaseConnector");
		}
		logger.info("Context destroyed");
	}

}
