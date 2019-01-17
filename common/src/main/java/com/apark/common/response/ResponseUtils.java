package com.apark.common.response;

public class ResponseUtils {

	public static boolean isSuccess(ServiceResponse serviceResponse){
		return serviceResponse.getCode().equals(BaseResponseCode.SUCCESS.getCode()) ? true : false;
	}
	
	public static boolean isNotSuccess(ServiceResponse serviceResponse){
		return serviceResponse.getCode().equals(BaseResponseCode.SUCCESS.getCode()) ? false : true;
	}
}
