package com.scream.study.principle.responsibility_chain.service.impl;

import com.scream.study.principle.responsibility_chain.bean.LeaveRequest;
import com.scream.study.principle.responsibility_chain.service.AbstractLeaveHandler;
import org.springframework.stereotype.Component;

@Component
public class BossManagerLeaveHandler extends AbstractLeaveHandler {
	@Override
	public void hadnleLeaveRequestDirective(LeaveRequest request) {
		if (MIDDLE < request.getLeavaDays() && request.getLeavaDays() <= MAX) {
			System.out.println("请假天数小于等于30天，Boss处理完毕...");
			request.setIsHandled(true);
		}
	}
}
