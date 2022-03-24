package org.bube.distributed.locks.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.integration.jdbc.lock.DefaultLockRepository;
import org.springframework.integration.jdbc.lock.JdbcLockRegistry;
import org.springframework.integration.jdbc.lock.LockRepository;
import org.springframework.integration.redis.util.RedisLockRegistry;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class LockConf {

  @Bean
  public DefaultLockRepository defaultLockRepository(DataSource dataSource) {
    return new DefaultLockRepository(dataSource);
  }

  @Bean
  @Primary
  public JdbcLockRegistry jdbcLockRegistry(LockRepository lockRepository) {
    return new JdbcLockRegistry(lockRepository);
  }

  @Bean
//  @Primary
  public RedisLockRegistry redisLockRegistry(RedisConnectionFactory redisConnectionFactory) {
    return new RedisLockRegistry(redisConnectionFactory, "DistributedLock");
  }

}
