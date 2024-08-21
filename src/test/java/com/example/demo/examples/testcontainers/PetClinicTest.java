package com.example.demo.examples.testcontainers;


import com.codeborne.selenide.Configuration;
import com.example.demo.examples.testcontainers.pages.HomePage;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.open;

@Testcontainers
public class PetClinicTest {

    public static final String PETCLINIC_IMAGE_NAME = "springcommunity/spring-framework-petclinic:latest";
    public static int port;
    public static GenericContainer<?> petClinicContainer = new GenericContainer<>(DockerImageName.parse(PETCLINIC_IMAGE_NAME))
            .withExposedPorts(8080)
            .withStartupTimeout(Duration.ofMinutes(1));

    @BeforeClass
    static void setUp() throws Exception {
        petClinicContainer.start();
        petClinicContainer.waitingFor(Wait.forHealthcheck());
        port = petClinicContainer.getMappedPort(8080);
        petClinicContainer.waitingFor(Wait.forHttp("/").forStatusCode(200));
        Configuration.baseUrl = "http://localhost:" + port;
    }

    @Test
    public void userCanSearch() {
        open("/");
        new HomePage().goToFindOwners();
        new HomePage().goToError();
    }

    @Test
    public void userCanSearch2() {
        open("/");
        new HomePage().goToVeterinarians();
        new HomePage().goToError();
    }

}
