# Distributed locks with SpringBoot

#### PostgresDb

PostgresDb on port 5432 is needed for this example, you can run it with the following docker command:
```
docker run -e POSTGRES_PASSWORD=1234 -p 5432:5432 -d postgres:10-alpine
```

Also, a table called int_lock is needed:

```
CREATE TABLE INT_LOCK  (
    LOCK_KEY CHAR(36),
    REGION VARCHAR(100),
    CLIENT_ID CHAR(36),
    CREATED_DATE TIMESTAMP NOT NULL,
    constraint INT_LOCK_PK primary key (LOCK_KEY, REGION)
);
```

#### Redis

```
docker run -d \
  -h redis \
  -e REDIS_PASSWORD=1234 \
  -p 6379:6379 \
  --name redis \
  --restart always \
  redis:5-alpine /bin/sh -c 'redis-server --appendonly yes --requirepass ${REDIS_PASSWORD}'
```

### Example shared resource

Example db table called "person" with a couple of pre-inserted rows (shared resources):

```
CREATE TABLE person (
	id serial PRIMARY KEY,
	name VARCHAR ( 50 ),
	age INT
);
INSERT INTO person (name, age) 
VALUES('Jim',30);
INSERT INTO person (name, age) 
VALUES('Jess',26);
```

### Further reading
Here is more info about the available LockRegistry implementations by Spring Integration:
- [JdbcLockRegistry](https://docs.spring.io/spring-integration/reference/html/jdbc.html#jdbc-lock-registry)
- [RedisLockRegistry](https://docs.spring.io/spring-integration/reference/html/redis.html#redis-lock-registry)
- [ZookeeperLockRegistry](https://docs.spring.io/spring-integration/reference/html/zookeeper.html#zk-lock-registry)

### Testing examples:

- http://localhost:8081/editPerson/1?name=Jim
- http://localhost:8082/editPerson/1?age=20
- http://localhost:8083/editPerson/1?name=John