package com.scream.study.elasticsearch;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface UserDao extends ElasticsearchRepository<UserEntity, Long> {

}
