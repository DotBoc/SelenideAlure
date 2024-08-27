package com.example.demo.examples.testcontainers.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;

public class Owners extends BaseElements {
    private SelenideElement editOwnerLink() {
        return $("a[href*='/edit']");
    }

    private SelenideElement addNewPetLink() {
        return $("a[href*='/pets/new']");
    }

    private SelenideElement ownerName() {
        return $("td[headers='name']");
    }

    private SelenideElement ownerAddress() {
        return $("td[headers='address']");
    }

    private SelenideElement ownerCity() {
        return $("td[headers='city']");
    }

    private SelenideElement ownerTelephone() {
        return $("td[headers='telephone']");
    }

    public void assertOwnerInfo(String name, String address, String city,String telephone){
        ownerName().shouldHave(exactText(name));
        ownerAddress().shouldHave(exactText(address));
        ownerCity().shouldHave(exactText(city));
        ownerTelephone().shouldHave(exactText(telephone));
    }


}
