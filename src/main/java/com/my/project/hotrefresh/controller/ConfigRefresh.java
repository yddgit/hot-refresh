package com.my.project.hotrefresh.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.my.project.hotrefresh.config.ConfigCacheService;

@RestController
@RequestMapping("/config")
public class ConfigRefresh {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	private static volatile Long LAST_REFRESH_TIME = System.currentTimeMillis();

	@Value("${app.config.min-refresh-interval-ms:20000}")
	private long minRefreshInterval;

	@Autowired
	private ConfigCacheService configCacheService;

	@RequestMapping(value = "/refresh", method = RequestMethod.POST)
	public void refresh() {
		long now = System.currentTimeMillis();
		if(now - LAST_REFRESH_TIME >= minRefreshInterval) {
			configCacheService.refresh();
			LAST_REFRESH_TIME = System.currentTimeMillis();
		}
		logger.info(configCacheService.toString());
	}
}
