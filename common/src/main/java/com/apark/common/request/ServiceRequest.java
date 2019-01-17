package com.apark.common.request;



import com.apark.common.request.session.Session;
import com.apark.common.utils.DateUtil;

import java.io.Serializable;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ServiceRequest implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	private Session session;
	
	private Map<String, Object> params = new HashMap<>();

	public Map<String, Object> getParams() {
		return params;
	}

	public void setParams(Map<String, Object> params) {
		this.params = params;
	}

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}
	
	public void put(String key, Object value){
		this.params.put(key, value);
	}
	
	public Object get(String key){
		return this.params.get(key);
	}
	
	public Integer getInt(String key) throws NumberFormatException {
		return params.containsKey(key) ? params.get(key) == null ? null : Integer.parseInt(params.get(key)
				.toString()) : null;
	}

	public Long getLong(String key) throws NumberFormatException {
		return params.containsKey(key) ? params.get(key) == null ? null : Long.parseLong(params.get(key)
				.toString()) : null;
	}

	public Double getDouble(String key) throws NumberFormatException {
		return params.containsKey(key) ? params.get(key) == null ? null : Double.parseDouble(params.get(key)
				.toString()) : null;
	}

	public String getString(String key) {
		return params.containsKey(key) ? params.get(key) == null ? null : params.get(key).toString() : null;
	}

	public Date getDate(String key) throws ParseException {
		return params.containsKey(key) ? params.get(key) == null ? null : DateUtil.parseDateTime(params
				.get(key).toString()) : null;
	}

	public boolean containKey(String key) {
		return params.containsKey(key);
	}

}
