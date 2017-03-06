package it.caldesi.consumer.database.connector;

import java.net.UnknownHostException;

import org.apache.log4j.Logger;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

import it.caldesi.consumer.conf.ConfigBean;

public class DatabaseConnector {

	private static final Logger logger = Logger.getLogger(DatabaseConnector.class);

	private static DatabaseConnector instance;

	private MongoClient mongoClient;
	private DB db;
	private DBCollection collection;

	private DatabaseConnector() {

	}

	public static DatabaseConnector getInstance() {
		if (instance == null)
			instance = new DatabaseConnector();
		return instance;
	}

	public void connect(ConfigBean config) throws UnknownHostException {
		mongoClient = new MongoClient(config.host, config.port);
		db = mongoClient.getDB(config.databaseName);

		if (config.username != null && !config.username.trim().isEmpty()) {
			boolean auth = db.authenticate(config.username, config.password.toCharArray());
			if (!auth) {
				String errorMessage = "Cannot authenticate to DB";
				logger.error(errorMessage);
				throw new RuntimeException(errorMessage);
			}
		}

		collection = db.getCollection(config.collectionName);
	}

	public void destroyConnection() {
		try {
			mongoClient.close();
		} catch (Exception exception) {
			logger.error("Unable to close database connection", exception);
		}
	}

	public DBCollection getCollection() {
		return collection;
	}

}