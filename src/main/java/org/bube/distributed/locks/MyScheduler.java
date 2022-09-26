package org.bube.distributed.locks;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.integration.support.locks.LockRegistry;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.locks.Lock;

@Component
@Slf4j
@RequiredArgsConstructor
public class MyScheduler {

  @Value("${server.port}")
  private int serverPort;
  private final LockRegistry lockRegistry;


  @SneakyThrows
  @Scheduled(cron = "0 50 10 * * *")
  public void scheduledTask() {
    Lock lock = lockRegistry.obtain("person");
    boolean lockAcquired = lock.tryLock();
    if (lockAcquired) {
      try {
        log.info("Running instance [{}]", serverPort);
        Thread.sleep(1000);
      } finally {
        lock.unlock();
      }
    }
  }

}
