# Distributed locks with SpringBoot

### Running locally

PostgresDb on port 5432 is needed for this example, you can run it with the following docker command:
```
sudo docker run -e POSTGRES_PASSWORD=1234 -p 5432:5432 -d postgres:10-alpine
```
### Further reading
Here is more info about the available LockRegistry implementations by Spring Integration:
- [JdbcLockRegistry](https://docs.spring.io/spring-integration/reference/html/jdbc.html#jdbc-lock-registry)
- [RedisLockRegistry](https://docs.spring.io/spring-integration/reference/html/redis.html#redis-lock-registry)
- [ZookeeperLockRegistry](https://docs.spring.io/spring-integration/reference/html/zookeeper.html#zk-lock-registry)

### Request examples:

- http://localhost:8080/editPerson/1?name=Jim
- http://localhost:8080/editPerson/1?age=20