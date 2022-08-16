package ru.netology.page;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import ru.netology.data.CardInfo;
import ru.netology.data.DatabaseConnection;

import java.sql.SQLException;

import static com.codeborne.selenide.Selenide.open;
import static ru.netology.data.DataGenerator.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Тестирование покупки тура по дебетовой карте")
class PaymentPageTest {
    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @BeforeEach
    public void setUp() {
        Configuration.headless = true;
        open("http://localhost:8080");
    }

    @AfterAll
    static void tearDownAll() throws SQLException {
        SelenideLogger.removeListener("allure");
        DatabaseConnection.deleteTables();
    }

    @DisplayName("Проверка карты со статусом APPROVED")
    @Test
    void shouldDataCardFillingApproved() throws SQLException {
        CardInfo cardInfo = new CardInfo(getApprovedCardNumber(), getMonthCard(2),
                getYearCard(1), getMasterCard(), getCvcCode());
        var startPage = new StartPage();
        var paymentPage = startPage.payment();
        paymentPage.dataCardFilling(cardInfo);
        String status = DatabaseConnection.getPaymentStatus();
        assertEquals("APPROVED", status);
    }

    @DisplayName("Проверка карты со статусом DECLINED")
    @Test
    void shouldDataCardFillingDeclined() throws SQLException {
        CardInfo cardInfo = new CardInfo(getDeclinedCardNumber(), getMonthCard(3),
                getYearCard(2), getMasterCard(), getCvcCode());
        var startPage = new StartPage();
        var paymentPage = startPage.payment();
        paymentPage.dataCardFilling(cardInfo);
        String status = DatabaseConnection.getPaymentStatus();
        assertEquals("DECLINED", status);
    }

    @DisplayName("Отправка пустой формы")
    @Test
    void shouldNullDataFilling() {
        var startPage = new StartPage();
        var paymentPage = startPage.payment();
        paymentPage.nullDataFilling();
        paymentPage.checkFullInvalidFields();
    }

    @DisplayName("Проверка успешной отправки формы с окончанием карты в текущем месяце")
    @Test
    void shouldSuccessPayment() {
        CardInfo cardInfo = new CardInfo(getApprovedCardNumber(), getMonthCard(0),
                getYearCard(0), getMasterCard(), getCvcCode());
        var startPage = new StartPage();
        var paymentPage = startPage.payment();
        paymentPage.dataCardFilling(cardInfo);
        paymentPage.successPayment();
    }

    @DisplayName("Проверка получения сообщения со статусом Ошибка")
    @Test
    void shouldFailPayment() {
        CardInfo cardInfo = new CardInfo(getDeclinedCardNumber(), getMonthCard(0),
                getYearCard(0), getMasterCard(), getCvcCode());
        var startPage = new StartPage();
        var paymentPage = startPage.payment();
        paymentPage.dataCardFilling(cardInfo);
        paymentPage.failPayment();
    }

    @DisplayName("Отправка короткого номера карты")
    @Test
    void shouldShortInvalidDataFormat() {
        CardInfo cardInfo = new CardInfo(getInvalidShortCardNumber(), getMonthCard(0),
                getYearCard(0), getMasterCard(), getCvcCode());
        var startPage = new StartPage();
        var paymentPage = startPage.payment();
        paymentPage.dataCardFilling(cardInfo);
        paymentPage.checkInvalidDataFormat();
    }

    @DisplayName("Отправка пустого поля номера карты")
    @Test
    void shouldNullDataCardFormat() {
        CardInfo cardInfo = new CardInfo(null, getMonthCard(0),
                getYearCard(0), getMasterCard(), getCvcCode());
        var startPage = new StartPage();
        var paymentPage = startPage.payment();
        paymentPage.dataCardFilling(cardInfo);
        paymentPage.checkNullField();
    }

    @DisplayName("Отправка некорректного номера карты")
    @Test
    void shouldCheckInvalidDataFormat() {
        CardInfo cardInfo = new CardInfo(getInvalidCardNumber(), getMonthCard(0),
                getYearCard(0), getMasterCard(), getCvcCode());
        var startPage = new StartPage();
        var paymentPage = startPage.payment();
        paymentPage.dataCardFilling(cardInfo);
        paymentPage.failPayment();
    }

    @DisplayName("Отправка месяца со значением 00")
    @Test
    void shouldCheckInvalidCardZeroMonth() {
        CardInfo cardInfo = new CardInfo(getApprovedCardNumber(), getZeroMonth(),
                getYearCard(3), getMasterCard(), getCvcCode());
        var startPage = new StartPage();
        var paymentPage = startPage.payment();
        paymentPage.dataCardFilling(cardInfo);
        paymentPage.checkInvalidCardExpiryMonth();
    }

    @DisplayName("Отправка пустого поля месяц")
    @Test
    void shouldCheckInvalidCardNullMonth() {
        CardInfo cardInfo = new CardInfo(getApprovedCardNumber(), null,
                getYearCard(2), getMasterCard(), getCvcCode());
        var startPage = new StartPage();
        var paymentPage = startPage.payment();
        paymentPage.dataCardFilling(cardInfo);
        paymentPage.checkNullField();
    }

    @DisplayName("Отправка месяца со значением больше 12")
    @Test
    void shouldCheckInvalidCardMonth() {
        CardInfo cardInfo = new CardInfo(getApprovedCardNumber(), getInvalidMonth(),
                getYearCard(3), getMasterCard(), getCvcCode());
        var startPage = new StartPage();
        var paymentPage = startPage.payment();
        paymentPage.dataCardFilling(cardInfo);
        paymentPage.checkInvalidCardExpiryMonth();
    }

    @DisplayName("Отправка текущего года и месяца со значением меньше текущего")
    @Test
    void shouldCheckInvalidMonthBellowCurrent() {
        CardInfo cardInfo = new CardInfo(getApprovedCardNumber(), getInvalidMonthBellowCurrent(),
                getYearCard(0), getMasterCard(), getCvcCode());
        var startPage = new StartPage();
        var paymentPage = startPage.payment();
        paymentPage.dataCardFilling(cardInfo);
        paymentPage.checkInvalidCardExpiryMonth();
    }

    @DisplayName("Отправка значения месяца одним символом")
    @Test
    void shouldCheckInvalidMonthOneSign() {
        CardInfo cardInfo = new CardInfo(getApprovedCardNumber(), getInvalidMonthOneSign(),
                getYearCard(1), getMasterCard(), getCvcCode());
        var startPage = new StartPage();
        var paymentPage = startPage.payment();
        paymentPage.dataCardFilling(cardInfo);
        paymentPage.checkInvalidCardExpiryMonth();
    }

    @DisplayName("Отправка значения года ниже текущего")
    @Test
    void shouldCheckInvalidCardExpiryYear() {
        CardInfo cardInfo = new CardInfo(getApprovedCardNumber(), getMonthCard(3),
                getInvalidYearBellowCurrent(), getMasterCard(), getCvcCode());
        var startPage = new StartPage();
        var paymentPage = startPage.payment();
        paymentPage.dataCardFilling(cardInfo);
        paymentPage.checkInvalidCardExpiryYear();
    }

    @DisplayName("Отправка значения года одним символом")
    @Test
    void shouldCheckInvalidCardYearOneSign() {
        CardInfo cardInfo = new CardInfo(getApprovedCardNumber(), getMonthCard(3),
                getInvalidYearOneSign(), getMasterCard(), getCvcCode());
        var startPage = new StartPage();
        var paymentPage = startPage.payment();
        paymentPage.dataCardFilling(cardInfo);
        paymentPage.checkInvalidCardExpiryMonth();
    }

    @DisplayName("Отправка пустого значения года")
    @Test
    void shouldCheckInvalidCardNullYear() {
        CardInfo cardInfo = new CardInfo(getApprovedCardNumber(), getMonthCard(3),
                null, getMasterCard(), getCvcCode());
        var startPage = new StartPage();
        var paymentPage = startPage.payment();
        paymentPage.dataCardFilling(cardInfo);
        paymentPage.checkNullField();
    }

    @DisplayName("Отправка cvc кода 2 символами")
    @Test
    void shouldCheckInvalidCardCVC() {
        CardInfo cardInfo = new CardInfo(getApprovedCardNumber(), getMonthCard(3),
                getYearCard(2), getMasterCard(), getInvalidCvc());
        var startPage = new StartPage();
        var paymentPage = startPage.payment();
        paymentPage.dataCardFilling(cardInfo);
        paymentPage.checkInvalidDataFormat();
    }

    @DisplayName("Отправка пустого поля cvc кода")
    @Test
    void shouldCheckInvalidCardNullCVC() {
        CardInfo cardInfo = new CardInfo(getApprovedCardNumber(), getMonthCard(3),
                getYearCard(2), getMasterCard(), null);
        var startPage = new StartPage();
        var paymentPage = startPage.payment();
        paymentPage.dataCardFilling(cardInfo);
        paymentPage.checkNullCvc();
    }

    @DisplayName("Отправка пустого поля имени владельца")
    @Test
    void shouldCheckNullCardMaster() {
        CardInfo cardInfo = new CardInfo(getApprovedCardNumber(), getMonthCard(1),
                getYearCard(3), null, getCvcCode());
        var startPage = new StartPage();
        var paymentPage = startPage.payment();
        paymentPage.dataCardFilling(cardInfo);
        paymentPage.checkNullField();
    }

    @DisplayName("Отправка только фамилии владельца")
    @Test
    void checkInvalidCardMasterFirstName() {
        CardInfo cardInfo = new CardInfo(getApprovedCardNumber(), getMonthCard(1),
                getYearCard(3), getInvalidMasterFirstName(), getCvcCode());
        var startPage = new StartPage();
        var paymentPage = startPage.payment();
        paymentPage.dataCardFilling(cardInfo);
        paymentPage.checkInvalidCardMaster();
    }

    @DisplayName("Отправка имени владельца на кириллице")
    @Test
    void shouldCheckIncorrectMasterLanguage() {
        CardInfo cardInfo = new CardInfo(getApprovedCardNumber(), getMonthCard(1),
                getYearCard(3), getInvalidMasterCyrillic(), getCvcCode());
        var startPage = new StartPage();
        var paymentPage = startPage.payment();
        paymentPage.dataCardFilling(cardInfo);
        paymentPage.checkIncorrectLanguage();
    }

    @DisplayName("Отправка имени владельца с цифрами")
    @Test
    void shouldCheckIncorrectMasterWithNumbers() {
        CardInfo cardInfo = new CardInfo(getApprovedCardNumber(), getMonthCard(1),
                getYearCard(3), getInvalidMasterWithNumber(), getCvcCode());
        var startPage = new StartPage();
        var paymentPage = startPage.payment();
        paymentPage.dataCardFilling(cardInfo);
        paymentPage.checkIncorrectLanguage();
    }

    @DisplayName("Отправка имени владельца с символами")
    @Test
    void shouldCheckIncorrectMasterWithSymbols() {
        CardInfo cardInfo = new CardInfo(getApprovedCardNumber(), getMonthCard(1),
                getYearCard(3), getMasterWithSymbols(), getCvcCode());
        var startPage = new StartPage();
        var paymentPage = startPage.payment();
        paymentPage.dataCardFilling(cardInfo);
        paymentPage.checkIncorrectLanguage();
    }

    @DisplayName("Отправка имени владельца только пробелами")
    @Test
    void shouldCheckIncorrectMasterWithSpaces() {
        CardInfo cardInfo = new CardInfo(getApprovedCardNumber(), getMonthCard(1),
                getYearCard(3), getMasterWithSpaces(), getCvcCode());
        var startPage = new StartPage();
        var paymentPage = startPage.payment();
        paymentPage.dataCardFilling(cardInfo);
        paymentPage.checkIncorrectLanguage();
    }

    @DisplayName("Отправка имени владельца одним символом")
    @Test
    void shouldCheckIncorrectShortMaster() {
        CardInfo cardInfo = new CardInfo(getApprovedCardNumber(), getMonthCard(1),
                getYearCard(3), getInvalidMasterShort(), getCvcCode());
        var startPage = new StartPage();
        var paymentPage = startPage.payment();
        paymentPage.dataCardFilling(cardInfo);
        paymentPage.checkInvalidCardMaster();
    }

    @DisplayName("Отправка имени владельца с фамилией через дефис")
    @Test
    void shouldCheckMasterWithDoubleSurname() {
        CardInfo cardInfo = new CardInfo(getApprovedCardNumber(), getMonthCard(1),
                getYearCard(3), getMasterWithDoubleSurname(), getCvcCode());
        var startPage = new StartPage();
        var paymentPage = startPage.payment();
        paymentPage.dataCardFilling(cardInfo);
        paymentPage.successPayment();
    }

    @DisplayName("Повторная отправка данных после неправильного ввода")
    @Test
    void shouldCheckDoubleEntryData() {
        CardInfo cardInfo = new CardInfo(getApprovedCardNumber(), getMonthCard(1),
                getInvalidYearBellowCurrent(), getMasterCard(), getCvcCode());
        var startPage = new StartPage();
        var paymentPage = startPage.payment();
        paymentPage.dataCardFilling(cardInfo);
        cardInfo.setYear(getYearCard(2));
        paymentPage.dataDoubleCardFilling(cardInfo);
        paymentPage.checkDoubleEntryData();
    }
}