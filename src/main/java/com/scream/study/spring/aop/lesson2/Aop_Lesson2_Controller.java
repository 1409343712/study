package com.scream.study.spring.aop.lesson2;

import com.scream.study.mybatis.bean.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/aop")
public class Aop_Lesson2_Controller {
	@Autowired
	private Aop_Lesson2_Service userService;

	@RequestMapping("/insert_Transactional")
	public void insert_Transactional() {
		User user = getUser();
		userService.insert_Transactional(user);
	}
	@RequestMapping("/insert_TransactionalUtils")
	public void insert_TransactionalUtils() {
		User user = getUser();
		userService.insert_TransactionalUtils(user);
	}
	@RequestMapping("/insert_Aspect")
	public User insert_Aspect() {
		User user = getUser();
		return userService.insert_Aspect(user);
	}
	private User getUser(){
		User user = new User();
		user.setId(UUID.randomUUID().toString().replace("-", ""));
		user.setName("赵冬雪");
		user.setSex("女");
		user.setAge("26");
		return user;
	}
}
