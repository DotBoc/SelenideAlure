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

    public HomePage goToHome(){
        homeLink().click();
        return new HomePage();
    }

    public FindOwners goToFindOwners(){
        findOwnersLink().click();
        return new FindOwners();
    }

    public BaseElements goToVeterinarians(){
        veterinariansLink().click();
        return this;
    }

    public BaseElements goToError(){
        errorLink().click();
        return this;
    }


}
