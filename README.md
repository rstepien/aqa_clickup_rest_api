# A practical introduction to automated REST API testing in JAVA

In this project, I am learning about REST API automation using REST Assured and many other libraries.

## About this repository 

The code from this repository comes from the course:
[**Backend Testing School**](https://backend.akademiaqa.pl/)

## ClickUp REST API Testing Project

This Maven project is designed for testing the REST API of the ClickUp application. It covers end-to-end (E2E) scenarios including creating spaces, lists, tasks, updating tasks, closing tasks, and deleting spaces.

## Setup and Usage
1. Clone the repository to your local machine.
2. Import the project into your preferred IDE.
3. Install Maven dependencies if not downloaded automatically, check pom.xml file.
4. Sign up for a ClickUp account.
5. Explore the [**ClickUp API documentation**](https://clickup.com/api/) 
6. Generate your personal API token.
7. Prepare also `team_id`, in API documentation, team_id refers to the id of a Workspace.
8. Replace the values with the ones prepared in the previous points in the your local `/src/main/resources/clickup.properties` file.
9. Now you are ready to start testing.

## Libraries Used
1. [**Rest Assured**](https://mvnrepository.com/artifact/io.rest-assured/rest-assured): Used for making HTTP requests and validating RESTful APIs.
2. [**JUnit**](https://mvnrepository.com/artifact/org.junit.jupiter/junit-jupiter): Used for writing and running unit tests in Java.
3. [**AssertJ**](https://mvnrepository.com/artifact/org.assertj/assertj-core): Provides fluent assertions for easier test code readability.
4. [**Allure**](https://mvnrepository.com/artifact/io.qameta.allure/allure-junit5): Used for generating interactive test reports with detailed information.
5. [**Lombok**](https://mvnrepository.com/artifact/org.projectlombok/lombok): Provides annotations to reduce boilerplate code in Java classes.
6. [**Jackson**](https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-databind): Used for JSON serialization and deserialization in Java.
7. [**Hamcrest**](https://mvnrepository.com/artifact/org.hamcrest/hamcrest): Provides matchers for writing expressive and readable tests.
8. [**JavaFaker**](https://mvnrepository.com/artifact/com.github.javafaker/javafaker): Generates fake data for testing purposes.
9. [**JSON**](https://mvnrepository.com/artifact/org.json/json): Used for parsing and manipulating JSON data in Java. 
10. [**SLF4J**](https://mvnrepository.com/artifact/org.slf4j/slf4j-simple): Simple Logging Facade for Java, provides a simple facade for logging frameworks.
