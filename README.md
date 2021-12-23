# WeatherRest
This is test project from Rajder soft

This project provides acsess to DataBase with information about weather (id, date, temperature), 
using date (dd-MM-yyyy) user may get temperature from DB
if date input was incorrect user will receive readable error mesage
If dataBase have no line with current date the programm will get current temperature from yandex.ru and add it to dataBase (with date? that was inpup by user)


:::::HOW TO USE:::::

For this project You need postgresql database with columns: id(integer),date(date), temperature(integer)

Also is necessery add to application.properties:
spring.datasource.url={DB address}
spring.datasource.username={USER_NAME}
spring.datasource.password={PASSWORD}
