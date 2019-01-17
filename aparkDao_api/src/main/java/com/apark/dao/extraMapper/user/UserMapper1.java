package com.apark.dao.extraMapper.user;


import com.apark.pojo.user.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;


@Mapper
public interface UserMapper1 {

	@Insert("insert user(id,name) values(#{id},#{name})")
	int insert(User u);
	
	@Select("select id,name from user where id=#{id} ")
	User findById(@Param("id") String id);
	
	//注：方法名和要UserMapper.xml中的id一致
	@Select("select id,name from user   ")
	List<User> getPageList();
	
}
