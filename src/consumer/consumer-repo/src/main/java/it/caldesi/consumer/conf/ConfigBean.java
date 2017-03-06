package it.caldesi.consumer.conf;

public class ConfigBean {

	private static ConfigBean instance = null;

	public String host;
	public int port;
	public String username;
	public String password;
	public String databaseName;
	public String collectionName;

	private ConfigBean() {
	}

	public static ConfigBean getInstance() {
		if (instance == null)
			instance = new ConfigBean();

		return instance;
	}

}
