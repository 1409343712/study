package com.scream.study.mybatis.service;

import com.scream.study.mybatis.bean.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserService {

	User insert(User u);

	User delete(@Param("id") String id);

	void update(User u);

	User getById(@Param("id") String id);

	List<User> getAll();

	List<User> query(@Param("name") String name);

	void deleteAll();
}
