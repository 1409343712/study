package com.scream.study.spring.aop.lesson2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.interceptor.DefaultTransactionAttribute;

@Component
public class PersonalTransactional {
	//TransactionAspectSupport currentTransactionStatus().setRollbackOnly();
	/**
	 * 获取当前事务管理器
	 */
	@Autowired
	private DataSourceTransactionManager dataSourceTransactionManager;

	public TransactionStatus begin() {
		TransactionStatus transaction = dataSourceTransactionManager.getTransaction(new DefaultTransactionAttribute());
		System.out.println("获取当前的事务>>>>>");
		return transaction;
	}

	/**
	 * 提交事务
	 */
	public void commit(TransactionStatus transactionStatus) {
		System.out.println("提交当前的事务>>>>>");
		dataSourceTransactionManager.commit(transactionStatus);
	}

	public void rollback(TransactionStatus transactionStatus) {
		System.out.println("回滚当前的事务>>>>>");
		dataSourceTransactionManager.rollback(transactionStatus);
	}


}
