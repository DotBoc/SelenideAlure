package com.example.demo.examples.testcontainers.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class BaseElements {

    private SelenideElement homeLink() {
        return $("a[title='home page']");
    }

    private SelenideElement findOwnersLink() {
        return $("a[title='find owners']");
    }

    private SelenideElement veterinariansLink() {
        return $("a[title='veterinarians']");
    }

    private SelenideElement errorLink() {
        return $("a[title='trigger a RuntimeException to see how it is handled']");
    }

    public void goToHome(){
        homeLink().click();
    }

    public void goToFindOwners(){
        findOwnersLink().click();
    }

    public void goToVeterinarians(){
        veterinariansLink().click();
    }

    public void goToError(){
        errorLink().click();
    }


}
