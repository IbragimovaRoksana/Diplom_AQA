package ru.netology.page;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;
import ru.netology.data.CardInfo;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class CreditPage {
    private SelenideElement heading = $$("h3.heading").find(exactText("Кредит по данным карты"));
    private SelenideElement cardNumber = $(".input__control[placeholder='0000 0000 0000 0000']");
    private SelenideElement cardMonth = $(".input__control[placeholder='08']");
    private SelenideElement cardYear = $(".input__control[placeholder='22']");
    private SelenideElement cardMaster = $$(".input__control").get(3);
    private SelenideElement cardCVC = $(".input__control[placeholder='999']");
    private SelenideElement continueButton = $$(".button").find(exactText("Продолжить"));
    private SelenideElement failMessage = $$(".notification__title").find(exactText("Ошибка"));
    private SelenideElement successMessage = $$(".notification__title").find(exactText("Успешно"));

    public CreditPage() {
        heading.shouldBe(visible);
    }

    public void dataCreditCardFilling(CardInfo card) {
        cardNumber.setValue(card.getNumberCard());
        cardMonth.setValue(card.getMonth());
        cardYear.setValue(card.getYear());
        cardMaster.setValue(card.getMasterCard());
        cardCVC.setValue(card.getCvc());
        continueButton.click();
    }

    public void dataCreditDoubleCardFilling(CardInfo card) {
        cardNumber.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
        cardNumber.setValue(card.getNumberCard());
        cardMonth.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
        cardMonth.setValue(card.getMonth());
        cardYear.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
        cardYear.setValue(card.getYear());
        cardMaster.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
        cardMaster.setValue(card.getMasterCard());
        cardCVC.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
        cardCVC.setValue(card.getCvc());
        continueButton.click();
    }

    public void nullDataFilling() {
        continueButton.click();
    }

    public void successCreditPayment() {
        successMessage.shouldBe(visible, Duration.ofSeconds(15));
    }

    public void failCreditPayment() {
        failMessage.shouldBe(visible, Duration.ofSeconds(15));
    }

    public void checkCreditInvalidDataFormat() {
        $(".input__sub").shouldHave(text("Неверный формат")).shouldBe(visible);
    }

    public void checkCreditInvalidCardExpiryMonth() {
        $(".input__sub").shouldHave(text("Неверно указан срок действия карты")).shouldBe(visible);
    }

    public void checkCreditInvalidCardExpiryYear() {
        $(".input__sub").shouldHave(text("Истёк срок действия карты")).shouldBe(visible);
    }

    public void checkCreditNullField() {
        $(".input__sub").shouldHave(text("Поле обязательно для заполнения")).shouldBe(visible);
    }

    public void checkCreditInvalidCardMaster() {
        $(".input__sub").shouldHave(text("Введите имя и фамилию, указанные на карте"))
                .shouldBe(visible);
    }

    public void checkCreditIncorrectLanguage() {
        $(".input__sub").shouldHave(text("Значение поля может содержать только латинские буквы и дефис"))
                .shouldBe(visible);
    }

    public void checkCreditFullInvalidFields() {
        $$(".input__sub").shouldHave(CollectionCondition.size(5))
                .shouldHave(CollectionCondition.texts("Поле обязательно для заполнения"));
    }

    public void checkCreditDoubleEntryData() {
        $$(".input__sub").shouldBe(CollectionCondition.empty);
        successMessage.shouldBe(visible, Duration.ofSeconds(15));
    }
}
