package com.scream.study.principle.ifelse;

public enum VipLevelEnum {

	LEVEL3("3"),
	LEVEL2("2"),
	LEVEL1("1"),
	LEVEL0("0");
	
	private String code;
	
	VipLevelEnum(String code){
		this.code = code;
	}
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	
}
