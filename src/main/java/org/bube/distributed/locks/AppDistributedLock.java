package org.bube.distributed.locks;


import lombok.AllArgsConstructor;
import org.bube.distributed.locks.repo.PersonRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@AllArgsConstructor
public class AppDistributedLock {

  private PersonRepository personRepository;

  public static void main(String[] args) {
    SpringApplication.run(AppDistributedLock.class, args);
  }

}
