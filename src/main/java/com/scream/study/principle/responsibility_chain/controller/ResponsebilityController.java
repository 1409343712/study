package com.scream.study.principle.responsibility_chain.controller;

import com.scream.study.spring.ioc.SpringContextUtil;
import com.scream.study.principle.responsibility_chain.bean.LeaveRequest;
import com.scream.study.principle.responsibility_chain.service.AbstractLeaveHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/responsebilitychain")
public class ResponsebilityController {
	/**
	 * 自定义责任链顺序
	 **/
	private static final String[] CHAIN = {"directLeaderLeaveHandler", "deptManagerLeaveHandler", "bossManagerLeaveHandler"};

	@RequestMapping("/leaveRequest/{leaveDays}")
	public void leaveRequest(@PathVariable Integer leaveDays) {
		LeaveRequest leaveRequest = new LeaveRequest(leaveDays, false);
		AbstractLeaveHandler firstHandler = getFirstHandler(0);
		firstHandler.hadnleLeaveRequest(leaveRequest);
		System.out.println(leaveRequest.getIsHandled() ? "请假成功..." : "请假失败...");
	}

	/**
	 * create by: scream
	 * create time: 2020/2/4 18:00
	 * description: 拿到责任链起始位置 同时封装责任链
	 */
	private AbstractLeaveHandler getFirstHandler(Integer index) {
		AbstractLeaveHandler firstHandler = SpringContextUtil.getBean(CHAIN[index++], AbstractLeaveHandler.class);
		/**
		 * 封装责任链
		 */
		AbstractLeaveHandler tempHandler = firstHandler;
		while (index < CHAIN.length) {
			AbstractLeaveHandler nextHandler = SpringContextUtil.getBean(CHAIN[index++], AbstractLeaveHandler.class);
			tempHandler.setNextHandler(nextHandler);
			tempHandler = nextHandler;
		}
		return firstHandler;
	}
}
