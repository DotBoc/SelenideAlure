package com.example.demo.examples.testcontainers.pages;

import com.codeborne.selenide.SelenideElement;
import com.example.demo.examples.testcontainers.customComponent.DatePicker;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Condition.text;
public class NewPet extends BaseElements {

    private final DatePicker datePicker = new DatePicker();

    private SelenideElement ownerName() {
        return $(".form-group > label + .col-sm-10");
    }
    private SelenideElement inputPetName() {
        return $("input[class='form-control']");
    }

    private SelenideElement inputBirthDate() {
        return $("input[id='birthDate']");
    }

    private SelenideElement selectType() {
        return $("select[id='type']");
    }

    private SelenideElement addPetButton() {
        return $("button[type='submit']");
    }

    public NewPet verifyOwnerName(String name){
        ownerName().shouldHave(text(name));
        return this;
    }

    public NewPet setPetname(String name){
        inputPetName().setValue(name);
        return this;
    }

    public NewPet setBirthDate(String birthdate){
        datePicker.selectDate(birthdate);
        return this;
    }

    public NewPet setPetType(String type){
        selectType().selectOptionByValue(type);
        return this;
    }

    public NewPet addPet(){
        addPetButton().click();
        return this;
    }

    public void addNewPet(String petName,String birthdate,String type){
        setPetname(petName).setBirthDate(birthdate).setPetType(type).addPet();
    }
}
