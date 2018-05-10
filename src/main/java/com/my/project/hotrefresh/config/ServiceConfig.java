package com.my.project.hotrefresh.config;

import java.util.List;

import com.my.project.hotrefresh.dto.ServiceInfo;

public class ServiceConfig {

	private List<ServiceInfo> service;

	/**
	 * @return the service
	 */
	public List<ServiceInfo> getService() {
		return service;
	}

	/**
	 * @param service the service to set
	 */
	public void setService(List<ServiceInfo> service) {
		this.service = service;
	}

}
