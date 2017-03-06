package it.caldesi.producer.conf;

public class ConfigBean {

	private static ConfigBean instance = null;

	public String eventEndpoint;
	public int queueConsumerThreads;
	public long queueConsumerThreadSleepTime;

	private ConfigBean() {
	}

	public static ConfigBean getInstance() {
		if (instance == null)
			instance = new ConfigBean();

		return instance;
	}

}
