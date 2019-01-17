package com.apark.common.response;

/**
 * 基本状态码200~999
 * @author johnny
 *
 */
public enum BaseResponseCode {
	
	SUCCESS("200", "success"),
	SERVICE_EXCEPTION("400", "service exception"),
	FAIL("-1", "FAIL"),
	NO_DATA("700", "no data"),
	PARAMETERS_MISS("801", "miss parameters"),
	PARAMETERS_NULL("802", "null parameters"),
	PARAMETERS_ILLEGAL("803", "illegal parameters"),
	TOKEN_INVALID("805", "invalid token"),
	AUTHEN_FAIL("806", "authentication fail"),
	PERMISSION_DENIED("807", "permission denied"),
	NOT_LOGIN("808", "not login"),
	TYPE_MISMATCH("809", "type mismatch"),
	PAGE_PARAMETERS_INVALID("810", "page parameters invalid"),
	PAGE_SIZE_OUT_OF_SIZE("811", "page size out of size"),
	SERVICE_IP_NO_ALLOW("-2", "page size out of size")
	;
	
	private String code;
	private String msg;
	
	private BaseResponseCode(String code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public String getMsg() {
		return msg;
	}

	public String getCode() {
		return code;
	}
	
}
