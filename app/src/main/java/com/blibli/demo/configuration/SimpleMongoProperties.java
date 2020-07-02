package com.blibli.demo.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("mongo")
public class SimpleMongoProperties {
	private Integer connectionPerHost;
	private Integer minConnectionsPerHost;
	private Integer threadsAllowedToBlockForConnectionMultiplier;
	private Integer connectTimeout;
	private Integer maxWaitTime;
	private Integer socketTimeout;
	private Boolean socketKeepAlive;
	private Integer heartbeatConnectTimeout;
	private Integer heartbeatFrequency;
	private Integer heartbeatSocketTimeout;
	private Integer maxConnectionIdleTime;
	private Integer maxConnectionLifeTime;
	private Integer minHeartbeatFrequency;
	private String readPreference;
	private Integer writeNumber;

	public Integer getConnectionPerHost() {
		return connectionPerHost;
	}

	public void setConnectionPerHost(Integer connectionPerHost) {
		this.connectionPerHost = connectionPerHost;
	}

	public Integer getMinConnectionsPerHost() {
		return minConnectionsPerHost;
	}

	public void setMinConnectionsPerHost(Integer minConnectionsPerHost) {
		this.minConnectionsPerHost = minConnectionsPerHost;
	}

	public Integer getThreadsAllowedToBlockForConnectionMultiplier() {
		return threadsAllowedToBlockForConnectionMultiplier;
	}

	public void setThreadsAllowedToBlockForConnectionMultiplier(Integer threadsAllowedToBlockForConnectionMultiplier) {
		this.threadsAllowedToBlockForConnectionMultiplier = threadsAllowedToBlockForConnectionMultiplier;
	}

	public Integer getConnectTimeout() {
		return connectTimeout;
	}

	public void setConnectTimeout(Integer connectTimeout) {
		this.connectTimeout = connectTimeout;
	}

	public Integer getMaxWaitTime() {
		return maxWaitTime;
	}

	public void setMaxWaitTime(Integer maxWaitTime) {
		this.maxWaitTime = maxWaitTime;
	}

	public Integer getSocketTimeout() {
		return socketTimeout;
	}

	public void setSocketTimeout(Integer socketTimeout) {
		this.socketTimeout = socketTimeout;
	}

	public Integer getHeartbeatConnectTimeout() {
		return heartbeatConnectTimeout;
	}

	public void setHeartbeatConnectTimeout(Integer heartbeatConnectTimeout) {
		this.heartbeatConnectTimeout = heartbeatConnectTimeout;
	}

	public Integer getHeartbeatFrequency() {
		return heartbeatFrequency;
	}

	public void setHeartbeatFrequency(Integer heartbeatFrequency) {
		this.heartbeatFrequency = heartbeatFrequency;
	}

	public Integer getHeartbeatSocketTimeout() {
		return heartbeatSocketTimeout;
	}

	public void setHeartbeatSocketTimeout(Integer heartbeatSocketTimeout) {
		this.heartbeatSocketTimeout = heartbeatSocketTimeout;
	}

	public Integer getMaxConnectionIdleTime() {
		return maxConnectionIdleTime;
	}

	public void setMaxConnectionIdleTime(Integer maxConnectionIdleTime) {
		this.maxConnectionIdleTime = maxConnectionIdleTime;
	}

	public Integer getMaxConnectionLifeTime() {
		return maxConnectionLifeTime;
	}

	public void setMaxConnectionLifeTime(Integer maxConnectionLifeTime) {
		this.maxConnectionLifeTime = maxConnectionLifeTime;
	}

	public Integer getMinHeartbeatFrequency() {
		return minHeartbeatFrequency;
	}

	public void setMinHeartbeatFrequency(Integer minHeartbeatFrequency) {
		this.minHeartbeatFrequency = minHeartbeatFrequency;
	}

	public String getReadPreference() {
		return readPreference;
	}

	public void setReadPreference(String readPreference) {
		this.readPreference = readPreference;
	}

	public Integer getWriteNumber() {
		return writeNumber;
	}

	public void setWriteNumber(Integer writeNumber) {
		this.writeNumber = writeNumber;
	}

	public Boolean getSocketKeepAlive() {
		return socketKeepAlive;
	}

	public void setSocketKeepAlive(Boolean socketKeepAlive) {
		this.socketKeepAlive = socketKeepAlive;
	}
}
