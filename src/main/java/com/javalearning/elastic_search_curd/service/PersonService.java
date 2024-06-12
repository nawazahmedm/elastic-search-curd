package com.javalearning.elastic_search_curd.service;

import com.javalearning.elastic_search_curd.entity.Person;
import com.javalearning.elastic_search_curd.entity.PersonDocument;
import com.javalearning.elastic_search_curd.repository.PersonDocumentRepository;
import com.javalearning.elastic_search_curd.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private PersonDocumentRepository personDocumentRepository;

    @Transactional
    public Person savePerson(Person person) {
        // Check if person exists in the database
        Optional<Person> existingPerson = personRepository.findById(person.getId());
        if (existingPerson.isPresent()) {
            // Update existing person
            Person updatedPerson = existingPerson.get();
            updatedPerson.setName(person.getName());
            updatedPerson.setEmail(person.getEmail());

            // Save updated person to relational database
            personRepository.save(updatedPerson);

            // Save updated person to Elasticsearch
            PersonDocument personDocument = new PersonDocument();
            personDocument.setId(updatedPerson.getId().toString());
            personDocument.setName(updatedPerson.getName());
            personDocument.setEmail(updatedPerson.getEmail());
            personDocumentRepository.save(personDocument);

            return updatedPerson;
        } else {
            // Insert new person
            Person newPerson = personRepository.save(person);

            // Save new person to Elasticsearch
            PersonDocument personDocument = new PersonDocument();
            personDocument.setId(newPerson.getId().toString());
            personDocument.setName(newPerson.getName());
            personDocument.setEmail(newPerson.getEmail());
            personDocumentRepository.save(personDocument);

            return newPerson;
        }
    }

    public Optional<Person> findPersonById(Long id) {
        return personRepository.findById(id);
    }

    public Iterable<Person> findAllPersons() {
        return personRepository.findAll();
    }

    @Transactional
    public void deletePerson(Long id) {
        // Delete from relational database
        personRepository.deleteById(id);

        // Delete from Elasticsearch
        personDocumentRepository.deleteById(id.toString());
    }
}
