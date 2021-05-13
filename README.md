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

![image](https://user-images.githubusercontent.com/338921/118195750-cb203600-b419-11eb-8211-87d8a7a336cc.png)

Note that all of the REST APIs are relative to the context `firstbase`.

---

In addition to the given requirements, there is an additional REST API that interacts with the remote "random user" API, exposing a downsampled version of the remote JSON responses. The API may be used to generate any number of random users. There is also an additional method `bootstrapEmployees` which pulls some number of random users from the remote service and turns them into `Employee` entities stored in the database. This is handy for testing the search functions interactively.
