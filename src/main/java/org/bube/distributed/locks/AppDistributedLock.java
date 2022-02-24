package org.bube.distributed.locks;


import lombok.AllArgsConstructor;
import org.bube.distributed.locks.repo.Person;
import org.bube.distributed.locks.repo.PersonRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
@AllArgsConstructor
public class AppDistributedLock {

  private PersonRepository personRepository;

  public static void main(String[] args) {
    SpringApplication.run(AppDistributedLock.class, args);
  }

  @PostConstruct
  public void initDb() {
    Person p1 = Person.builder().name("Jimmy").age(30).build();
    Person p2 = Person.builder().name("Jessica").age(26).build();
    personRepository.save(p1);
    personRepository.save(p2);
  }

}
