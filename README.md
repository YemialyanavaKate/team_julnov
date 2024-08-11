# JE 140

### Формат МР(запросов на слияни веток)
  * Название ветки должно соотвествовать паттеру
    * "<фамилия>-<цифра>-<цифра>" (все буквы с строчные) - "wasileuski-1-3"
  * Название(title) МР
    * "<ITA>:<фамилия> <цифра>-<цифра>" - "ITA: wasileuski 1-3"

## Задачи Первого модуля

### 1.1.0 Задание
  * Создание мультимодульного проекта с помощью Maven
  * Название модулей: 
    * web-app,
    * data-base-app, 
    * service-app
  * В каждом модуле должна быть идентичная структура корневых каталогов
      * /src/main/java/by/ita/je/
      * /src/test/java/by/ita/je/
  * Каждый модуль полностью независим и собирается в jar файл

#### 1.1.1
  * Отредактируйте pom файл, который общий для всех модулей
```
    <groupId>by.ita</groupId>
    <artifactId>je140</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <packaging>pom</packaging>

    <name>demo</name>
    <description>Demo project for Spring Boot</description>
    <modules>
      <module>web-app</module>
      <module>data-base-app</module>
      <module>service-app</module>
    </modules>
```
  * Для каждого из модулей создайте класс для старта Spring-Boot приложения в каждом модуле раздельно. Структура должна соответствовать этой:
  
  ![alt text](изображение.png)
  * Классы для старта должны именоваться соответственно
    * Для web-app - WebApp.java 
    * data-base-app - DataBase.java
    * service-app - ServiceApp.java
  Незбывайте про аннотации в этих классах `@SpringBootApplication`
  * Добавьте в каждый модуль файл `application.yml`, который должен находиться по пути src/main/resources/application.yml
  Файл должен содержать настройки для сервера приложение , а точнее номер порта соответственно:
    * web-app - 8091
    * data-base-app - 8101
    * service-app - 8111
    
  Пример:
  ```
    server:
      port: 8091
  ```
  * Удалите лишние каталоги, что бы структура кода выглядила так:
  
  ![alt text](изображение-1.png)
