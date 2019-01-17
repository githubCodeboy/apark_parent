package com.apark.common.response;

import com.alibaba.fastjson.JSON;

import java.io.Serializable;
import java.util.HashMap;

public class ServiceResponse implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * 状态码
     */
    private String code;
    /**
     * 返回信息
     */
    private String msg;

    private Object data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public ServiceResponse(Object data) {
        this.code = BaseResponseCode.SUCCESS.getCode();
        this.msg = BaseResponseCode.SUCCESS.getMsg();
        this.data = data;
    }

    public ServiceResponse() {
        this.code = BaseResponseCode.SUCCESS.getCode();
        this.msg = BaseResponseCode.SUCCESS.getMsg();
    }

    public ServiceResponse(BaseResponseCode baseResponseCode) {
        this.code = baseResponseCode.getCode();
        this.msg = baseResponseCode.getMsg();
    }

    public ServiceResponse(BaseResponseCode baseResponseCode, Object data) {
        this.code = baseResponseCode.getCode();
        this.msg = baseResponseCode.getMsg();
        this.data = data;
    }

    public ServiceResponse(String code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public ServiceResponse(String code, String msg) {
        this.code = code;
        this.msg = msg;

    }

    public static ServiceResponse success() {
        return new ServiceResponse();
    }

    public static ServiceResponse fail() {
        return new ServiceResponse(BaseResponseCode.FAIL);
    }

    public String toJson() {
        if (this.data == null) {
            this.data = new HashMap<>();
        }
        return  JSON.toJSONString(this);
        //return JsonUtils.objectToJson(this);
    }
}
