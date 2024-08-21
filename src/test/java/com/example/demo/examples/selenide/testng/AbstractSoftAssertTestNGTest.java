package com.example.demo.examples.selenide.testng;

import com.codeborne.selenide.Screenshots;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.codeborne.selenide.testng.SoftAsserts;
import com.codeborne.selenide.testng.TextReport;
import com.google.common.io.Files;
import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import io.qameta.allure.selenide.AllureSelenide;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;

@Listeners({SoftAsserts.class, TextReport.class})
abstract class AbstractSoftAssertTestNGTest {
    @BeforeMethod
    final void openBrowser() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide().screenshots(true).savePageSource(true));
        closeWebDriver();
        open("https://selenide.org/users.html");
    }

    @AfterMethod
    public void tearDown() throws IOException {
        Allure.addAttachment(Selenide.webdriver().driver().url(),new ByteArrayInputStream(screenshot()));
    }

    @Attachment(type = "image/png")
    public byte[] screenshot() throws IOException {
        File screenshot = Screenshots.getLastScreenshot();
        return screenshot == null ? null : Files.toByteArray(screenshot);
    }
}
