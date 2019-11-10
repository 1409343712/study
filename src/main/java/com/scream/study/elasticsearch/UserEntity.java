package com.scream.study.elasticsearch;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "myindex", type = "user")
@Data
public class UserEntity {
    @Id
    private String id;

    private String name;

    private String sex;

    private Integer age;
}
