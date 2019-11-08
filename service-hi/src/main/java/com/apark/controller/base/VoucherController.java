package com.apark.controller.base;

import com.apark.common.Exception.BusinessException;
import com.apark.common.constant.ApiConstants;
import com.apark.common.response.ServiceResponse;
import com.apark.constant.BaseController;
import com.apark.service.Interface.IRedisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@Slf4j
@RequestMapping("/apark/voucher")
public class VoucherController extends BaseController {


    @Autowired
    private IRedisService  redisService;


    /**
     *  api 获取添加信息 分布式凭证
     * @return
     * @throws BusinessException
     */
    @RequestMapping("/get")
    public ServiceResponse addInfo(@RequestParam(required = true, name = "serviceName") String serviceName) throws BusinessException {

        String   workId =   ApiConstants.getSnowflakeIdWorker_id() + ""  ;
        String   key   = "voucher_" +workId +serviceName;
        redisService.set( key , workId   ,600 )  ;

        return new ServiceResponse("200","get addVoucher  success ",  key);
    }

}
