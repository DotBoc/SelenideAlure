# Selenide Alure

UI testing of a Spring application using Maven, Allure Report, Selenide, Selenium Grid, TestNG and TestContainers 

recommended way to execute project for development purposes 

**mvn clean test allure:report allure:serve -D maven.test.failure.ignore=true**

## Project Composition

### [Spring PetClinic Sample Application](https://github.com/spring-petclinic/spring-framework-petclinic)
This is a Spring based Java application that utilises
  -   Spring Framework
  -   Spring MVC
  -   Thymeleaf
  -   Spring Data JPA,Hibernate,H2 Database

### Maven
Maven is used as the build and dependency management tool. You would configure the pom.xml file to include all necessary dependencies.

### Test Environment Setup

TestContainers are used to spin up as containers : 
  - The _"springcommunity/spring-framework-petclinic:latest"_ image in order for our tests to run against.
  - A selenium grid which will host our browsers which can allow execution in multiple browsers in parallel with the right configuration allowing for distributed testing.

### Test Execution

We are utilizing TestNG and [Selenide](https://selenide.org/) as a framework for our test automation using [Selenium WebDriver](https://docs.seleniumhq.org/projects/webdriver/) in the background.
