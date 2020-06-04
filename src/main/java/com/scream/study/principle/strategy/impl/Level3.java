package com.scream.study.principle.strategy.impl;

import com.scream.study.principle.strategy.DealAmountFactory;
import com.scream.study.principle.strategy.IDealAmount;
import com.scream.study.principle.strategy.VipLevelEnum;
import org.springframework.stereotype.Service;

/**
 * 三级会员
 *
 * @author 银娣
 */
@Service
public class Level3 implements IDealAmount {
	@Override
	public double getFinalAmount(double monye) {
		System.out.println("3级会员");
		return monye * 0.9;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		DealAmountFactory.registService(VipLevelEnum.LEVEL3, this);
	}
}
