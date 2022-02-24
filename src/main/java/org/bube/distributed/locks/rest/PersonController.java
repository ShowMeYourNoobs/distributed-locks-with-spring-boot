package org.bube.distributed.locks.rest;

import lombok.AllArgsConstructor;
import org.bube.distributed.locks.repo.Person;
import org.bube.distributed.locks.service.PersonService;
import org.springframework.http.HttpStatus;
import org.springframework.integration.support.locks.LockRegistry;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

@RestController
@AllArgsConstructor
public class PersonController {

  private PersonService personService;
  private LockRegistry lockRegistry;

  @GetMapping("/editPerson/{id}")
  public Person editPerson(@PathVariable Long id,
                           @RequestParam(required = false) String name,
                           @RequestParam(required = false) Integer age) throws InterruptedException {

    Lock lock = lockRegistry.obtain("PersonLock");
    boolean lockAcquired = lock.tryLock(1, TimeUnit.SECONDS);
    if (lockAcquired) {
      try {
        TimeUnit.SECONDS.sleep(10);
        return personService.editPerson(id, name, age);
      } finally {
        lock.unlock();
      }
    }
    throw new ResponseStatusException(HttpStatus.LOCKED, "This end-point is locked at the moment");
  }

}
