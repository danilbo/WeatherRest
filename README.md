# WeatherRest
This is test project from Rajder soft

This project provides acsess to DataBase with information about weather (id, date, temperature), 
using date (dd-MM-yyyy) user may get temperature from DB
if date input was incorrect user will receive readable error mesage
If dataBase have no line with current date the programm will get current temperature from yandex.ru and add it to dataBase (with date that was input by user)


# HOW TO USE

For this project You need postgresql. On the first run table weather_history with columns: id(integer),date(date), temperature(string/varchar) will be generated

Before start:
Set application.properties:
spring.datasource.url={DB address}
spring.datasource.username={USER_NAME}
spring.datasource.password={PASSWORD}

JDK 17
Build application with maven version 3.6.3 or higher maven clean install
Run application with:
    ->mvn spring-boot:run

After programm was started You can use it with web-browser by address:
              http://localhost:8080/weather/currentWeather/{date}
              where instead "{date}" should be date like:/22-12-2021

### Warning
If You will input date in another format, programm return error(readable error in JSON format)
Right date format is: dd-MM-yyyy
              
