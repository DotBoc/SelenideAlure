package com.example.demo.examples.selenide.google;

import com.codeborne.selenide.Screenshots;
import com.codeborne.selenide.Selenide;
import com.google.common.io.Files;
import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;


public class GoogleTest {

    @Test
    public void userCanSearch() {
        open("https://duckduckgo.com");
        new GooglePage().searchFor("selenide java");

        SearchResultsPage results = new SearchResultsPage();
        results.getResults().shouldHave(sizeGreaterThan(1));
        results.getResult(0).shouldHave(text("Selenide: concise UI tests in Java"));
    }

    @Test
    public void search_selenide_in_google() {
        open("https://duckduckgo.com");
        $(By.name("q")).val("selenide java").pressEnter();
        $$("[data-testid=\"result\"]").shouldHave(sizeGreaterThan(1));
        $("[data-testid=\"result\"]").shouldBe(visible).shouldHave(text("Selenide: concise UI tests in Java"), text("selenide.org"));
    }

    @AfterTest
    public void tearDown() throws IOException {
        Allure.addAttachment(Selenide.webdriver().driver().url(),new ByteArrayInputStream(Objects.requireNonNull(Selenide.screenshot(OutputType.BYTES))));
    }

    @Attachment(type = "image/png")
    public byte[] screenshot() throws IOException {
        File screenshot = Screenshots.getLastScreenshot();
        return screenshot == null ? Selenide.screenshot(OutputType.BYTES) : Files.toByteArray(screenshot);
    }
}
