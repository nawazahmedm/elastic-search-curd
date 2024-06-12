package com.javalearning.elastic_search_curd.controller;

import com.javalearning.elastic_search_curd.entity.Person;
import com.javalearning.elastic_search_curd.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

import java.util.Optional;

@RestController
@RequestMapping("/api/persons")
@Tag(name = "Person", description = "API for managing persons")
public class PersonController {

    @Autowired
    private PersonService personService;

    @Operation(summary = "Save a person")
    @PostMapping
    public ResponseEntity<Person> savePerson(@RequestBody Person person) {
        Person savedPerson = personService.savePerson(person);
        return new ResponseEntity<>(savedPerson, HttpStatus.OK);
    }

    @Operation(summary = "Get a person by ID")
    @GetMapping("/{id}")
    public ResponseEntity<Person> getPersonById(@PathVariable Long id) {
        Optional<Person> person = personService.findPersonById(id);
        return person.map(ResponseEntity::ok).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Operation(summary = "Get all persons")
    @GetMapping
    public ResponseEntity<Iterable<Person>> getAllPersons() {
        Iterable<Person> persons = personService.findAllPersons();
        return new ResponseEntity<>(persons, HttpStatus.OK);
    }

    @Operation(summary = "Delete a person by ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePerson(@PathVariable Long id) {
        personService.deletePerson(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
