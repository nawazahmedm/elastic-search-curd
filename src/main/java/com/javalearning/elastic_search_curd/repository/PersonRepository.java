package com.javalearning.elastic_search_curd.repository;

import com.javalearning.elastic_search_curd.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
