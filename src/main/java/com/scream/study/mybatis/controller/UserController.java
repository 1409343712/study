package com.scream.study.mybatis.controller;

import com.scream.study.mybatis.bean.User;
import com.scream.study.mybatis.service.UserService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {
	@Resource
	private UserService userService;

	/**
	 * 先从redis缓存拿 拿不到就去数据库拿
	 *
	 * @param key
	 * @return
	 */
	@RequestMapping("/getUser/{key}")
	public User getUser(@PathVariable String key) {
		User user = userService.getById(key);
		return user;
	}
	@RequestMapping("/query/{key}")
	public List<User> query(@PathVariable String key) {
		List<User> userList = userService.query(key);
		return userList;
	}

	@RequestMapping("/addUser")
	public void addUser() {
		User user = new User();
		user.setId(UUID.randomUUID().toString().replace("-", ""));
		user.setName("赵冬雪");
		user.setSex("女");
		user.setAge("26");
		userService.insert(user);
	}

	@RequestMapping("/deleteAll")
	public void deleteAll() {
		userService.deleteAll();
	}
}
