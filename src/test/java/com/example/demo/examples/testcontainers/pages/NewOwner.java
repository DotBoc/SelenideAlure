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

    public NewOwner setFirstName(String firstName){
        firstNameInput().setValue(firstName);
        return this;
    }

    public NewOwner setLastName(String lastName){
        lastNameInput().setValue(lastName);
        return this;
    }

    public NewOwner setAddress(String address){
        addressInput().setValue(address);
        return this;
    }

    public NewOwner setCity(String city){
        cityInput().setValue(city);
        return this;
    }

    public NewOwner setTelephone(String telephone){
        telephoneInput().setValue(telephone);
        return this;
    }


    public NewOwner addOwner(){
        addOwnerButton().click();
        return this;
    }

    public void addNewOwner(String firstName,String lastName,String address,String city,String telephone){
        setFirstName(firstName).setLastName(lastName).setAddress(address).setCity(city).setTelephone(telephone).addOwner();
    }
}
