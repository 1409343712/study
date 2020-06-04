package com.scream.study.principle.responsibility_chain.service.impl;

import com.scream.study.principle.responsibility_chain.bean.LeaveRequest;
import com.scream.study.principle.responsibility_chain.service.AbstractLeaveHandler;
import org.springframework.stereotype.Component;

@Component
public class DeptManagerLeaveHandler extends AbstractLeaveHandler {
	@Override
	public void hadnleLeaveRequestDirective(LeaveRequest request) {
		if (MIN < request.getLeavaDays() && request.getLeavaDays() <= MIDDLE) {
			System.out.println("请假天数小于等于10天，直接主管处理完毕...");
			request.setIsHandled(true);
		}
	}
}
