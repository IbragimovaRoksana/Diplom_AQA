package ru.netology.data;

import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Random;

public class DataGenerator {
    private DataGenerator() {
    }

    public static Faker faker = new Faker(new Locale("en"));

    public static String getApprovedCardNumber() {
        return ("4444 4444 4444 4441");
    }

    public static String getDeclinedCardNumber() {
        return ("4444 4444 4444 4442");
    }

    public static String getInvalidShortCardNumber() {
        return faker.numerify("#### #### #### ##");
    }

    public static String getInvalidCardNumber() {
        return faker.finance().creditCard();
    }

    public static String getMonthCard(int plusMonth) {
        return LocalDate.now().plusMonths(plusMonth).format(DateTimeFormatter.ofPattern("MM"));
    }

    public static String getZeroMonth() {
        return ("00");
    }

    public static String getInvalidMonth() {
        Random random = new Random();
        Integer month = 13 + random.nextInt(12);
        return month.toString();
    }

    public static String getInvalidMonthBellowCurrent() {
        Integer minusMonth = LocalDate.now().getMonthValue() - 1;
        return minusMonth.toString();
    }

    public static String getInvalidMonthOneSign() {
        return faker.numerify("#");
    }

    public static String getYearCard(int plusYear) {
        return LocalDate.now().plusYears(plusYear).format(DateTimeFormatter.ofPattern("YY"));
    }

    public static String getInvalidYearBellowCurrent() {
        Random random = new Random();
        Integer minusYear = 1 + random.nextInt(5);
        return LocalDate.now().minusYears(minusYear).format(DateTimeFormatter.ofPattern("YY"));
    }

    public static String getInvalidYearOneSign() {
        return faker.numerify("#");
    }

    public static String getCvcCode() {
        return faker.numerify("###");
    }

    public static String getInvalidCvc() {
        return faker.numerify("##");
    }

    public static String getMasterCard() {
        return faker.name().fullName();
    }

    public static String getInvalidMasterFirstName() {
        return faker.name().firstName();
    }

    public static String getInvalidMasterCyrillic() {
        Faker fakerCyrillic = new Faker(new Locale("ru"));
        return fakerCyrillic.name().fullName();
    }

    public static String getInvalidMasterWithNumber() {
        return faker.name().firstName() + faker.numerify("####");
    }

    public static String getMasterWithDoubleSurname() {
        return ("Vasiliy Ivanov-Petrov");
    }

    public static String getMasterWithSymbols() {
        return ("$_&-*@");
    }

    public static String getMasterWithSpaces() {
        return ("    ");
    }

    public static String getInvalidMasterShort() {
        return faker.letterify("?");
    }

}
