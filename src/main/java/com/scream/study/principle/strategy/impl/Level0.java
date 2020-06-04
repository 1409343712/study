package com.scream.study.principle.strategy.impl;

import com.scream.study.principle.strategy.DealAmountFactory;
import com.scream.study.principle.strategy.IDealAmount;
import com.scream.study.principle.strategy.VipLevelEnum;
import org.springframework.stereotype.Service;

/**
 * 特级会员
 *
 * @author 银娣
 */
@Service
public class Level0 implements IDealAmount {

	@Override
	public double getFinalAmount(double monye) {
		System.out.println("特级会员");
		return monye * 0.5;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		DealAmountFactory.registService(VipLevelEnum.LEVEL0, this);
	}

}
