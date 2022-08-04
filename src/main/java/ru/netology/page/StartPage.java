package ru.netology.page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class StartPage {
    private SelenideElement heading = $("[h2.heading]");
    private SelenideElement buttonPayment = $$(".button").find(exactText("Купить"));
    private SelenideElement buttonCredit = $$(".button").find(exactText("Купить в кредит"));
    public StartPage(){
        heading.shouldBe(visible);
    }

}
