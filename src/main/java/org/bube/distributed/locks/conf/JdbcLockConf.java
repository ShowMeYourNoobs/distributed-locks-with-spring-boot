package org.bube.distributed.locks.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.integration.jdbc.lock.DefaultLockRepository;
import org.springframework.integration.jdbc.lock.JdbcLockRegistry;
import org.springframework.integration.jdbc.lock.LockRepository;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class JdbcLockConf {

  @Bean
  public DefaultLockRepository defaultLockRepository(DataSource dataSource) {
    return new DefaultLockRepository(dataSource);
  }

  @Bean
  public JdbcLockRegistry jdbcLockRegistry(LockRepository lockRepository) {
    return new JdbcLockRegistry(lockRepository);
  }

}
