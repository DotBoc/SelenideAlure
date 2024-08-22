package com.example.demo.examples.testcontainers.containers.initializers;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.Network;
import org.testcontainers.utility.DockerImageName;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public abstract class BaseTest {


    public static final String PETCLINIC_IMAGE_NAME = "springcommunity/spring-framework-petclinic:latest";
    protected static GenericContainer<?> petclinic;
    protected static String petclinicUrl;
    protected static Network network;

    @BeforeSuite
    void setUpPetclinic() {
        network = Network.newNetwork();

        petclinic = new GenericContainer<>(DockerImageName.parse(PETCLINIC_IMAGE_NAME)).withExposedPorts(8080).withNetwork(network).withNetworkAliases("petclinic");

        petclinic.start();
        petclinicUrl = "http://" + petclinic.getHost() + ":" + petclinic.getMappedPort(8080);

        Configuration.baseUrl = petclinicUrl;

        SelenideLogger.addListener("AllureSelenide", new AllureSelenide().screenshots(true).savePageSource(true));
    }

    @AfterSuite
    public void tearDownPetclinic() {
        if (petclinic != null) {
            petclinic.stop();
        }
        if (network != null) {
            network.close();
        }
    }
}
