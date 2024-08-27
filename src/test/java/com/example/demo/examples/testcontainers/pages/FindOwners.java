package com.example.demo.examples.testcontainers.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class FindOwners extends BaseElements {

    private SelenideElement pageTitle() {
        return $("h2");
    }

    private SelenideElement lastNameInput() {
        return $("input[id='lastName']");
    }

    private SelenideElement findOwnerButton() {
        return $("button[type='submit']");
    }

    private SelenideElement addOwnerLink() {
        return $("a[class='btn btn-primary']");
    }

    public void searchForOwnerWithLastName(String lastName){
        lastNameInput().setValue(lastName);
        searchForOwners();
    }

    public void searchForOwners(){
        findOwnerButton().click();
    }

    public void addNewOwner(){
        addOwnerLink().click();
    }

}
