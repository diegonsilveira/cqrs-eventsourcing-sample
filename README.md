# Getting Started

## Interface do usuário do Swagger
Acesso: [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

## H2 Console
Executar a classe Servers.java para subir o banco. 

> Implementação manual para simulação de diversas instancias.

Acesso: [http://localhost:8080/h2-console](http://localhost:8080/h2-console) 
* Digite a URL JDBC: jdbc:h2:tcp://localhost:9092/mem:sample > Conectar.

## Axon Framework
Para utilizar o Axon Framework é necessário utilizar o AxonServer. Para executar o AxonServer como um contêiner de docker:  `sudo docker run -d -p 8024:8024 -p 8124:8124 -p 8224:8224 --name axonserver axoniq/axonserver`.

Disponivel em: [http://localhost:8024/](http://localhost:8024/)

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.5.0/maven-plugin/reference/html/)
* [Spring Boot DevTools](https://docs.spring.io/spring-boot/docs/2.5.0/reference/htmlsingle/#using-boot-devtools)
* [Spring Web](https://docs.spring.io/spring-boot/docs/2.5.0/reference/htmlsingle/#boot-features-developing-web-applications)
* [Spring Data JPA](https://docs.spring.io/spring-boot/docs/2.5.0/reference/htmlsingle/#boot-features-jpa-and-spring-data)

### Guides
The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/bookmarks/)
* [Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)

