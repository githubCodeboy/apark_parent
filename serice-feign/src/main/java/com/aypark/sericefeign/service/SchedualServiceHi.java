package com.aypark.sericefeign.service;

import com.apark.pojo.user.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


/**
 * feign 封装 http 作为服务
 *
 */
@FeignClient(value = "service-hi" ,fallback = SchedualServiceHiHystric.class)
public interface SchedualServiceHi {

    @RequestMapping(value = "/hi",method = RequestMethod.GET)
    String sayHiFromClientOne(@RequestParam(value = "name") String name);


    @RequestMapping(value = "/apark/user/testAdd",method = RequestMethod.GET)
    String addUser();


    @RequestMapping(value = "/apark/user/getPageList",method = RequestMethod.GET)
    List<User> getPageList();



}
