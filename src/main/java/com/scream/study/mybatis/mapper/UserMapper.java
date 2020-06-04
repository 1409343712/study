package com.scream.study.mybatis.mapper;

import com.scream.study.mybatis.bean.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface UserMapper {

	@Insert("insert into user(id,name,sex,age) values(#{id},#{name},#{sex},#{age})")
	void insert(User u);

	@Delete("delete from user where id=#{id} ")
	void delete(@Param("id") String id);

	@Update("update user set name = #{name} where id=#{id} ")
	void update(User u);

	@Select("select id,name,sex,age from user where id=#{id} ")
	User getById(@Param("id") String id);

	@Select("select id,name,sex,age from user where id=#{id} ")
	List<User> getAll();

	//注：方法名和要UserMapper.xml中的id一致
	List<User> query(@Param("name") String name);

	@Delete("delete from user")
	void deleteAll();
}
