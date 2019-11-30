package com.scream.study;

import com.scream.study.elasticsearch.UserEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;

@SpringBootTest
class StudyApplicationTests {
	@Autowired
	private ElasticsearchTemplate esTemplate;
	@Test
	void createIndex() {
		esTemplate.createIndex(UserEntity.class);
	}

}
