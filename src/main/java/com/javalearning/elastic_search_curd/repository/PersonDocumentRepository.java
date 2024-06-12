package com.javalearning.elastic_search_curd.repository;

import com.javalearning.elastic_search_curd.entity.PersonDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface PersonDocumentRepository extends ElasticsearchRepository<PersonDocument, String> {
}
