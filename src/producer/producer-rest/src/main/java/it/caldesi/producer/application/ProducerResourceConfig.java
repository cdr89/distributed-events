package it.caldesi.producer.application;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;

import it.caldesi.producer.exceptions.GenericExceptionMapper;
import it.caldesi.producer.init.Initializer;

@ApplicationPath("/rest")
public class ProducerResourceConfig extends ResourceConfig {

	private static final String RESOURCES_PACKAGE = "it.caldesi.producer.webservices";

	public ProducerResourceConfig() {
		packages(RESOURCES_PACKAGE);
		register(new Binder());
		register(Initializer.class);
		register(GenericExceptionMapper.class);
		register(MultiPartFeature.class);
	}

}