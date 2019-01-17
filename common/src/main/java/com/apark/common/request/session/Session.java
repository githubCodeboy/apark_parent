package com.apark.common.request.session;

import java.io.Serializable;


public class Session implements Serializable {
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	private String token;
	
	private Integer userId;
	
	private long createdTime;

	public String getToken() {
		return token;
	}

	public Integer getUserId() {
		return userId;
	}

	public long getCreatedTime() {
		return createdTime;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public void setCreatedTime(long createdTime) {
		this.createdTime = createdTime;
	}
	
}
