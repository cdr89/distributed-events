package it.caldesi.producer.client;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import it.caldesi.producer.conf.ConfigBean;

public class RESTClient {

	private static RESTClient instance;

	private Client client;
	private WebTarget target;

	private RESTClient(String url) {
		client = ClientBuilder.newClient();
		target = client.target(url);
	}

	public static void init(ConfigBean configBean) {
		instance = new RESTClient(configBean.eventEndpoint);
	}

	public static RESTClient getInstance() {
		if (instance == null)
			throw new IllegalStateException("REST client not initialized!");

		return instance;
	}

	public WebTarget getTarget() {
		return target;
	}

}
