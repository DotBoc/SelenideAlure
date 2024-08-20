package com.example.demo.examples.selenide.google;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

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
}
