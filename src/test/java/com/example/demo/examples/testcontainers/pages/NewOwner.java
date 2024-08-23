package com.example.demo.examples.testcontainers.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class NewOwner {

    private SelenideElement pageTitle() {
        return $("h2");
    }

    private SelenideElement firstNameInput() {
        return $("input[id='firstName']");
    }

    private SelenideElement lastNameInput() {
        return $("input[id='lastName']");
    }

    private SelenideElement addressInput() {
        return $("input[id='address']");
    }

    private SelenideElement cityInput() {
        return $("input[id='city']");
    }

    private SelenideElement telephoneInput() {
        return $("input[id='telephone']");
    }

    private SelenideElement addOwnerButton() {
        return $("button[type='submit']");
    }
}
