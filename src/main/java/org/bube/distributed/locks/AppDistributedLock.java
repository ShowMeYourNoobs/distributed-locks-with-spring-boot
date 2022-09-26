package org.bube.distributed.locks;


import lombok.AllArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@AllArgsConstructor
public class AppDistributedLock {


  public static void main(String[] args) {
    SpringApplication.run(AppDistributedLock.class, args);
  }

}
