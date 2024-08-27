package com.example.demo.examples.testcontainers.tests;


import com.codeborne.selenide.testng.TextReport;
import com.example.demo.examples.testcontainers.containers.initializers.UIBaseConfig;
import com.example.demo.examples.testcontainers.pages.HomePage;
import com.example.demo.examples.testcontainers.pages.NewOwner;
import com.example.demo.examples.testcontainers.pages.Owners;
import io.qameta.allure.*;
import io.qameta.allure.testng.Tag;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static io.qameta.allure.SeverityLevel.CRITICAL;

@Listeners({TextReport.class})
public class PetClinicTest extends UIBaseConfig {

    @Test(groups = {"petClinic", "UI"})
    @Epic("Pet Clinic")
    @Feature("Owners")
    @Story("Owners CRUD operations")
    @Description("This test adds an owner using UI and asserts that addition")
    @Owner("gtrifyll")
    @Tag("UI")
    @Tag("SMK")
    @Severity(CRITICAL)
    @Issue("ERMIS-46048")
    @TmsLink("ERMIS-46032")
    public void userCanCreateOwners() {
        new HomePage().goToFindOwners().addNewOwner();
        new NewOwner().addNewOwner("testFirst","testLast","testAdd","testCity","1234567890");
        new Owners().assertOwnerInfo("testFirst testLast","testAdd","testCity","1234567890");
    }



    @Test(groups = {"petClinic", "UI"})
    @Epic("Pet Clinic")
    @Feature("Owners")
    @Story("Owners CRUD operations")
    @Description("This test reads all available owners")
    @Owner("gtrifyll")
    @Tag("UI")
    @Tag("SMK")
    @Severity(CRITICAL)
    @Issue("ERMIS-46048")
    @TmsLink("ERMIS-46032")
    public void userCanReadOwners() {
        new HomePage().goToFindOwners().addNewOwner();
        new NewOwner().addNewOwner("testFirst","testLast","testAdd","testCity","1234567890");
        new Owners().assertOwnerInfo("testFirst testLast","testAdd","testCity","1234567890");
    }

    @Test(groups = {"petClinic", "UI"})
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
    public void userCanAddNewOwners() {
        new HomePage().goToFindOwners().addNewOwner();
    }

    @Test(groups = {"petClinic", "UI"})
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
