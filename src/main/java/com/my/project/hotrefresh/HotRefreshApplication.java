package com.my.project.hotrefresh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

@Configuration
@SpringBootApplication
public class HotRefreshApplication {

	public static void main(String[] args) {
		SpringApplication.run(HotRefreshApplication.class, args);
	}

}
