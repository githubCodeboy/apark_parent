package com.apark.common.Exception;

public class BusinessException extends Exception {

	private static final long serialVersionUID = 6116573137499928636L;

	public BusinessException(String msg) {
		super(msg);
	}
	
	public BusinessException(Throwable t) {
		super(t);
	}
	
	public BusinessException(String msg,Throwable t) {
		super(msg,t);
	}
	
}
