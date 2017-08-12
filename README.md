# Imdb REST interface

## Description
REST interface which support searching for a given title on imdb.com. Result is in JSON format. REST endpoints are under `com.jirapave.imdb.rest.controller`. Technology stack used:

* Java 8
* Spring Boot - Core, MVC, AOP
* Lombok

## Instalation
Run `mvn clean package` to produce `imdb-rest-1.0-SNAPSHOT-jar-with-dependencies.jar` file. This file behaves as runnable jar with self-contained Apache Tomcat.

## Running
Resulting jar can be run as `java -jar imdb-rest-1.0-SNAPSHOT.jar`. After start, Swagger-ui can be found at:
[http://localhost:8080/rest/swagger-ui.html](http://localhost:8080/rest/swagger-ui.html)

You can test interface there or just call it directly on:
`http://localhost:8080/rest/imdb/title - POST - request as JSON: { title: "lost" }`

## Notes
* Since no definition of XML format was provided (XSD or DTD), I am transforming XML to JSON without "understanding" of format. If the format would be known, I would have used some DTO objects in which I would parse the XML response.
* Swagger is used as a documentation of REST API and to be able to easily test the service
* Lombok is used to have more concise code especially when creating POJO classes and logging utilities
* Exception handling in REST is done using @ControllerAdvice in RestExceptionHandlerAspect - every exception is mapped into ErrorMessageDTO and sent back
* Only simple test for service has been made, normally much larger tests would be done - more variants and for rest controllers also, not just services