package ru.netology.page;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
public class PaymentPage {
    private SelenideElement heading = $$("[.heading]").find(exactText("Оплата по карте"));
    private SelenideElement cardNumber= $(".input__control[placeholder='0000 0000 0000 0000']");
    private SelenideElement cardMonth= $(".input__control[placeholder='08']");
    private SelenideElement cardYear = $(".input__control[placeholder='22']");
    private SelenideElement cardMaster= $$(".input__control").get(3);
    private SelenideElement cardCVC= $(".input__control[placeholder='999']");
    private SelenideElement continueButton = $$(".button").find(exactText("Продолжить"));
    private SelenideElement failMessage = $$(".notification__title").find(exactText("Ошибка"));
    private SelenideElement successMessage= $$(".notification__title").find(exactText("Успешно"));
}
