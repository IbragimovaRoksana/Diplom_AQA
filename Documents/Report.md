# Отчёт о проведённом тестировании

### 1. Краткое описание
   Целью дипломного проекта было проведение тестирования веб-сервиса покупки тура "Путешествие дня",
   который представляет собой комплексный сервис, взаимодействующий с СУБД и API Банка.

1. Проведено исследовательское тестирование, на основе которого разработан план тестирования 
2. Проведено ручное тестирование и составлен план автоматизации (Plan.md)
3. Разработаны data-классы и классы с автотестами
4. Проведено автоматизированное тестирование с отладкой тестов
5. Сгенерирован отчет о проведенном тестировании в Gradle и Allure Report

### 2. Количество тест-кейсов
- Всего тест-кейсов: 54
- Всего успешных тест-кейсов: MySQL - 19; PosgreSQL - 22
- Всего не успешных тест-кейсов: MySQL - 35; PostgreSQL - 32

### 3. Процент успешных/не успешных
- Всего успешных тест-кейсов: MySQL - 35%; PostgreSQL - 40%
- Всего не успешных тест-кейсов: MySQL - 65%; PostgreSQL - 60

### 4. Общие рекомендации

Устранить дефекты обнаруженные в ходе тестирования: https://github.com/IbragimovaRoksana/Diplom_AQA/issues

### 5. Скриншоты отчетов
*Gradle отчет с подключенной базой данных MySQL*
![Gradle MySQL](https://user-images.githubusercontent.com/73775733/185325604-e85189fb-ac03-421b-b504-a3ebb030202f.jpg)
![Gradle MySQL2](https://user-images.githubusercontent.com/73775733/185325889-0ee7484c-b984-495e-8b82-aba929b435c8.jpg)

*Gradle отчет с подключенной базой данных PostgreSQL*
![Gradle PosgreSQL](https://user-images.githubusercontent.com/73775733/185326023-eff18caf-659b-400e-8b9b-5a6c8913a682.jpg)
![Gradle PosgreSQL2](https://user-images.githubusercontent.com/73775733/185326171-cf005f22-f490-4ea3-ab60-9f552b559e51.jpg)

*Allure Report с подключенной базой данных MySQL*
![Allure MySQL](https://user-images.githubusercontent.com/73775733/185326850-23065e0c-397a-4e0e-8829-6a76f98eac09.jpg)
![Allure MySQL2](https://user-images.githubusercontent.com/73775733/185326903-1e9e0465-f69f-402d-bc6d-1b35a2a3e71b.jpg)

*Allure Report с подключенной базой данных PostgreSQL*
![Allure PosgreSQL](https://user-images.githubusercontent.com/73775733/185326291-fbb3b229-5a25-426a-a8c3-9646946a2b90.jpg)
![Allure PosgreSQL2](https://user-images.githubusercontent.com/73775733/185326355-60dc5c76-822d-4120-959f-b6b2bfc8fe57.jpg)

