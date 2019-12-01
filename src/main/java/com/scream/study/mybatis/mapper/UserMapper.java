package com.scream.study.mybatis.mapper;

import com.scream.study.mybatis.bean.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface UserMapper {

	@Insert("insert into user(id,name) values(#{id},#{name})")
	void insert(User u);

	@Update("update user set name = #{name} where id=#{id} ")
	void update(User u);

	@Delete("delete from user where id=#{id} ")
	void delete(@Param("id") String id);

	@Select("select id,name from user where id=#{id} ")
	User find(@Param("id") String id);

	//注：方法名和要UserMapper.xml中的id一致
	List<User> query(@Param("name") String name);

	@Delete("delete from user")
	void deleteAll();
}
