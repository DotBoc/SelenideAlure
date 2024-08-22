package com.example.demo.examples.testcontainers.tests;


import com.codeborne.selenide.testng.TextReport;
import com.example.demo.examples.testcontainers.containers.initializers.UITestBase;
import com.example.demo.examples.testcontainers.pages.HomePage;
import io.qameta.allure.*;
import io.qameta.allure.testng.Tag;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static io.qameta.allure.SeverityLevel.CRITICAL;

@Testcontainers
@Listeners({TextReport.class})
public class PetClinicTest extends UITestBase {

    @Test
    @Epic("Pet Clinic")
    @Feature("Veterinarians")
    @Story("Veterinarian CRUD operations")
    @Description("This test adds a veterinarian using UI and asserts that addition")
    @Owner("gtrifyll")
    @Tag("UI")
    @Tag("SMK")
    @Severity(CRITICAL)
    @Issue("ERMIS-46048")
    @TmsLink("ERMIS-46032")
    public void userCanSearch() {
        new HomePage().goToFindOwners().goToError();
        new HomePage().goToFindOwners().goToError();
    }

    @Test
    @Epic("Pet Clinic")
    @Feature("Veterinarians")
    @Story("Veterinarian CRUD operations")
    @Description("This test adds a veterinarian using UI and asserts that addition")
    @Owner("gtrifyll")
    @Tag("UI")
    @Tag("SMK")
    @Severity(CRITICAL)
    @Issue("ERMIS-46048")
    @TmsLink("ERMIS-46032")
    public void veterinarianAddition() {
        new HomePage().goToVeterinarians().goToError();
    }

}
