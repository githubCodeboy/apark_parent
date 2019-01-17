package com.apark.service.Interface;



import com.apark.pojo.user.User;

import java.util.List;

public interface IUserService {


    public User findById(String id);

    public List<User> getPageList();

    public List<User> getPageList_genarate();

    public void insertUser(User u);

    public void readAndWirte(User u);

    public void wirteAndRead(User u);
}
