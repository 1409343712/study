package com.scream.study.principle.strategy;

import java.util.HashMap;
import java.util.Map;

public class DealAmountFactory {

	private static Map<String, IDealAmount> serviceMap = new HashMap<>();
	
	public static IDealAmount getService(String vipLevel) {
		return serviceMap.get(vipLevel);
	}
	public static  void registService(VipLevelEnum vipLevel, IDealAmount dealAmount) {
		serviceMap.put(vipLevel.getCode(), dealAmount);
	}
}
