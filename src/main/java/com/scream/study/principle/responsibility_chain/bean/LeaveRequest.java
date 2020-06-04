package com.scream.study.principle.responsibility_chain.bean;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LeaveRequest {
	private Integer leavaDays;
	private Boolean isHandled;
}
