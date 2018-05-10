package com.my.project.hotrefresh.config;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.bind.PropertySourcesBinder;
// For Spring 2.0.2
//import org.springframework.boot.context.properties.bind.BindHandler;
//import org.springframework.boot.context.properties.bind.Bindable;
//import org.springframework.boot.context.properties.bind.Binder;
//import org.springframework.boot.context.properties.bind.handler.IgnoreTopLevelConverterNotFoundBindHandler;
//import org.springframework.boot.context.properties.source.ConfigurationPropertySources;
import org.springframework.boot.env.YamlPropertySourceLoader;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

@Component
public class ConfigCacheService {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	private static volatile boolean DEV = false;
	private static final int DEV_REFRESH_INTERVAL_SECOND = 5;

	private ResourceConfig resourceConfig;
	private ServiceConfig serviceConfig;

	@Value("${app.config.url:application-extends.yml}")
	private String configUrl;

	@PostConstruct
	public void init() {
		logger.info("Initializing App Config...");
		refresh();
		if(!ResourceUtils.isUrl(this.configUrl)) {
			DEV = true;
		}
		if(DEV) {
			new Thread(() -> {
				while(DEV) {
					try {
						logger.info("refresh...");
						loadConfig();
						TimeUnit.SECONDS.sleep(DEV_REFRESH_INTERVAL_SECOND);
					} catch (Exception e) {
						logger.error("App config auto refresh in dev environment failed!", e);
						DEV = false;
					}
				}
			}, "app-config-auto-refresh-in-dev-mode").start();
		}
		logger.info("Initializing App Config Finished.");
	}

	public void refresh() {
		try {
			logger.info("App Config Refresh Start...");
			loadConfig();
			logger.info("App Config Refresh Success!");
		} catch (Exception e) {
			logger.error("App Config Refresh Failed!", e);
		}
	}

	/**
	 * For Spring Boot 1.5.13
	 * @throws IOException 
	 */
	private void loadConfig() throws IOException {

		Resource config;
		if(!ResourceUtils.isUrl(this.configUrl)) {
			config = new ClassPathResource(this.configUrl);
		} else {
			config = new UrlResource(this.configUrl);
		}

		YamlPropertySourceLoader sourceLoader = new YamlPropertySourceLoader();
        PropertySource<?> yamlProperties = sourceLoader.load("app", config, null);
        this.resourceConfig = new ResourceConfig();
        PropertySourcesBinder binder = new PropertySourcesBinder(yamlProperties);
        binder.bindTo("app.resource", this.resourceConfig);
        this.serviceConfig = new ServiceConfig();
		binder.bindTo("app", this.serviceConfig);
	}

	/**
	 * For Spring Boot 2.0.2
	 * @throws IOException
	 */
	/*
	private void loadConfig() throws IOException {

		Resource config;
		if(!ResourceUtils.isUrl(this.configUrl)) {
			config = new ClassPathResource(this.configUrl);
		} else {
			config = new UrlResource(this.configUrl);
		}

		YamlPropertySourceLoader sourceLoader = new YamlPropertySourceLoader();
        List<PropertySource<?>> yamlProperties = sourceLoader.load("app", config);
		BindHandler handler = new IgnoreTopLevelConverterNotFoundBindHandler();
		Binder binder = new Binder(ConfigurationPropertySources.from(yamlProperties));
		this.resourceConfig = binder.bind("app.resource", Bindable.of(ResourceConfig.class), handler).get();
		this.serviceConfig = binder.bind("app", Bindable.of(ServiceConfig.class), handler).get();
	}
	*/

	/**
	 * @return the resourceConfig
	 */
	public ResourceConfig getResourceConfig() {
		return resourceConfig;
	}
	/**
	 * @return the serviceConfig
	 */
	public ServiceConfig getServiceConfig() {
		return serviceConfig;
	}

}
