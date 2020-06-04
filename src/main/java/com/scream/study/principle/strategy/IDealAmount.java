package com.scream.study.principle.strategy;

import org.springframework.beans.factory.InitializingBean;

public interface IDealAmount extends InitializingBean {

	double getFinalAmount(double money);
}
