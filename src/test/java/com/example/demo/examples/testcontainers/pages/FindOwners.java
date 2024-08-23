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

    public Owners searchForOwnerWithLastName(String lastName){
        lastNameInput().setValue(lastName);
        return searchForOwners();
    }

    public Owners searchForOwners(){
        findOwnerButton().click();
        return new Owners();
    }

    public NewOwner addNewOwner(){
        addOwnerLink().click();
        return new NewOwner();
    }

}
