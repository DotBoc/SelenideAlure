package com.example.demo.examples.testcontainers.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.utility.DockerImageName;
import org.testng.annotations.*;

import java.time.Duration;

import static com.codeborne.selenide.AssertionMode.STRICT;
import static com.codeborne.selenide.Selenide.open;

abstract class BaseTest {

    private static final Logger log = LoggerFactory.getLogger(BaseTest.class);

    public static final String PETCLINIC_IMAGE_NAME = "springcommunity/spring-framework-petclinic:latest";
    public static int port;
    public static String hostname;
    public static GenericContainer<?> petClinicContainer = new GenericContainer<>(DockerImageName.parse(PETCLINIC_IMAGE_NAME))
            .withExposedPorts(8080)
            .withStartupTimeout(Duration.ofMinutes(1));
    @BeforeSuite
    static void beforeSuite() throws Exception {
        petClinicContainer.start();
        port = petClinicContainer.getMappedPort(8080);
        hostname = petClinicContainer.getHost();
        petClinicContainer.waitingFor(Wait.forHttp("/").forStatusCode(200));
        petClinicContainer.waitingFor(Wait.forHealthcheck());
        Configuration.baseUrl =  "http://" + hostname +":"+ port;

        SelenideLogger.addListener("AllureSelenide", new AllureSelenide()
                .screenshots(true)
                .savePageSource(true)
        );
    }

    @BeforeClass
    final void beforeClass() {
        log.info("Start {} TestNG tests in {}", getClass().getName(), Configuration.browser);
    }

    @BeforeMethod
    public void beforeMethod() {
        Configuration.timeout = 1;
        Configuration.assertionMode = STRICT;
        open("/");
    }

    @AfterClass
    final void afterClass() {
        log.info("Finished {} TestNG tests in {}", getClass().getName(), Configuration.browser);
    }
}
