# Дипломный проект профессии «Тестировщик»
[![Build status](https://ci.appveyor.com/api/projects/status/jr3f171nmlu93x12?svg=true)](https://ci.appveyor.com/project/IbragimovaRoksana/diplom-aqa)
## Краткое описание проекта
Дипломный проект представляет собой автоматизацию тестирования комплексного сервиса покупки тура, взаимодействующего с СУБД и API Банка.

База данных хранит информацию о способах оплаты, статусе карты, сумме заказа, данные карты.

В приложении используются два отдельных сервиса оплаты: Payment Gate и Credit Gate, которые реализуют возможность покупки тура с помощью дебетовой или кредитной карты сответственно.

Ссылка на дипломный проект: https://github.com/IbragimovaRoksana/Diplom_AQA.

## Запуск приложения
### Предустановки
- Установить IntellIJ IDEA Ultimate;
- Установить Node.js;
- Установить Docker Desktop.
### Запуск тестов
1. В рабочую папку склонировать проект: git clone https://github.com/IbragimovaRoksana/Diplom_AQA.git
2. Запустить Docker Desktop
3. Запустить IntellIJ Idea Ultimate:

  3.1 Открыть склонированный проект;
  
  3.2 Открыть терминал, запустить работу контейнеров командой docker-compose up -d --force-recreate;
  
  3.3 Открыть новую вкладку терминала (+), перейти в папку симулятора командой: cd gate-simulator;
  
  3.4 Запустить симулятор банковских сервисов командой npm start;
  
  3.5 Открыть новую вкладку терминала (+), запустить приложение командой: java -jar aqa-shop.jar;
  
  3.6 Открыть браузер Google Chrome, перейти по адресу: http://localhost:8080.
  
  3.7 Открыть боковую вкладку IntellIJ IDEA Ultimate - Database для подключения к БД;
  
  3.8 Нажать +, выбрать Data Source -> MySQL;
  
  3.9 В окне Data Source ввести данные подключения к БД User, Password, Name Database из файла application.properties;
  
  3.10 Повторить шаги 3.8 и 3.9 по подключению к БД PostgreSQL;
  
  3.11 Провести тест подключения с помощью надписи Test Connection, после успеха нажать кнопку Ок;
  
  3.12 Запустить работу тестового класса Ctrl+Shift+F10.
  
4. После окончания работы с приложением:
  - Закончить работу приложения Ctrl+F2;
  - Закончить работу контейнеров: в терминале запуска docker ввести команду: docker-compose down;
  - Закончить работу симулятора: в терминале запуска симулятора нажать Ctrl+C, на вопрос о завершении ввести y и нажать Enter.

### Получение отчетов
**Формирование отчета MySQL**
- Открыть в терминале (+) новую вкладку и ввести команду ./gradlew clean test "-Ddb.url=jdbc:mysql://localhost:3306/app" "-Ddb.user=app" "-Ddb.password=pass"
- В терминале ввести команду ./gradlew allureServe
- После прохождения всех тестов автоматически откроется браузер с генерированным отчетом Allure Report. 
- После работы с отчетом Allure нажать Сtrl+C для выхода из режима генерации отчета в терминал.
**Формирование отчета postgresql**
- В файле application.properties в строке spring.datasource.url прописываем jdbc:postgresql://localhost:5432/app
- В классе Database изменить значение переменной private static String url = "jdbc:postgresql://localhost:5432/app"
- Открыть в терминале (+) новую вкладку и ввести команду  ./gradlew clean test "-Ddb.url=jdbc:postgresql://localhost:5432/app" "-Ddb.user=app" "-Ddb.password=pass"
- После прохождения тестов в терминале ввести команду ./gradlew allureServe
- После прохождения всех тестов автоматически откроется браузер с генерированным отчетом Allure Report. 
- После работы с отчетом Allure нажать Сtrl+C для выхода из режима генерации отчета в терминал.
  
