package org.bube.distributed.locks.service;

import lombok.AllArgsConstructor;
import org.bube.distributed.locks.repo.Person;
import org.bube.distributed.locks.repo.PersonRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
@AllArgsConstructor
public class PersonService {

  private PersonRepository personRepository;

  public Person editPerson(Long id, String name, Integer age) throws InterruptedException {
    Optional<Person> personOptional = personRepository.findById(id);
    if (personOptional.isEmpty()) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Person with id: %s was not found", id));
    }

    TimeUnit.SECONDS.sleep(20); // Simulate heavy operation
    Person person = personOptional.get();
    if (name != null && !name.isEmpty()) {
      person.setName(name);
    }
    if (age != null && age != 0) {
      person.setAge(age);
    }

    return personRepository.save(person);
  }
}
