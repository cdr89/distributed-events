package it.caldesi.producer.init;

import java.io.File;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.configuration2.XMLConfiguration;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import it.caldesi.producer.client.RESTClient;
import it.caldesi.producer.conf.ConfigBean;
import it.caldesi.producer.queue.EventQueueManager;
import it.caldesi.producer.queue.QueueConsumerThread;

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
			RESTClient.init(configBean);
			initializeQueueConsumerThreads();
		} catch (Exception e) {
			logger.error("Cannot initialize context", e);
		}
		logger.info("Context initialized");
	}

	private void initializeQueueConsumerThreads() {
		int numOfThread = configBean.queueConsumerThreads;
		long sleepTime = configBean.queueConsumerThreadSleepTime;
		EventQueueManager eventQueueManager = EventQueueManager.getInstance();
		for (int i = 0; i < numOfThread; i++) {
			QueueConsumerThread consumerThread = new QueueConsumerThread(sleepTime);
			eventQueueManager.registerQueueConsumerThread(consumerThread);
			consumerThread.start();
			logger.debug("Registered Thread #" + i + " with id:" + consumerThread.getId());
		}
	}

	private void initializeConfiguration(ServletContextEvent contextEvent) throws Exception {
		Configurations configs = new Configurations();
		File configFile = new File(contextEvent.getServletContext().getRealPath(CONFIG_FILE_PATH));
		logger.debug("Config file: " + configFile.getAbsolutePath());
		XMLConfiguration config = configs.xml(configFile.getAbsolutePath());

		configBean = ConfigBean.getInstance();
		configBean.eventEndpoint = config.getString("eventEndpoint");
		configBean.queueConsumerThreads = config.getInt("queueConsumerThreads");
		configBean.queueConsumerThreadSleepTime = config.getLong("queueConsumerThreadSleepTime");
	}

	@Override
	public void contextDestroyed(ServletContextEvent contextEvent) {
		logger.info("Destroying context...");
		EventQueueManager.getInstance().destroy();
		logger.info("Context destroyed");
	}

}
