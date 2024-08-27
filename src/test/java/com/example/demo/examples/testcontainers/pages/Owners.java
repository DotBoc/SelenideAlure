package com.example.demo.examples.testcontainers.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$$;

public class Owners {

    private ElementsCollection getOwners() {
        return $$(".table tbody tr");
    }

    public ElementsCollection getOwnerByPartialMatchText(String text) {
        return getOwners().filterBy(text(text));
    }

    public void assertOwner(String query,int expectedSize){
        ElementsCollection ownerByPartialMatchText = getOwnerByPartialMatchText(query);
        ownerByPartialMatchText.shouldHave(size(expectedSize));
    }

}
