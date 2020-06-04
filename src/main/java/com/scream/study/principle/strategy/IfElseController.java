package com.scream.study.principle.strategy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("ifelse")
public class IfElseController {

	@Autowired
	private JieSuan jieSuan;
	@RequestMapping("test/{vipType}/{money}")
	public void test(@PathVariable String vipType, @PathVariable double money) {
		Consumer consumer = new Consumer();
		consumer.setVipType(vipType);
		jieSuan.jisuan(consumer, money);
	}
}
