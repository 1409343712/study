package com.scream.study.spring.aop.lesson2;

import com.scream.study.mybatis.bean.User;
import com.scream.study.mybatis.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;

/**
 * create by: scream
 * create time: 2020/2/2 23:25
 * description: Spring
 */
@Component
public class Aop_Lesson2_Service {

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private PersonalTransactional personalTransactional;


	/**
	 * create by: scream
	 * create time: 2020/2/2 23:46
	 * description: 通过@Transactional注解实现事务控制
	 */
	@Transactional
	public User insert_Transactional(User u) {
		try{
			userMapper.insert(u);
			System.out.println(1/0);//当出现异常时，@Transactional注解会对事务进行自动回滚
		}catch(Exception e){
			System.out.println("异常了。。。");
//			@Transactional注解作用的方法如果catch住了异常 那么@Transactional注解是无法自动进行回滚的（因为无法进入切面类@Around方法的catch）
//			解决方案有两种 1：手动回滚 2：抛出异常让Aop进行捕获处理
//			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			throw e;
		}
		return userMapper.getById(u.getId());

	}
   /**
    * create by: scream
    * create time: 2020/2/2 23:47
    * description: 通过DataSourceTransactionManager手动控制事务
    */
	public User insert_TransactionalUtils(User u) {
		TransactionStatus status = personalTransactional.begin();
		try {
			userMapper.insert(u);
			System.out.println(1/0);//当出现异常时，DataSourceTransactionManager会对事务进行回滚
			personalTransactional.commit(status);
		} catch (Exception e){
			e.printStackTrace();
			personalTransactional.rollback(status);
		}
		return userMapper.getById(u.getId());
	}
	/**
	 * create by: scream
	 * create time: 2020/2/2 23:57
	 * description: 自定义AOP切面类（也是通过DataSourceTransactionManager）手动控制事务
	 */
	public User insert_Aspect(User u) {
		userMapper.insert(u);
//		System.out.println(1/0);//当出现异常时，Aop切面类会对事务进行回滚
		return userMapper.getById(u.getId());
	}

}
