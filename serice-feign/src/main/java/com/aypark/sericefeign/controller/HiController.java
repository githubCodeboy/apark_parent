package com.aypark.sericefeign.controller;


//在Web层的controller层，对外暴露一个”/hi”的API接口，通过上面定义的Feign客户端SchedualServiceHi 来消费服务

import com.apark.pojo.user.User;
import com.aypark.sericefeign.model.Activity;
import com.aypark.sericefeign.service.ActivityService;
import com.aypark.sericefeign.service.SchedualServiceHi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HiController {

    @Autowired
    SchedualServiceHi schedualServiceHi;

    @Autowired
    ActivityService activityService;
    @RequestMapping(value = "/hi",method = RequestMethod.GET)
    public String sayHi(@RequestParam String name){
        return schedualServiceHi.sayHiFromClientOne(name);
    }

    @RequestMapping(value = "/addUser",method = RequestMethod.GET)
    public Activity addUser(@RequestParam String name){

        return activityService.detailActivity("1");
    }

    @RequestMapping(value = "/getPageList",method = RequestMethod.GET)
    public List<User> getPageList()
    {

        return schedualServiceHi.getPageList();
    }

}
