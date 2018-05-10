package com.my.project.hotrefresh.dto;

public class ConfigInfo {

	private Boolean autoRefresh;
	private Long connectTimeoutMs;
    private String url;

    /**
	 * @return the autoRefresh
	 */
	public Boolean getAutoRefresh() {
		return autoRefresh;
	}
	/**
	 * @param autoRefresh the autoRefresh to set
	 */
	public void setAutoRefresh(Boolean autoRefresh) {
		this.autoRefresh = autoRefresh;
	}
	/**
	 * @return the connectTimeoutMs
	 */
	public Long getConnectTimeoutMs() {
		return connectTimeoutMs;
	}
	/**
	 * @param connectTimeoutMs the connectTimeoutMs to set
	 */
	public void setConnectTimeoutMs(Long connectTimeoutMs) {
		this.connectTimeoutMs = connectTimeoutMs;
	}
	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}
	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	@Override
	public String toString() {
		return "ConfigInfo [autoRefresh=" + autoRefresh + ", connectTimeoutMs=" + connectTimeoutMs + ", url=" + url
				+ "]";
	}

}
