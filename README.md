# JE 140

### Предварительные заметки
### Формат МР(запросов на слияни веток)
  * Название ветки должно соотвествовать паттеру
    * "<фамилия>-<цифра>-<цифра>" (все буквы с строчные) - "wasileuski-1-3"
  * Название(title) МР
    * "<ITA>:<фамилия> <цифра>-<цифра>" - "ITA: wasileuski 1-3"
---
```html
```
## Задачи Первого модуля

---
### 1.1.0 Задание
#### Создание мультимодульного проекта с использованием Maven
#### Описание задания:

В этом задании вы разработаете мультимодульный проект на языке **Java** с использованием системы сборки Maven. Проект будет состоять из трех модулей: **web-app**, **data-base-app** и **service-app**. Каждый модуль должен иметь идентичную структуру корневых каталогов:
  * Создание мультимодульного проекта с помощью Maven
  * Название модулей: 
    * web-app,
    * data-base-app, 
    * service-app
  * В каждом модуле должна быть идентичная структура корневых каталогов
      * /src/main/java/by/ita/je/
      * /src/test/java/by/ita/je/
  * Каждый модуль полностью независим и собирается в jar файл
---
### 1.1.1 Задание
#### Редактирование проекта
#### Описание задания:
В этом задании вы научитесь конфигурировать модули как самостоятельные приложения, настроете работу каждого модуля отдельно от другого
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
  
  ![alt text](image-1.png)
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
  
  ![alt text](image-2.png)
---
```html
```
## Задачи второго модуля
---
### 2.1.0 Задание
#### Работа с SOLID, моделями, DTO и REST API
#### Описание задания:
Изучаем подходы к построению многослойной архитектуры, следуя принципам SOLID.
Создаем модели (сущности) и DTO (Data Transfer Object) с использованием паттерна Builder без применения библиотеки Lombok.
В моделях реализуются сложные структуры с различными типами полей, включая работу с временными метками ZoneDateTime.
Создаем REST-контроллеры для каждой DTO, реализующие CRUD операции, и формируют ответы в формате JSON.

* Соблюдайте правила "слоения" и структуру папок в проекте, `SOLID` принципы!
* В модуле `data-base-app` создать 4 сущности `MODEL`, разного назначения.
    * Пользоваться Ломбоком, нельзя! см пример https://projectlombok.org/features/Builder
    * В каждой сущности должно быть минимум 8 полей, не больше 2 одинаковых типов полей на класс.
    * Сущности должны собираться через паттерн `Builder`.
    * В каждой сущности должно быть одно поле типа `ZoneDateTime`.
* В модуле `data-base-app` создать 4 сущности `DTO` согласно вашим моделям.
    * В ваших `DTO` не должны быть показано поле `ZoneDateTime` типа.
    * `DTO` должны иметь в общем на 2 поля меньше чем соответствующая `MODEL`. 6 полей минимум.
    * `DTO` должен так же конструироваться билдером.
* В модуле `data-base-app` создать `REST` контроллеры, для `CRUD` задач под каждую созданную `DTO`(отдельный класс контроллер для каждой дто).
    * Чтение всех записей, удаление списком.
    * Следите за маппингами ваших методов контроллера, разделителем слов может быть только "/", все слова в маппинге пишутся строго строчными буквами.
    * Маппинг контроллеров должен содержать существительные/инфинитивы, говорящие о назначении эндпоинта.
    * Контроллеры должны возвращать ответы `DTO` сущностями в формате `JSON`. https://www.json.org/json-en.html

---
### 2.2.0 Задание
### Тестирование REST API с Postman
#### Описание задания:
Учимся тестировать свои контроллеры с помощью инструмента Postman. Создаем коллекции запросов для каждого контроллера, которые экспортируются для дальнейшего использования в проекте.

  * Установите `Postman` и проверьте все ваши контроллеры.
    * Создайте коллекцию в `Postman`, с папками для каждого из ваших контроллеров. (cм запись лекции)
    * В каждой папке, должны быть сохранены все запросы на контроллер.
    * Создайте папку `postman` в модуле `data-base-app` и экспортируйте туда свою коллекцию.

---
### 2.4.0 Задание
#### Введение в Lombok
#### Описание задания:
Переводим свои модели и DTO на библиотеку Lombok, автоматизируя создание стандартных методов и конструкторов.

  * Вы можете перевести все ваши модели и дто на ломбок.
  * Вы можете сделать это самостоятельно по гайду https://www.baeldung.com/intro-to-project-lombok
  * В следующих заданиях можете полноценно использовать ломбок для ваших `POJO`.\

---
### 2.5.0 Задание
#### Работа с базами данных через JDBC и H2
#### Описание задания:
Введение в работу с базами данных через Spring JDBC и настройка взаимодействия с базой данных H2. SQL-скрипты для генерации таблиц и заполнения их данными. Настраивается автоконфигурация базы данных через файл `application.yml`.
* Необходимо добавить в модуль `data-base-app` работу с БД через `JDBCTemplate`.
* Добавтьте необходимые зависимости в модуль
```xml
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-jpa</artifactId>
        </dependency>
```
Эта зависимость позволяет вам использовать разлиные библиотеки для работы с БД в Спринг
```xml
        <dependency>
            <groupId>com.h2database</groupId>
            <artifactId>h2</artifactId>
        </dependency>
```
Эта зависимость позволяет использовать одну из реализаций БД, для вашего приложения.
* Настройте автоконфигурирование для вашей БД, добавьте настройки в `application.yml` примерно такие:
```yaml
spring:
  datasource:
    url: jdbc:h2:mem:testdb
    username: sa
    password: sa
    driver-class-name: org.h2.Driver
    initialization-mode: always
  h2:
    console:
      enabled: true
      path: /h2-console
      settings.web-allow-others: true
```
* Для каждой модели вам необходимо сделать скрипт создания и заполнения БД (см. запись лекции), пример:
```sql
CREATE TABLE ANIMAL(uuid CHAR(36) PRIMARY KEY, appeared TIMESTAMP WITH TIME ZONE, name VARCHAR(255), price DECIMAL, weight DECIMAL, size DECIMAL,isCarnivore BOOLEAN, isPredator BOOLEAN);
INSERT TNTO ANIMAL(id, appeared, name, price, weight, size,isCarnivore, isPredator) VALUES (...);
INSERT TNTO ANIMAL(id, appeared, name, price, weight, size,isCarnivore, isPredator) VALUES (...);
INSERT TNTO ANIMAL(id, appeared, name, price, weight, size,isCarnivore, isPredator) VALUES (...);
INSERT TNTO ANIMAL(id, appeared, name, price, weight, size,isCarnivore, isPredator) VALUES (...);
```
* В скриптах, должно быть добавленно как минимум 4 строк добавленния данных.

---
### 2.6.0 Задание
#### Создание сервисного слоя и работа в нем с данными из БД
#### Описание задания:
Научимся использовать подключение к БД для манипуляции с данными, преобразоввывать ответы от **jdbcTemplate** в созданные нами модели и ДТО.
* Соблюдайте правила "слоения" и структуру папок в проекте, `SOLID` принципы!
* В модуле `data-base-app` создайте новый слой для сервисов в папке `services`.
* В папке `services` создайте компоненты для каждой из моделей соотвественно
* Интегрируйте в компоненты сервисов `JDBCTemplate` где будете выполнять запросы к БД, с помощью SQL запросов
  * jdbcTemplate.queryForObject(...) - для получениея записей
```java
  public Animal findAnimalById(UUID uuid) {
    String sql = "SELECT * FROM ANIMAL WHERE uuid = ?";
    return jdbcTemplate.queryForObject(sql, new RowMapper<Animal>() {
        @Override
        public Animal mapRow(ResultSet rs, int rowNum) throws SQLException {
            UUID uuid = UUID.fromString(rs.getString("uuid"));
            LocalDate appeared = rs.getDate("appeared").toLocalDate();
            String name = rs.getString("name");
            double price = rs.getDouble("price");
            double weight = rs.getDouble("weight");
            double size = rs.getDouble("size");
            boolean isCarnivore = rs.getBoolean("isCarnivore");
            boolean isPredator = rs.getBoolean("isPredator");

            return new Animal(uuid, appeared, name, price, weight, size, isCarnivore, isPredator);
        }
    }, uuid);
}
```
  * jdbcTemplate.update(...) - для изменения записей
```java
public Animal updateAnimal(Animal animal) {
    String sql = "UPDATE ANIMAL SET appeared = ?, name = ?, price = ?, weight = ?, size = ?, isCarnivore = ?, isPredator = ? WHERE uuid = ?";
    jdbcTemplate.update(sql, animal.getAppeared(), animal.getName(), animal.getPrice(), animal.getWeight(), animal.getSize(), animal.isCarnivore(), animal.isPredator(), animal.getUuid());
    return findAnimalById(animal.getUuid());
}
```
```java
public Animal deleteAnimal(UUID uuid) {
        Animal animal = findAnimalById(animal.getUuid());
        String sql = "DELETE FROM ANIMAL WHERE uuid = ?";
        jdbcTemplate.update(sql, animal.getUuid());
        return animal;
    }
```
```java
public Animal insertAnimal(Animal animal) {
        String sql = "INSERT INTO ANIMAL (uuid, appeared, name, price, weight, size, isCarnivore, isPredator) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, animal.getUuid(), animal.getAppeared(), animal.getName(), animal.getPrice(), animal.getWeight(), animal.getSize(), animal.isCarnivore(), animal.isPredator());
        return findAnimalById(animal.getUuid());
    }
```
```java
    public List<Animal> readAll() {
        return jdbcTemplate.queryForList("SELECT * FROM ANIMAL", Animal.class);
    }
```
* После того как вы реализовали методы сервисов, вам нужно интегрировать ваши сервисы к вашим контроллерам соответственно
* Тк возвращаемые значения сервисов не совпадают с типами возвращаемыми в контроллерах, вам нужно реализовать механизм маппинга.
Пример:
```java
@Component
public class AnimalMapper {

    public AnimalDTO toDTO(Animal animal) {
        return new AnimalDTO( // или с помощью билдера
            animal.getName(),
            animal.getUuid(),
            (float) animal.getWeight(), // Преобразование double в Float
            (float) animal.getSize(),   // Преобразование double в Float
            animal.isCarnivore(),
            animal.isPredator()
        );
    }

    // Если нужен маппер из DTO в модель
    public Animal toEntity(AnimalDTO animalDTO) {
        return new Animal( // или с помощью билдера
            animalDTO.getUuid(),
            null, // Поле appeared не доступно в DTO, можно передать null или другой дефолтный объект
            animalDTO.getName(),
            0.0, // Поле price не доступно в DTO, можно передать 0.0 или другой дефолтный объект
            animalDTO.getWeight() != null ? animalDTO.getWeight() : 0.0, // Преобразование Float в double
            animalDTO.getSize() != null ? animalDTO.getSize() : 0.0,     // Преобразование Float в double
            animalDTO.getIsCarnivore() != null && animalDTO.getIsCarnivore(),
            animalDTO.getIsPredator() != null && animalDTO.getIsPredator()
        );
    }
}
```
* маппер интегрируется в контроллер и участвоет в преобразовании ответов от сервиса в ДТО.

---
### 2.7.0 Задание
#### Основы юнит тестирования с Mockito
#### Описание задания:
* Необходимо написать Unit тесты для публичных методов для все сервисов в пакете `by.ita.je.service`
* Тесты должны находиться в папке `src/test/java/by/ita/je/service`
* Шаблон для тестов должен выглядить примерно так:

```java
@ExtendWith(MockitoExtension.class) // расширение позволяющее использовать механизм мокирования
class SomeServiceTest { // класс теста должен иеменоваться `<Имя компонента>Test`

    @Test
    void methodNameTest_expectedResult() { // название теста должно состоять из `<имени метода>_then_<результат проверки в 1-2 слова>`
      // тело теста
    }
}
```
* На каждый публичный метод сервиса должен быть написан как минимум:
  * 1 тест (Happy path) на основную логику в методе
  * 0-1 тест на другие варианты логики (см лекцию)
  * 0-1 тест на каждое исключение которое может быть выброшено в методе или выброшено обращением в компонент, используемый сервисом
* Ориентировачная структура теста:
```java
  @Test
  void methodNameTest_expectedResult() { // название теста должно состоять из `<имени метода>_<результат проверки в 1-2 слова>`
    // описание вызовов моков

    // вызов тестируемого метода

    // сверка полученных результато

    // сверка задействованных моков
  }
```
* Для компонентов которые инициализированы в сервис последством DI нужно использовать моки как замена контектса
```java
@ExtendWith(MockitoExtension.class)
class BlushServiceTest {

    @Mock // мокируем компоненты из компазиции тестируемого компонента
    private JdbcTemplate jdbcTemplate;

    @InjectMocks // аннотация тестируемого компонента
    private BlushService service;
}
```
* В качестве ориентира можно использовать такие примеры:
Сервис который нужно протестировать
```java
@Service
public class BlushService {

    private final JdbcTemplate jdbcTemplate;

    public BlushService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Blush deleteBlush(UUID uuid) {
        Blush blush = findBlushById(uuid);
        if (blush == null) {
            return null;
        }

        String sql = "DELETE FROM BLUSH WHERE uuid = ?";
        jdbcTemplate.update(sql, blush.getUuid());
        return blush;
    }
}
```
Тесты для этого сервиса будут выглядеть примерно так:
```java
     @Test
    void deleteBlush_then_return() {
        UUID uuid = UUID.randomUUID();

        Mockito.when(
                jdbcTemplate.queryForObject(
                        any(String.class), // any (...) использовать в задании нельзя
                        any(RowMapper.class), // any (...) использовать в задании нельзя
                        any(UUID.class) // any (...) использовать в задании нельзя
                )
        ).thenReturn(
                Blush.builder() // возвращаемый обьект должен быть описан полностью
                        .uuid(uuid)
                        .build()
        );

        Mockito.when(
                jdbcTemplate.update(
                        any(String.class), // any (...) использовать в задании нельзя
                        any(UUID.class) // any (...) использовать в задании нельзя
                )
        ).thenReturn(1);

        Blush actual = service.deleteBlush(uuid);

        Assertions.assertEquals(actual, Blush.builder().uuid(uuid).build()); 
    }

    @Test
    void deleteBlush_then_return_null() {
        UUID uuid = UUID.randomUUID();

        Mockito.when(
                jdbcTemplate.queryForObject(
                        any(String.class), // any (...) использовать в задании нельзя
                        any(RowMapper.class), // any (...) использовать в задании нельзя
                        any(UUID.class) // any (...) использовать в задании нельзя
                )
        ).thenReturn(
                null
        );

        Blush actual = service.deleteBlush(uuid);

        Assertions.assertEquals(actual, null);

        Mockito.verify(jdbcTemplate, new Times(1)).queryForObject(
                any(String.class), // any (...) использовать в задании нельзя
                any(RowMapper.class), // any (...) использовать в задании нельзя
                any(UUID.class) // any (...) использовать в задании нельзя
        ); 
        Mockito.verify(jdbcTemplate, new Times(0)).update(
                any(String.class), // any (...) использовать в задании нельзя
                any(UUID.class) // any (...) использовать в задании нельзя
        );
    }

    @Test
    void deleteBlush_then_throws_exception() {
        UUID uuid = UUID.randomUUID();

        Mockito.when(
                jdbcTemplate.queryForObject(
                        any(String.class), // any (...) использовать в задании нельзя
                        any(RowMapper.class), // any (...) использовать в задании нельзя
                        any(UUID.class) // any (...) использовать в задании нельзя
                )
        ).thenReturn(
                Blush.builder().uuid(uuid).build()
        );

        Mockito.when(
                jdbcTemplate.update(
                        any(String.class), // any (...) использовать в задании нельзя
                        any(UUID.class) // any (...) использовать в задании нельзя
                )
        ).thenThrow(
                new DataAccessException("Boo") {
                    @Override
                    public String getMessage() {
                        return super.getMessage();
                    }
                }
        );

        Assertions.assertThrows(DataAccessException.class, () -> service.deleteBlush(uuid));
    }
```
* Важный критерий!!! Тесты должны "реагировать" на изменения в коде методов компонентов. Это показатель качества тестов

---
### Задание 2.8.0
#### Тестирование сервисов с использованием @SpringBootTest
#### Описание задания
В этом задании вы научитесь писать тесты для сервисов вашего проекта с использованием аннотации **@SpringBootTest**. Эта аннотация помогает запускать весь контекст приложения, что позволяет вам тестировать сервисы в условиях, близких к реальному использованию.

**Задача:**
1. Напишите тест для каджого из сервисов в вашем проекте с использованием **@SpringBootTest**.
2. Тест должен проверить бизнес-логику сервиса. Например, если в сервисе есть метод, который взаимодействует с базой данных, то убедитесь, что данные обрабатываются корректно.
3. В тесте можно использовать аннотацию **@Autowired** для инжекции зависимостей и доступ к репозиториям, если это требуется.

**Пример**:
```java
@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void testFindUser() {
        User user = userService.findUserById(1L);
        assertNotNull(user);
        assertEquals("John", user.getName());
    }
}
```

#### Подсказки:
- Вы можете использовать аннотацию **@Transactional** для отката изменений после теста.
- Для упрощения взаимодействия с базой данных в тестах можно использовать **@TestEntityManager**.

Не бойтесь допустить ошибки — это часть процесса обучения. Постарайтесь проанализировать, как работает ваш сервис в реальном приложении, и используйте тесты для проверки этой логики.

---
### 2.9.0 Задание
#### Работа с CrudRepository, JpaRepository и EntityManager
#### Описание задания
Это задание поможет вам глубже разобраться в работе с репозиториями в Spring Data и с **EntityManager** для более сложных операций с базой данных. Вам нужно будет исследовать и применить подходы для работы с различными типами репозиториев и менеджером сущностей в рамках вашего проекта.

**Задача:**
1. Расширьте функционал по одному из ваших репозиториев, использовав **CrudRepository**, **JpaRepository**, и **EntityManager**. Последний остается в **JdbcTemplate** реализации, просто вынесите из сервиса **JdbcTemplate** в обертку ля слоя репозиториев.
2. Порторите функционал **JdbcTemplate** с помощью **CrudRepository**, **JpaRepository**, **EntityManager**. 
3. Для **EntityManager**, используйте транзации для каждого из методов на изменение данных.
4. Проверьте правильность работы ваших сервисов с новыми реализациями репозиториев с помощью **@SpringBootTest**.

**Пример:**
```java
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByName(String name);
}
```

Для **EntityManager**:
```java
@PersistenceContext
private EntityManager entityManager;

public User findUserByCustomQuery(Long id) {
    return entityManager.createQuery("SELECT u FROM User u WHERE u.id = :id", User.class)
                        .setParameter("id", id)
                        .getSingleResult();
}
```

#### Подсказки:
- В чем различие между **CrudRepository** и **JpaRepository**? Попробуйте изучить это самостоятельно.
- Когда стоит использовать **EntityManager** вместо стандартных репозиториев?
- Подумайте, как вы можете комбинировать возможности стандартных репозиториев и кастомных запросов через **EntityManager** для более сложных задач.
---
```html
```
## Задачи Третьего модуля

---
### 3.0.0 Задание
#### ?
#### Описание задания