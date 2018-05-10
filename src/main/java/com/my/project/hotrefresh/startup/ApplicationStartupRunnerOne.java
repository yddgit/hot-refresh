package com.my.project.hotrefresh.startup;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(value=1)
@Component
public class ApplicationStartupRunnerOne implements CommandLineRunner {

	private final Logger logger = LoggerFactory.getLogger(getClass());
 
    @Override
    public void run(String... args) throws Exception {
        logger.info("ApplicationStartupRunnerOne run method Started !!");
    }
}