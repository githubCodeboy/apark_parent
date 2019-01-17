package com.apark.common.utils.sms;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class SmsAccess implements Serializable {

    protected Integer id;
    protected String name;
    protected String signName;
    protected String templateCode;
    protected Date createTime;
    protected Integer source;
    protected String accessKey;
    protected String accessSecret;
    protected String jsonData;
    protected boolean pushQueue;
    protected String ips;
    protected String beanName;



}

