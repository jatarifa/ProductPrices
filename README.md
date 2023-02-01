# 👋 ProductPrices

Spring-boot REST microservice maven project to illustrate how to structure an hexagonal architecture project.

## ⚙️ Requirements

- JDK8.

## 📘 Technologies

### Common

| Technology | Purpose |
| ---------- |----------|
|Hexagonal architecture| I used the clean architecture approach (aka. hexagonal architecture), dividing the code into their typical architecture layers, namedly domain, application and infrastructure. |
| Domain Driven Design | The model layer is built using the tactical DDD patterns, like Entities, ValueObjects, Factories and Repositories. |
| Spring-boot | To create simpler microservice projects using Spring REST. |
| Maven Wrapper | Instead of require a proper installation of a Maven version in the operating system, this project uses this script to use an specific maven version, which is downloaded and used transparently. |

### H2 adapter

| Technology | Purpose |
| ---------- |----------|
| H2Database | Very fast, open source, JDBC API which provides an embedded in-memory database. |
| Spring JDBC | Due to the simplicity of the project I decided not use any JPA framework and use a more lightweight approach based in plain JDBC SQLs. |

### REST adapter

| Technology | Purpose |
| ---------- |----------|
| SpringFox Swagger | To automate the generation of API documentation throught the SwaggerUI interface |

## 🚀 How to execute the application

Go to the project root directory and execute the following command to compile and test the project.

```shell
./mvnw clean verify
```

After creating all artifacts you can run the project with the following command:

```shell
./mvnw spring-boot:run
```

Once the application is running it will expose the following endpoint accessible by any web browser:

- [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html): Swagger interface that helps you to test the `ProductPrices` endpoints.


### Possible improvements

- API REST definition in a more formal format as OpenAPI spec.
- Based on the OpenAPI spec, the generation of the API documentation, server interfaces and related beans using the automatic server code generator provided by OpenAPI.
- Provide the means to run the application in a dockerized environment, simplifying the steps to build and run the application by using a simple docker-compose command.
