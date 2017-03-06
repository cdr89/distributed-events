package it.caldesi.consumer.application;

import java.util.Set;

import javax.inject.Singleton;

import org.apache.log4j.Logger;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.process.internal.RequestScoped;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.scanners.TypeAnnotationsScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;

import it.caldesi.consumer.database.operations.DbOperations;
import it.caldesi.consumer.services.Service;

public class Binder extends AbstractBinder {

	private static final Logger logger = Logger.getLogger(Binder.class);

	private static final String SERVICES_PACKAGE = "it.caldesi.consumer.services";
	private static final String OPERATIONS_PACKAGE = "it.caldesi.consumer.database.operations";

	@Override
	protected void configure() {
		bindDbOperations();
		bindServices();
	}

	private void bindDbOperations() {
		ConfigurationBuilder configurationBuilder = new ConfigurationBuilder();
		configurationBuilder.setUrls(ClasspathHelper.forPackage(OPERATIONS_PACKAGE));
		configurationBuilder.addScanners(new SubTypesScanner(), new TypeAnnotationsScanner());

		Reflections reflections = new Reflections(configurationBuilder);

		Set<Class<?>> operations = reflections.getTypesAnnotatedWith(DbOperations.class);

		for (Class<?> operation : operations) {
			Singleton[] singleton = operation.getAnnotationsByType(Singleton.class);
			if (singleton != null && singleton.length > 0) {
				bind(operation).to(operation).in(Singleton.class);
				logger.debug("Bound DBOperations " + operation.getName() + " to " + operation.getName());
			} else {
				bind(operation).to(operation).in(RequestScoped.class);
				logger.debug("Bound DBOperations " + operation.getName() + " to " + operation.getName());
			}
		}
	}

	private void bindServices() {
		ConfigurationBuilder configurationBuilder = new ConfigurationBuilder();
		configurationBuilder.setUrls(ClasspathHelper.forPackage(SERVICES_PACKAGE));
		configurationBuilder.addScanners(new SubTypesScanner(), new TypeAnnotationsScanner());

		Reflections reflections = new Reflections(configurationBuilder);

		Set<Class<?>> operations = reflections.getTypesAnnotatedWith(Service.class);

		for (Class<?> operation : operations) {
			Singleton[] singleton = operation.getAnnotationsByType(Singleton.class);
			if (singleton != null && singleton.length > 0) {
				bind(operation).to(operation).in(Singleton.class);
				logger.debug("Bound Service " + operation.getName() + " to " + operation.getName());
			} else {
				bind(operation).to(operation).in(RequestScoped.class);
				logger.debug("Bound Service " + operation.getName() + " to " + operation.getName());
			}
		}
	}

}
