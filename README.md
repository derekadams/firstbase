# Firstbase Coding Challenge
This project is a Spring Boot application which implements the requirements set forth in the Firstbase Coding Challenge.

## Building/Testing the Project
After downloading the repository execute the following command to build the project and run unit tests:

```
gradle clean test
```

## Running the Project and Accessing Swagger User Interface
Run the application by executing the following command:

```
gradle clean bootRun
```

This will start the Spring Boot application which includes a Swagger UI wrapper that allows the APIs to be manipulated manually. In order to access the Swagger UI, access the following URL:

[http://localhost:8080/firstbase/swagger-ui/index.html](http://localhost:8080/firstbase/swagger-ui/index.html)

Note that all of the REST APIs are relative to the context `firstbase`.