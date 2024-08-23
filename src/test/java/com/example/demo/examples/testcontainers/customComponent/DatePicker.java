package com.example.demo.examples.testcontainers.customComponent;

import com.codeborne.selenide.SelenideElement;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Selenide.$;

public class DatePicker {

    private SelenideElement inputYear() {
        return $("input[aria-label='Year']");
    }

    private SelenideElement selectMonth() {
        return $("select[aria-label='Month']");
    }


    /**
     * @param date of format June 18, 2025
     * @return the day in the datepicker component
     */
    private SelenideElement spanDay(String date) {
        return $("span[aria-label='" + date + "']");
    }

    public void selectDate(String date){
        LocalDate localDate = LocalDate.parse(date);
        inputYear().setValue(String.valueOf(localDate.getYear()));
        selectMonth().selectOptionByValue(String.valueOf(localDate.getMonthValue()-1));

        String formattedDate = localDate.format(DateTimeFormatter.ofPattern("MMMM d, yyyy"));
        spanDay(formattedDate).click();
    }
}
