package com.scream.study.principle.ifelse.impl;

import com.scream.study.principle.ifelse.DealAmountFactory;
import com.scream.study.principle.ifelse.IDealAmount;
import com.scream.study.principle.ifelse.VipLevelEnum;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

/**
 * 特级会员
 *
 * @author 银娣
 */
@Service
public class Level0 implements IDealAmount, InitializingBean {

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
