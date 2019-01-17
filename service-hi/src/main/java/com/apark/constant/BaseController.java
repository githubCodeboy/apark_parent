package com.apark.constant;


import com.ayhealth.constant.UserConstants;

import com.ayhealth.request.ServiceRequest;
import com.ayhealth.response.BaseResponseCode;
import com.ayhealth.response.ServiceResponse;
import com.ayhealth.wxUtils.wechatConfig.SignUtil;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;


@Data
@Slf4j
public class BaseController {

	@Autowired
	HttpServletRequest request;

	public ServiceRequest getServiceRequest(){
		return (ServiceRequest) request.getAttribute("serviceRequest");
	}
	

	public ServiceResponse checkRequiredParams(ServiceRequest serviceRequest, String... requiredParams){
		if(requiredParams == null){
			return new ServiceResponse(BaseResponseCode.PARAMETERS_NULL);
		}
		
		Map<String, Object> requestParams = serviceRequest.getParams();
		for(String param : requiredParams){
			if(!requestParams.containsKey(param)){
				return new ServiceResponse(BaseResponseCode.PARAMETERS_MISS.getCode(), "miss paramerter:" + param, null);
			}
			
			if(requestParams.get(param) == null){
				return new ServiceResponse(BaseResponseCode.PARAMETERS_NULL.getCode(), "null paramerter:" + param, null);
			}
			
		}
		return ServiceResponse.success();
	}


	/**
	 * 微信返回  回调接受成功
	 *
	 */

	public void WXReturnOK(HttpServletResponse response) {
		PrintWriter writer = null;
		try {
			writer = response.getWriter();

			String  noticeStr = SignUtil.setXML("SUCCESS", "OK");
			log.info("收到通知返回给微信api信息:-----------"+noticeStr);
			writer.write(noticeStr);
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 *微信返回  回调接受失败
	 */
	public void WXReturnFail(HttpServletResponse response){
		PrintWriter writer = null;
		try {
			writer = response.getWriter();
			String  noticeStr = SignUtil.setXML("FAIL", "");
			writer.write(noticeStr);
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
