package com.my.project.hotrefresh;

import java.net.MalformedURLException;

import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.UrlResource;

@Configuration
@SpringBootApplication
public class HotRefreshApplication {

	public static void main(String[] args) {
		SpringApplication.run(HotRefreshApplication.class, args);
	}

	@Bean
	public YamlPropertiesFactoryBean yamlProperties() throws MalformedURLException {
		PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer = new PropertySourcesPlaceholderConfigurer();
		YamlPropertiesFactoryBean yamlProperties = new YamlPropertiesFactoryBean();
		yamlProperties.setResources(new UrlResource("http://localhost:8080/application-extends.yml"));
		propertySourcesPlaceholderConfigurer.setProperties(yamlProperties.getObject());
		return new YamlPropertiesFactoryBean();
	}
}
