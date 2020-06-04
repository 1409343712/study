package com.scream.study.mybatis.service.impl;

import com.scream.study.mybatis.bean.User;
import com.scream.study.mybatis.mapper.UserMapper;
import com.scream.study.mybatis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@CacheConfig(cacheNames = "userInfoCache") // 本类内方法指定使用缓存时，默认的名称就是userInfoCache
public class UserServiceImpl implements UserService {
	@Autowired
	private UserMapper userMapper;

	@Override
	// 因为必须要有返回值，才能保存到数据库中，如果保存的对象的某些字段是需要数据库生成的，
	//那保存对象进数据库的时候，就没必要放到缓存了
	@CachePut(key = "#p0.id")  //#p0表示第一个参数
	//必须要有返回值，否则没数据放到缓存中
	public User insert(User u) {
		userMapper.insert(u);
		return userMapper.getById(u.getId());//利用cacheable不支持内部方法调用的方式的特性
	}

	@CacheEvict(key = "#id")  //删除缓存名称为userInfoCache,key等于指定的id对应的缓存
	@Override
	public User delete(String id) {
		userMapper.delete(id);
		return userMapper.getById(id);
	}

	@CachePut(key = "#p0.id")
	@Override
	public void update(User u) {
		userMapper.update(u);
	}

	//	@Nullable
	@Cacheable(key = "#p0") // @Cacheable 会先查询缓存，如果命中，则不执行方法
	@Override
	public User getById(String id) {
		return userMapper.getById(id);
	}

	@CachePut(key = "AllUser")
	@Override
	public List<User> getAll() {
		return userMapper.getAll();
	}

	@Override
	public List<User> query(String name) {
		return userMapper.query(name);
	}

	//清空缓存名称为userInfoCache（看类名上的注解)下的所有缓存
	//如果数据失败了，缓存时不会清除的
	@CacheEvict(allEntries = true)
	@Override
	public void deleteAll() {

	}
}
