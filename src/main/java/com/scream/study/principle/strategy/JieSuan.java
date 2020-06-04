package com.scream.study.principle.strategy;

import org.springframework.stereotype.Component;

@Component
public class JieSuan {
	
	public double jisuan(Consumer consumer, double money) {
		IDealAmount service = DealAmountFactory.getService(consumer.getVipType());
		double moneyFinal = service.getFinalAmount(money);
		System.out.println("结算后金额为" + moneyFinal);
		return moneyFinal;
	}

}
