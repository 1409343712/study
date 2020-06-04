package com.scream.study.principle.responsibility_chain.service;

import com.scream.study.principle.responsibility_chain.bean.LeaveRequest;
import lombok.Getter;
import lombok.Setter;

public abstract class AbstractLeaveHandler {
	/**
	 * 直接领导审批天数
	 **/
	protected final Integer MIN = 3;
	/**
	 * 直接领导审批天数
	 **/
	protected final Integer MIDDLE = 10;
	/**
	 * 直接领导审批天数
	 **/
	protected final Integer MAX = 30;

	@Setter
	@Getter
	public AbstractLeaveHandler nextHandler;

	/**
	 * create by: scream
	 * create time: 2020/2/4 15:45
	 * description: 对外开放的方法
	 */
	public void hadnleLeaveRequest(LeaveRequest request) {
		handleLeaveRequestCycle(request);
	}

	/**
	 * create by: scream
	 * create time: 2020/2/4 17:42
	 * description: 责任链上需要循环调用的方法
	 */
	private void handleLeaveRequestCycle(LeaveRequest request) {
		hadnleLeaveRequestDirective(request);
		hadnleLeaveRequestAfter(request);
	}

	/**
	 * create by: scream
	 * create time: 2020/2/4 15:25
	 * description: 定义公共行为 -> 处理请假请求方法
	 */
	public abstract void hadnleLeaveRequestDirective(LeaveRequest request);

	/**
	 * create by: scream
	 * create time: 2020/2/4 17:52
	 * description: 每次执行完之后判断是否有nextHandler 如果有的话继续执行
	 */
	public void hadnleLeaveRequestAfter(LeaveRequest request) {
		if (nextHandler != null) {
			nextHandler.handleLeaveRequestCycle(request);
		}
	}

}
