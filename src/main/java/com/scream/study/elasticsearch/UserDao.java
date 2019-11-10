package com.scream.study.elasticsearch;

import org.springframework.data.repository.CrudRepository;

public interface UserDao extends CrudRepository<UserEntity, String> {

}
