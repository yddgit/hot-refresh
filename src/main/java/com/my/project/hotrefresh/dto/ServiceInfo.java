package com.my.project.hotrefresh.dto;

import java.util.List;

public class ServiceInfo {
	private String name;
    private List<String> address;

    /**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the address
	 */
	public List<String> getAddress() {
		return address;
	}
	/**
	 * @param address the address to set
	 */
	public void setAddress(List<String> address) {
		this.address = address;
	}
	@Override
	public String toString() {
		return "ServiceInfo [name=" + name + ", address=" + address + "]";
	}

}
