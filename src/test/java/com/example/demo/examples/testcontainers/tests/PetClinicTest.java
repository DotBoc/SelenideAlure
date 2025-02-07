package com.example.demo.examples.testcontainers.tests;


import com.codeborne.selenide.testng.TextReport;
import com.example.demo.examples.testcontainers.containers.initializers.UIBaseConfig;
import com.example.demo.examples.testcontainers.pages.HomePage;
import com.example.demo.examples.testcontainers.pages.NewOwner;
import com.example.demo.examples.testcontainers.pages.Owner;
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
    @io.qameta.allure.Owner("gtrifyll")
    @Tag("UI")
    @Tag("SMK")
    @Severity(CRITICAL)
    @Issue("JiraId-46048")
    @TmsLink("JiraId-46032")
    public void userCanCreateOwners() {
        Allure.getLifecycle().updateTestCase(result -> result.setName("User can Create Owners"));
        new HomePage().goToFindOwners().addNewOwner();
        new NewOwner().addNewOwner("testFirst","testLast","testAdd","testCity","1234567890");
        new Owner().assertOwnerInfo("testFirst testLast","testAdd","testCity","1234567890");
    }

    @Test(groups = {"petClinic", "UI"})
    @Epic("Pet Clinic")
    @Feature("Owners")
    @Story("Owners CRUD operations")
    @Description("This test reads all available owners")
    @io.qameta.allure.Owner("gtrifyll")
    @Tag("UI")
    @Tag("SMK")
    @Severity(CRITICAL)
    @Issue("JiraId-46048")
    @TmsLink("JiraId-46032")
    public void userCanReadOwners() {
        Allure.getLifecycle().updateTestCase(result -> result.setName("User can Read Owners"));
        new HomePage().goToFindOwners().searchForOwners();
        new Owners().assertOwner("Lucky",2);
        new Owners().assertOwner("Eduardo Rodriquez 	2693 Commerce St. 	McFarland 	6085558763 	Jewel Rosy",1);
    }

    @Test(groups = {"petClinic", "UI"})
    @Epic("Pet Clinic")
    @Feature("Owners")
    @Story("Owners CRUD operations")
    @Description("This test adds an owner using UI and asserts that addition")
    @io.qameta.allure.Owner("gtrifyll")
    @Tag("UI")
    @Tag("SMK")
    @Severity(CRITICAL)
    @Issue("JiraId-46048")
    @TmsLink("JiraId-46032")
    public void failOnPurpose() {
        Allure.getLifecycle().updateTestCase(result -> result.setName("This will fail"));
        new HomePage().goToFindOwners().addNewOwner();
        new NewOwner().addNewOwner("testFirst", "testLast", "testAdd", "testCity", "1234567890");
        new Owner().assertOwnerInfo("testFirst testLast", "testAdd", "testCity", "test");
    }


}
