
# Spring Boot Hello World


## Compile

Directly run

```bash
mvn spring-boot:run
```

Test
```bash
mvn test
```

Compile to jar

```bash
mvn package
java -jar target/spring-boot-*.jar
```

Browse `http://127.0.0.1:8080` and `http://127.0.0.1:8080/actuator` for status.


## Multiple SideCar mode

```
APP_CONFIG_BASE="enreka.client.serviceUrl.defaultZone=http://127.0.0.1:8761"
APP_CONFIG_APP1="sidecar.port=5000;sidecar.health-uri=http://127.0.0.1:5000/health"
java -jar target/spring-boot-*jar --sidecar
```

## References

  * [Building an Application with Spring Boot](https://spring.io/guides/gs/spring-boot/)

