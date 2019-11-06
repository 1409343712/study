package com.scream.study.principle.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * 观察者模式
 * 网上介绍的观察者模式 一共有四个角色  1抽象发布者 2具体发布者 3抽象订阅者 4具体订阅者 但是当发布者发布消息后通知订阅者的功能是由发布者自己完成的 例如ThirdPublisher
 * 个人认为发布者应该只负责发布消息 而订阅者只负责订阅和接收消息 订阅和发布的具体实现 应该交由具体的控制类WeChat进行实现
 * @author 银娣
 *
 */
public class Client {
	public static void main(String[] args) {
		AbstractPublisher firstPublisher = new FirstPublisher();
		AbstractPublisher secondPublisher = new SecondPublisher();
		
		AbstractSubscriber firstSubscriber = new FirstSubscriber();
		AbstractSubscriber secondSubscriber = new SecondSubscriber();
		
		firstSubscriber.subscribe(firstPublisher);
		firstSubscriber.subscribe(secondPublisher);
		
		secondSubscriber.subscribe(firstPublisher);
		
		firstPublisher.publish();
		secondPublisher.publish();
	}
}
/********************发布者**********************************/
/**
 * 抽象发布者
 * @author 银娣
 *
 */
abstract class AbstractPublisher {
	public String messgae;
	List<AbstractSubscriber> subscriberList = new ArrayList<AbstractSubscriber>();
	abstract void publish();
}
/**
 * 具体发布者1
 * @author 银娣
 *
 */
class FirstPublisher extends AbstractPublisher{
	@Override
	public void publish() {
		messgae = "房价要涨了！";
		WeChat.notify(this);
	}
	
}
/**
 * 具体发布者2
 * @author 银娣
 *
 */
class SecondPublisher extends AbstractPublisher{
	@Override
	public void publish() {
		messgae = "房价要跌了！";
		WeChat.notify(this);
	}
	
}
/**
 * 具体发布者3
 * @author 银娣
 *
 */
class ThirdPublisher extends AbstractPublisher{
	@Override
	public void publish() {
		for (AbstractSubscriber subscriber : subscriberList) {
			subscriber.response(this);
		}
	}
	
}
/********************订阅者**********************************/
/**
 * 抽象订阅者
 * @author 银娣
 *
 */
abstract class AbstractSubscriber{
	public abstract void subscribe(AbstractPublisher publisher);
	
	public abstract void response(AbstractPublisher publisher);
}
/**
 * 具体订阅者1
 * @author 银娣
 *
 */
class FirstSubscriber extends AbstractSubscriber{

	@Override
	public void response(AbstractPublisher publisher) {
		System.out.println("FirstSubscriber" + publisher.messgae);
	}

	@Override
	public void subscribe(AbstractPublisher publisher) {
		WeChat.subscribe(publisher, this);
		
	}
	
}
/**
 * 具体订阅者2
 * @author 银娣
 *
 */
class SecondSubscriber extends AbstractSubscriber{

	@Override
	public void response(AbstractPublisher publisher) {
		System.out.println("SecondSubscriber" + publisher.messgae);
	}

	@Override
	public void subscribe(AbstractPublisher publisher) {
		WeChat.subscribe(publisher, this);
	}
	
}
/**
 * 功能类 负责订阅和发布消息的具体实现
 * @author 银娣
 *
 */
class WeChat {
	public static void subscribe(AbstractPublisher publisher, AbstractSubscriber subscriber) {
		publisher.subscriberList.add(subscriber);
	}
	public static void notify(AbstractPublisher publisher) {
		List<AbstractSubscriber> subscriberList = publisher.subscriberList;
		for (AbstractSubscriber subscriber : subscriberList) {
			subscriber.response(publisher);
		}
	}
}