package com.javalearning.elastic_search_curd.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "person")
@Data
public class PersonDocument {
    @Id
    private String id;
    private String name;
    private String email;
}
