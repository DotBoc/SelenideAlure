package com.example.demo.examples.selenide.testng;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.SeverityLevel.CRITICAL;
import static io.qameta.allure.SeverityLevel.MINOR;

import io.qameta.allure.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.codeborne.selenide.testng.TextReport;

@Listeners(TextReport.class)
public class SelenideOrgTest extends BaseTestNGTest {
    @BeforeMethod
    public void setUp() {
        TextReport.onSucceededTest = false;
        TextReport.onFailedTest = true;
        open("https://selenide.org/users.html");
    }

    @Test
    @Epic("Web interface")
    @Feature("Essential features")
    @Story("Authentication1")
    @Description("This test attempts to log into the website using a login and a password. Fails if any error happens.\n\nNote that this test does not test 2-Factor Authentication.")
    @Severity(MINOR)
    @Owner("John Doe")
    @Link(name = "Website", url = "https://dev.example.com/")
    @Issue("AUTH-123")
    @TmsLink("TMS-456")
    public void failingMethod() {
        $$("#user-tags .tag").shouldHave(sizeGreaterThan(Integer.MAX_VALUE));
        $("#missing-button").click();
    }

    @Test
    @Epic("Web interface")
    @Feature("Essential features")
    @Story("Authentication2")
    @Description("This test attempts to log into the website using a login and a password. Fails if any error happens.\n\nNote that this test does not test 2-Factor Authentication.")
    @Severity(CRITICAL)
    @Owner("John Doe")
    @Link(name = "Website", url = "https://dev.example.com/")
    @Issue("AUTH-123")
    @TmsLink("TMS-456")
    public void successfulMethod() {
        $("#user-tags .reset-filter").shouldBe(visible).shouldHave(text("all"));
        $$("#user-tags .tag").shouldHave(sizeGreaterThan(5));
    }
}