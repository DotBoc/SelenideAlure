package com.example.demo.examples.testcontainers.containers.initializers;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import lombok.extern.slf4j.Slf4j;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.Network;
import org.testcontainers.utility.DockerImageName;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

@Slf4j
public class BaseConfig {

    public static final String PETCLINIC_IMAGE_NAME = "springcommunity/spring-framework-petclinic:latest";
    public static final String PETCLINIC_NETWORK_ALIAS = "petclinic";
    public static GenericContainer<?> petclinic;
    public static String externalPetclinicUrl;
    public static String internalPetClinicUrl;
    public static Network network;

    @BeforeSuite(alwaysRun = true)
    public void setUpPetclinic() {
        network = Network.newNetwork();

        petclinic = new GenericContainer<>(DockerImageName.parse(PETCLINIC_IMAGE_NAME)).withExposedPorts(8080).withNetwork(network).withNetworkAliases(PETCLINIC_NETWORK_ALIAS);

        petclinic.start();
        externalPetclinicUrl = "http://" + petclinic.getHost() + ":" + petclinic.getMappedPort(8080);
        internalPetClinicUrl = "http://" + PETCLINIC_NETWORK_ALIAS + ":8080";

        Configuration.baseUrl = internalPetClinicUrl;

        log.info("External petclinicUrl : " + externalPetclinicUrl);
        log.info("Internal petclinicUrl : " + internalPetClinicUrl);
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide().screenshots(true).savePageSource(true));
    }

    @AfterSuite(alwaysRun = true)
    public void tearDownPetclinic() {
        if (petclinic != null) {
            petclinic.stop();
        }
        if (network != null) {
            network.close();
        }
    }
}
