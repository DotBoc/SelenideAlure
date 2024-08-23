package com.example.demo.examples.testcontainers.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class HomePage extends BaseElements {
    private SelenideElement pageTitle() {
        return $("h2");
    }
    private SelenideElement image() {
        return $("img");
    }

}
