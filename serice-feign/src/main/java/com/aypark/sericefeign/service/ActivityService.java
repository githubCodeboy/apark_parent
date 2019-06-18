package com.aypark.sericefeign.service;



import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import  com.aypark.sericefeign.model.Activity;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * feign 封装 http 作为服务
 *
 */
@FeignClient( value = "hrservice" )
public interface ActivityService {



    @RequestMapping(value = "/activity",method = RequestMethod.GET)
    Activity  detailActivity(@RequestParam(value = "id") String id);


}
