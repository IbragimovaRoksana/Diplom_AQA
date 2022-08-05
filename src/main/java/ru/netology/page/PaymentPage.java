package ru.netology.page;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.SelenideElement;
import ru.netology.data.CardInfo;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class PaymentPage {
    private SelenideElement heading = $$("[.heading]").find(exactText("Оплата по карте"));
    private SelenideElement cardNumber = $(".input__control[placeholder='0000 0000 0000 0000']");
    private SelenideElement cardMonth = $(".input__control[placeholder='08']");
    private SelenideElement cardYear = $(".input__control[placeholder='22']");
    private SelenideElement cardMaster = $$(".input__control").get(3);
    private SelenideElement cardCVC = $(".input__control[placeholder='999']");
    private SelenideElement continueButton = $$(".button").find(exactText("Продолжить"));
    private SelenideElement failMessage = $$(".notification__title").find(exactText("Ошибка"));
    private SelenideElement successMessage = $$(".notification__title").find(exactText("Успешно"));

    public PaymentPage() {
        heading.shouldBe(visible);
    }

    public void dataCardFilling(CardInfo card) {
        cardNumber.setValue(card.getNumberCard());
        cardMonth.setValue(card.getMonth());
        cardYear.setValue(card.getYear());
        cardMaster.setValue(card.getMasterCard());
        cardCVC.setValue(card.getCvc());
        continueButton.click();
    }

    public void nullDataFilling() {
        continueButton.click();
    }

    public void successPayment() {
        successMessage.shouldBe(visible, Duration.ofSeconds(15));
    }

    public void failPayment() {
        failMessage.shouldBe(visible, Duration.ofSeconds(15));
    }

    public void checkInvalidDataFormat() {
        $(".input__sub").shouldHave(text("Неверный формат")).shouldBe(visible);
    }

    public void checkInvalidCardExpiryMonth() {
        $(".input__sub").shouldHave(text("Неверно указан срок действия карты")).shouldBe(visible);
    }

    public void checkInvalidCardExpiryYear() {
        $(".input__sub").shouldHave(text("Истёк срок действия карты")).shouldBe(visible);
    }

    public void checkNullCardMaster() {
        $(".input__sub").shouldHave(text("Поле обязательно для заполнения")).shouldBe(visible);
    }

    public void checkInvalidCardMaster() {
        $(".input__sub").shouldHave(text("Введите имя и фамилию, указанные на карте"))
                .shouldBe(visible);
    }

    public void checkIncorrectLanguage() {
        $(".input__sub").shouldHave(text("Значение поля может содержать только латинские буквы и дефис"))
                .shouldBe(visible);
    }

    public void checkFullInvalidFields() {
        $$(".input__sub").shouldHave(CollectionCondition.size(5))
                .shouldHave(CollectionCondition.texts("Поле обязательно для заполнения"));
    }
}
