package com.scream.study.principle.strategy.impl;

import com.scream.study.principle.strategy.DealAmountFactory;
import com.scream.study.principle.strategy.IDealAmount;
import com.scream.study.principle.strategy.VipLevelEnum;
import org.springframework.stereotype.Service;

/**
 * 2级会员
 *
 * @author 银娣
 */
@Service
public class Level2 implements IDealAmount {
	@Override
	public double getFinalAmount(double monye) {
		System.out.println("2级会员");
		return monye * 0.8;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		DealAmountFactory.registService(VipLevelEnum.LEVEL2, this);
	}
}
