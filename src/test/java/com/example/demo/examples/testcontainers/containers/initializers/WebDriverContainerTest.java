package com.example.demo.examples.testcontainers.containers.initializers;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

public interface WebDriverContainerTest {

    @BeforeSuite
    void startContainers();

    @AfterSuite
    void stopContainer();

}
