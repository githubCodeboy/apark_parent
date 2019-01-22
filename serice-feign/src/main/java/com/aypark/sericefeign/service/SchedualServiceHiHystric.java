package com.aypark.sericefeign.service;

import com.apark.pojo.user.User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Component
public class SchedualServiceHiHystric implements SchedualServiceHi {
    @Override
    public String sayHiFromClientOne(String name) {
        return "sorry "+name;
    }

    @Override
    public String addUser(){
        return " addUser  fail " ;
    }

    @Override
    public List<User> getPageList(){
        return  null ;
    }

}