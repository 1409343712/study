package com.scream.study.principle.strategy;
/**
 * 消费者类
 * @author 银娣
 *
 */
public class Consumer {
	/**
	 * 用户名
	 */
	private String vipName;
	/**
	 * 会员类型
	 * 3级3 2级2  1级1 特级0
	 */
	private String vipType;

	public String getVipName() {
		return vipName;
	}

	public void setVipName(String vipName) {
		this.vipName = vipName;
	}

	public String getVipType() {
		return vipType;
	}

	public void setVipType(String vipType) {
		this.vipType = vipType;
	}
	
	
}
