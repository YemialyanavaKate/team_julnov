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

В этом задании вы разработаете мультимодульный проект на языке **Java** с использованием системы сборки Maven. Проект
будет состоять из трех модулей: **web-app**, **data-base-app** и **service-app**. Каждый модуль должен иметь идентичную
структуру корневых каталогов:

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

В этом задании вы научитесь конфигурировать модули как самостоятельные приложения, настроете работу каждого модуля
отдельно от другого

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

* Для каждого из модулей создайте класс для старта Spring-Boot приложения в каждом модуле раздельно. Структура должна
  соответствовать этой:

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
Создаем модели (сущности) и DTO (Data Transfer Object) с использованием паттерна Builder без применения библиотеки
Lombok.
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
* В модуле `data-base-app` создать `REST` контроллеры, для `CRUD` задач под каждую созданную `DTO`(отдельный класс
  контроллер для каждой дто).
    * Чтение всех записей, удаление списком.
    * Следите за маппингами ваших методов контроллера, разделителем слов может быть только "/", все слова в маппинге
      пишутся строго строчными буквами.
    * Маппинг контроллеров должен содержать существительные/инфинитивы, говорящие о назначении эндпоинта.
    * Контроллеры должны возвращать ответы `DTO` сущностями в формате `JSON`. https://www.json.org/json-en.html

---

### 2.2.0 Задание

### Тестирование REST API с Postman

#### Описание задания:

Учимся тестировать свои контроллеры с помощью инструмента Postman. Создаем коллекции запросов для каждого контроллера,
которые экспортируются для дальнейшего использования в проекте.

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

Введение в работу с базами данных через Spring JDBC и настройка взаимодействия с базой данных H2. SQL-скрипты для
генерации таблиц и заполнения их данными. Настраивается автоконфигурация базы данных через файл `application.yml`.

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

Научимся использовать подключение к БД для манипуляции с данными, преобразоввывать ответы от **jdbcTemplate** в
созданные нами модели и ДТО.

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

* После того как вы реализовали методы сервисов, вам нужно интегрировать ваши сервисы к вашим контроллерам
  соответственно
* Тк возвращаемые значения сервисов не совпадают с типами возвращаемыми в контроллерах, вам нужно реализовать механизм
  маппинга.
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
    * 0-1 тест на каждое исключение которое может быть выброшено в методе или выброшено обращением в компонент,
      используемый сервисом
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

В этом задании вы научитесь писать тесты для сервисов вашего проекта с использованием аннотации **@SpringBootTest**. Эта
аннотация помогает запускать весь контекст приложения, что позволяет вам тестировать сервисы в условиях, близких к
реальному использованию.

**Задача:**

1. Напишите тест для каджого из сервисов в вашем проекте с использованием **@SpringBootTest**.
2. Тест должен проверить бизнес-логику сервиса. Например, если в сервисе есть метод, который взаимодействует с базой
   данных, то убедитесь, что данные обрабатываются корректно.
3. В тесте можно использовать аннотацию **@Autowired** для инжекции зависимостей и доступ к репозиториям, если это
   требуется.

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

Не бойтесь допустить ошибки — это часть процесса обучения. Постарайтесь проанализировать, как работает ваш сервис в
реальном приложении, и используйте тесты для проверки этой логики.

---

### 2.9.0 Задание

#### Работа с CrudRepository, JpaRepository и EntityManager

#### Описание задания

Это задание поможет вам глубже разобраться в работе с репозиториями в Spring Data и с **EntityManager** для более
сложных операций с базой данных. Вам нужно будет исследовать и применить подходы для работы с различными типами
репозиториев и менеджером сущностей в рамках вашего проекта.

**Задача:**

1. Расширьте функционал по одному из ваших репозиториев, использовав **CrudRepository**, **JpaRepository**, и *
   *EntityManager**. Последний остается в **JdbcTemplate** реализации, просто вынесите из сервиса **JdbcTemplate** в
   обертку ля слоя репозиториев.
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
- Подумайте, как вы можете комбинировать возможности стандартных репозиториев и кастомных запросов через **EntityManager
  ** для более сложных задач.

---

```html
```

## Задачи Третьего модуля

---

### 3.0.0 Задание

#### Связи между моделями

#### Описание задания

Это задание поможет вам собрать связи между моделями и разобраться в тонкостях работы с ними.

* Добавьте в ваши модели 1 сущность типа `ENUM` для определения неких константных состояний.
    * Оберните ваш `ENUM` в обычный клас для более простого управления сущностями.
    * Для данной сущности контроллер делать не нужно.
* Из ваших 4-х(A,B,C,D) моделей соберите структуру, с логическим и очевидным бизнес процессом, опираясь на схему
  ниже, где E это 5-ая созданная вами модель `ENUM`.
  ![img.png](img.png)
* Все `FetchTypes` оставте по умолчанию но добавьте каскадность к связям.
* Обновите ваши скрипты для инициализации таблиц в БД с учетом этих связей и свяжите как минимум по 2 существующих
  записи для каждй таблицы
* Обновтие тесты согласно внесенным измениениям

---

### 3.1.0 Задание

#### Микросервисная эмуляция общения через REST

#### Описание задания

В этом задании мы попытаемся реализовать межсерверное взаимодействие через REST

* В модуле `service-app`создайте отдельный сервис для бизнес процесса который будет работать с 4 сущностями
  из `data-base-app`.
    * Этот сервис должен иметь поведение, - процесс добавления данных для А, обогащения А данными из В и С, так
      же должна быть описана логика по добавлению данных D и E по условию. Этот сервис не может на прямую обращаться
      к `DAO A,B,C,D,E`.
    * Этот сервис должен иметь поведение, - изменения данных А и связанных с ним C, B, D, E.
    * Этот сервис должен иметь поведение, - чтение А с глубиной вложенности до В, С.
    * Возвращаемая `DTO` должна содержать данные только о сущностях A, B, C.
    * Этот сервис должен иметь поведение, - удаление А и всех ЗАВИСИМЫХ от А сущностей. Т.е. Если записи А не
      существует, то смысл существования записей B,C,D,E пересматривается (зависит от бизнес логики ваших связей).
* Для передачи данных их текущего модуля нужно
  использовать `RestTemplate` [подсказка](https://www.baeldung.com/rest-template)
* Сервис работает только с моделяти, `DTO` должны оставаться на уровне контролеера
* Написать юниты и интеграционные тесты на этот сервис
* Новый сервис назовите `BuisnessService`.
* `DTO` должны отображать связи моделей аналогичным образом.
* Создайте контроллер по нужды вашего сервиса.

---

### 3.2.0 Задание

#### Обработка исключений на контроллерах и в интеграциях

#### Описание задания

* Напишите [контроллер](https://www.baeldung.com/exception-handling-for-rest-with-spring),
  для обработки ошибок возвращаемых с модуля `data-base-app` для запросов из `service-app`. Ошибки обрабатывать
  отдельной
  страничкой, с помощью `@AdviceController` (только для `web-app`), на странице должна быть гиперссылка/кнопка, для
  возврата на главную страницу.

---

### 3.3.0 Задание

#### Интеграция Thymeleaf и Spring Boot

#### Описание задания

Научиться интегрировать Thymeleaf с Spring Boot для работы с формами, а также передавать данные между web-приложением и
service-приложением с помощью RestTemplate.

* Создать шаблоны для будущих HTML-страниц. Thymeleaf по умолчанию ожидает, что все HTML-файлы будут находиться в папке
  src/main/resources/templates.
  Пример структуры проекта web-app:

```html
web-app/
├── src/
│   ├── main/
│   │   ├── java/com/example/webapp/
│   │   │   ├── WebAppApplication.java  // основной класс приложения
│   │   │   ├── controller/
│   │   │   │   └── WebAppController.java  // контроллер для работы с формами
│   │   ├── resources/
│   │   │   ├── templates/
│   │   │   │   ├── create.html  // форма создания EntityA
│   │   │   │   └── success.html        // страница успешного создания
│   │   │   ├── application.yml
```

* Создать контроллер для работы с шаблоном

```java

@Controller
@RequestMapping("/entityA")
public class EntityAController {

    private final aService AService;

    // Отображение формы для создания A
    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("entityA", new A());  // Пустой объект для заполнения формы
        return "create";
    }

    // Обработка данных формы и отправка через RestTemplate в service-app
    @PostMapping("/create")
    public String createEntityA(@ModelAttribute A entityA, Model model) {
        A createdEntity = aService.createEntityA(entityA);
        model.addAttribute("entityA", createdEntity);
        return "success";  // После успешного создания возвращаем страницу success
    }
}
```

* Использовать [документацию](https://www.thymeleaf.org/doc/tutorials/2.1/thymeleafspring.html) для создания шаблона,
  должно получиться, что-то подобное для страницы `create`:

```html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <title>Создать А</title>
</head>
<body>
<h1>Создать новую А</h1>

<form th:action="@{/entityA/create}" th:object="${entityA}" method="post">
    <label for="name">Имя:</label>
    <input type="text" id="name" th:field="*{name}"/><br>

    <label for="description">Описание:</label>
    <input type="text" id="description" th:field="*{description}"/><br>

    <button type="submit">Создать</button>
</form>
</body>
</html>
```

И для страницы `success`

```html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <title>Успех</title>
</head>
<body>
<h1>EntityA успешно создана!</h1>
<p>Имя: <span th:text="${entityA.name}"></span></p>
<p>Описание: <span th:text="${entityA.description}"></span></p>

<a href="/entityA/create">Создать ещё</a>
</body>
</html>

```

* Для передачии данных с форм, нужно создать сервис. Данный сервис будет **только** создавать сущность `A+B+C` из данных
  введенных в модель, а также получать созданный обьект `A+B+C` и отрисовывать его на странице
* Сервис должен передавать данные с помощью `RestTemplate` в `service-app`
* Написать юниты
* Написать MVC тесты (интеграционные тесты) для контроллера, по примеру:

```java

@SpringBootTest
@AutoConfigureMockMvc
public class EntityAControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testShowCreateForm() throws Exception {
        mockMvc.perform(get("/entityA/create"))
                .andExpect(status().isOk())
                .andExpect(view().name("createEntityA"))
                .andExpect(model().attributeExists("entityA"));
    }

    @Test
    public void testCreateEntityA() throws Exception {
        mockMvc.perform(post("/entityA/create")
                        .param("name", "Test Entity")
                        .param("description", "Test Description"))
                .andExpect(status().isOk())
                .andExpect(view().name("success"))
                .andExpect(model().attributeExists("entityA"));
    }
}
```

---

### 3.4.0 Задание

#### Работа с несколькими Thymeleaf-страницами

#### Описание задания

Развить навыки работы с Thymeleaf для организации навигации между страницами и работы с несколькими представлениями
данных.

* Создать страницы для всего функционала представленного в `BusinessService` в `service-app`, по отдельной странице на
  каждый метод,
  плюс "Главную страница" с которой можно будет переходить в другие. Примерно структура должна выглядеть так:

```less
web-app/
├── src/
│   ├── main/
│   │   ├── java/com/example/webapp/
│   │   │   ├── WebAppApplication.java
│   │   │   ├── controller/
│   │   │   │   └── EntityAController.java
│   │   ├── resources/
│   │   │   ├── templates/
│   │   │   │   ├── index.html  // главная страница
│   │   │   │   ├── entities.html  // список всех сущностей
│   │   │   │   ├── search.html  // страница поиска
│   │   │   │    ...
│   │   │   │   └── searchResults.html  // результаты поиска
```

* Страницы должны содержать все допустимые для ввода поля.
* Вывод данных должен выводиться для всех видимых в ДТО полей.
  Примеры страниц:
* Главная

```html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <title>Главная страница</title>
</head>
<body>
<h1>Добро пожаловать!</h1>
<a th:href="@{/entityA/all}">Просмотреть все сущности</a><br>
<a th:href="@{/entityA/search}">Поиск сущностей</a>
</body>
</html>

```

* Списки сущностей

```html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <title>Список всех сущностей</title>
</head>
<body>
<h1>Все сущности</h1>
<table>
    <tr>
        <th>Имя</th>
        <th>Описание</th>
    </tr>
    <tr th:each="entity : ${entities}">
        <td th:text="${entity.name}"></td>
        <td th:text="${entity.description}"></td>
    </tr>
</table>
</body>
</html>
```

* Поиск

```html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <title>Поиск сущности</title>
</head>
<body>
<h1>Поиск сущности по имени</h1>
<form th:action="@{/entityA/search/results}" method="get">
    <label for="name">Введите имя:</label>
    <input type="text" id="name" name="name">
    <button type="submit">Поиск</button>
</form>
</body>
</html>
```

* Результаты поиска

```html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <title>Результаты поиска</title>
</head>
<body>
<h1>Результаты поиска</h1>
<table>
    <tr>
        <th>Имя</th>
        <th>Описание</th>
    </tr>
    <tr th:each="entity : ${entities}">
        <td th:text="${entity.name}"></td>
        <td th:text="${entity.description}"></td>
    </tr>
</table>
</body>
</html>
```

И другие необходимые страницы

* Написать юниты
* Написать MVC тесты (интеграционные тесты) для контроллера.
---

### 4.0.0 Задание

#### Использование Docker для запуска приложений

#### Описание задания

Научиться создавать и запускать Docker-контейнеры для каждого модуля проекта, а также использовать Docker-compose для
организации многомодульных приложений в контейнерах.

* Для работы с Docker и Docker-compose на Windows необходимо
    * Скачать и установить [Docker Desktop](https://www.docker.com/products/docker-desktop/) с официального сайта.
    * При первом запуске Docker Desktop может предложить включить WSL 2 и перезагрузить компьютер.
    * После установки и перезагрузки система будет готова к работе с Docker и Docker-compose.
* Для каждого модуля вашего проекта необходимо создать свой Docker-файл. Примерно так:

```less
project-root/
├── data-base-app/
│   ├── src/
│   ├── Dockerfile  // Docker-файл для data-base-app
│   └── pom.xml  // Maven файл для data-base-app
│
├── web-app/
│   ├── src/
│   ├── Dockerfile  // Docker-файл для web-app
│   └── pom.xml  // Maven файл для web-app
│
├── service-app/
│   ├── src/
│   ├── Dockerfile  // Docker-файл для service-app
│   └── pom.xml  // Maven файл для service-app
```

* Содержимое Docker файлаб на примере `data-base-app`:

```shell
# Используем базовый образ с OpenJDK
FROM openjdk:17-jdk-alpine

# Устанавливаем рабочую директорию внутри контейнера
WORKDIR /build

# Копируем скомпилированный jar-файл приложения
COPY target/data-base-app.jar /build/data-base-app.jar

# Указываем команду запуска приложения
ENTRYPOINT ["java", "-jar", "data-base-app.jar"]
```

* Создать общий управляющий файл `docker-compose`:

```less
project-root/
│
├── data-base-app/
│   ├── src/
│   ├── Dockerfile  // Docker-файл для data-base-app
│   └── pom.xml  // Maven файл для data-base-app
│
├── web-app/
│   ├── src/
│   ├── Dockerfile  // Docker-файл для web-app
│   └── pom.xml  // Maven файл для web-app
│
├── service-app/
│   ├── src/
│   ├── Dockerfile  // Docker-файл для service-app
│   └── pom.xml  // Maven файл для service-app
│
├── docker-compose.yml  // Docker Compose файл для всех модулей
├── pom.xml  // Основной Maven файл для всего проекта
└── README.md  // Документация проекта
```

Пример содержимого:

```shell
version: '3'
services:
  web-app:
    build: ./web-app
    ports:
      - "8080:8080" // порты указать согласно указанным в настройках сервисов
    depends_on:
      - service-app

  service-app:
    build: ./service-app
    ports:
      - "8080:8080" // порты указать согласно указанным в настройках сервисов
      
  data-base-app:
    build: ./data-base-app
    ports:
      - "8081:8081" // порты указать согласно указанным в настройках сервисов
```
#### Основные команды для работы с Docker и Docker-compose 
* Сборка и запуск контейнеров:
```bash
docker-compose up --build
```
Эта команда соберёт образы для каждого сервиса и запустит их.

* Остановка контейнеров:
```bash
docker-compose down
```
Остановит и удалит все запущенные контейнеры.
* Просмотр логов:
```bash
docker-compose logs
```
Показывает логи всех контейнеров, что полезно для диагностики.
* Запуск контейнеров в фоновом режиме:
```bash
docker-compose up -d
```
Запускает контейнеры в фоновом режиме.
* Диагностика состояния контейнеров:
```bash
docker-compose ps
```
Показывает текущий статус запущенных контейнеров.

* Просмотр работающих контейнеров:
```bash
docker ps
```
Показывает информацию обо всех активных контейнерах.

---

### 4.1.0 Подключение базы данных в Docker-compose

#### Описание задания:
Научиться добавлять контейнер с базой данных в `docker-compose` и настраивать приложение для работы с этой базой данных.

#### Шаги выполнения задания:

1. **Добавление контейнера с базой данных в docker-compose**:
  - В `docker-compose.yml` добавить новый сервис для базы данных, например, PostgreSQL.
  - Пример конфигурации для PostgreSQL:

   ```yaml
   version: '3'
   services:
     database:
       image: postgres:15
       environment:
         POSTGRES_DB: mydatabase
         POSTGRES_USER: myuser
         POSTGRES_PASSWORD: mypassword
       ports:
         - "5432:5432"
   ```

2. **Переключение data-base-app на новую БД**:
  - В приложении `data-base-app` обновить настройки подключения к базе данных, чтобы использовать контейнер `database` из `docker-compose`.
  - В `application.yml` для `data-base-app` указать следующие параметры подключения:

   ```yaml
   spring:
     datasource:
       url: jdbc:postgresql://database:5432/mydatabase
       username: myuser
       password: mypassword
   ```

3. **Проверка запуска и диагностика**:
  - Собрать и запустить проект с помощью `docker-compose`:
    ```bash
    docker-compose up --build
    ```
  - Убедиться, что `data-base-app` успешно подключается к контейнеру базы данных `database`.
  - Использовать `docker-compose logs` и `docker-compose ps` для диагностики.

---

### 4.2.0 Подключение Liquibase в Docker-compose

#### Описание задания:
Научиться добавлять контейнер с Liquibase в `docker-compose` и настраивать его для автоматического выполнения миграций базы данных, используемой приложением `data-base-app`.

#### Шаги выполнения задания:

1. **Добавление контейнера Liquibase в docker-compose**:
  - В `docker-compose.yml` добавить новый сервис для Liquibase, настроив его так, чтобы он работал с базой данных `database` (созданной в задании 4.1.0).
  - Пример конфигурации для Liquibase:

   ```yaml
   version: '3'
   services:
     database:
       image: postgres:15
       environment:
         POSTGRES_DB: mydatabase
         POSTGRES_USER: myuser
         POSTGRES_PASSWORD: mypassword
       ports:
         - "5432:5432"

     liquibase:
       image: liquibase/liquibase:latest
       environment:
         LIQUIBASE_URL: jdbc:postgresql://database:5432/mydatabase
         LIQUIBASE_USERNAME: myuser
         LIQUIBASE_PASSWORD: mypassword
         LIQUIBASE_CHANGELOG_FILE: /liquibase/changelog/db.changelog.xml
       volumes:
         - ./liquibase/changelog:/liquibase/changelog
       depends_on:
         - database
       entrypoint: ["liquibase", "--changeLogFile=/liquibase/changelog/db.changelog.xml", "update"]
   ```

2. **Настройка Liquibase для автоматического применения миграций**:
  - Создать директорию `liquibase/changelog` в корневой папке проекта.
  - Добавить файл `db.changelog.xml` в папку `liquibase/changelog`, описывающий миграции базы данных, например:

   ```xml
   <?xml version="1.0" encoding="UTF-8"?>
   <databaseChangeLog
       xmlns="http://www.liquibase.org/xml/ns/dbchangelog"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.liquibase.org/xml/ns/dbchangelog
           http://www.liquibase.org/xml/ns/dbchangelog/dbchangelog-3.8.xsd">
   
       <changeSet id="1" author="student">
           <createTable tableName="example_table">
               <column name="id" type="INT" autoIncrement="true">
                   <constraints primaryKey="true"/>
               </column>
               <column name="name" type="VARCHAR(255)">
                   <constraints nullable="false"/>
               </column>
           </createTable>
       </changeSet>
   </databaseChangeLog>
   ```

3. **Настройка data-base-app для работы с изменённой БД**:
  - Убедитесь, что `data-base-app` настроен для взаимодействия с базой данных, которую инициализирует Liquibase.
  - Запустите проект:

    ```bash
    docker-compose up --build
    ```

4. **Диагностика**:
  - Убедитесь, что Liquibase успешно применил миграции. Используйте команды:
    ```bash
    docker-compose logs liquibase
    docker-compose ps
    ```

---

### 4.3.0 Балансировка нагрузки с несколькими экземплярами приложений

#### Описание задания:
Научиться создавать несколько копий приложений в `docker-compose` и настраивать балансировщик нагрузки, который будет распределять трафик между этими экземплярами на основе простого условия (например, round-robin).

#### Шаги выполнения задания:

1. **Дублирование сервисов в `docker-compose.yml`**:
  - Создайте по 2 копии каждого приложения (`data-base-app` и `service-app`) в файле `docker-compose.yml`.
  - Назовите сервисы уникально, например, `data-base-app-1`, `data-base-app-2`, `service-app-1`, `service-app-2`.

2. **Добавление балансировщика нагрузки**:
  - Добавьте сервис балансировщика нагрузки (например, Nginx) в `docker-compose.yml`.
  - Настройте его для распределения трафика между копиями приложений на основе простого условия, такого как round-robin.

3. **Настройка сети**:
  - Убедитесь, что все сервисы находятся в одной сети Docker, чтобы они могли взаимодействовать между собой.

4. **Конфигурация Nginx**:
  - Создайте файл конфигурации Nginx (`nginx.conf`) с настройками для балансировки нагрузки.
  - Монтируйте этот файл в контейнер Nginx через volume.

5. **Запуск и проверка**:
  - Запустите `docker-compose` и убедитесь, что балансировщик корректно распределяет трафик между экземплярами приложений.
  - Проверьте доступность приложений через балансировщик.

#### Пример конфигурации `docker-compose.yml`:

    * web-app - 8091
    * data-base-app - 8101
    * service-app - 8111

```yaml
version: '3'
services:
  # Копии data-base-app
  data-base-app-1:
    build: ./data-base-app
    ports:
      - "8201:8101"
    depends_on:
      - service-app-1
    networks:
      - app-network

  data-base-app-2:
    build: ./data-base-app
    ports:
      - "8301:8101"
    depends_on:
      - service-app-2
    networks:
      - app-network

  # Копии service-app
  service-app-1:
    build: ./service-app
    ports:
      - "8211:8111"
    networks:
      - app-network

  service-app-2:
    build: ./service-app
    ports:
      - "8311:8111"
    networks:
      - app-network

  # Балансировщик нагрузки
  nginx:
    image: nginx:latest
    ports:
      - "80:80"
    volumes:
      - ./nginx.conf:/etc/nginx/nginx.conf:ro
    depends_on:
      - data-base-app-1
      - data-base-app-2
      - service-app-1
      - service-app-2
    networks:
      - app-network

networks:
  app-network:
    driver: bridge
```

#### Пример конфигурации `nginx.conf`:

```nginx
# Определение событий
events { }

# HTTP-секция для настройки upstream и сервера
http {
    # Конфигурация upstream, которая определяет backend-сервисы для балансировки
    upstream data_base_app {
        # Два экземпляра приложения web-app, работающие на портах 8201 и 8301
        server data-base-app-1:8201;
        server data-base-app-2:8301;
        
        # Можно также указать дополнительные параметры балансировки, такие как вес:
        # server data-base-app-1:8201 weight=2; 
        # server data-base-app-2:8301;
    }

    # Настройка сервера, который будет обрабатывать входящие запросы
    server {
        listen 80;  # Порт, на котором Nginx будет слушать входящие HTTP-запросы

        # Определение обработки для всех входящих путей (location /)
        location / {
            # Передача всех запросов к upstream-балансировщику web_app
            proxy_pass http://data_base_app;
            
            # Настройка заголовков для проксирования
            proxy_set_header Host $host;
            proxy_set_header X-Real-IP $remote_addr;
            proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
            proxy_set_header X-Forwarded-Proto $scheme;

            # Настройка тайм-аутов
            proxy_connect_timeout 10s;
            proxy_read_timeout 30s;
            proxy_send_timeout 30s;
        }
    }
}

```

#### Пояснения:

- **Сервисы `data-base-app-1` и `data-base-app-2`**:
  - Оба сервиса строятся из директории `./data-base-app`.
  - Экземпляры доступны на портах `8201` и `8301` хоста соответственно.
  - Зависят от соответствующих экземпляров `service-app`.

- **Сервисы `service-app-1` и `service-app-2`**:
  - Оба сервиса строятся из директории `./service-app`.
  - Экземпляры доступны на портах `8211` и `8311` хоста соответственно.

- **Сервис `nginx`**:
  - Использует официальный образ Nginx.
  - Конфигурационный файл `nginx.conf` монтируется в контейнер.
  - Балансирует трафик между `data-base-app-1` и `data-base-app-2` с использованием метода round-robin.

- **Сеть `app-network`**:
  - Все сервисы подключены к одной сети для обеспечения возможности взаимодействия.

#### Инструкции по запуску:

1. **Создание конфигурационного файла Nginx**:
  - В корневой директории проекта создайте файл `nginx.conf` и вставьте приведённый выше пример конфигурации.

2. **Запуск `docker-compose`**:
   ```bash
   docker-compose up --build
   ```
  - Эта команда соберёт образы для всех сервисов и запустит их.

3. **Проверка работы балансировщика**:
  - Откройте браузер и перейдите по адресу `http://localhost`.
  - Обновите страницу несколько раз и убедитесь, что запросы распределяются между `data-base-app-1` и `data-base-app-2`.

4. **Диагностика**:
  - Просмотрите логи Nginx:
    ```bash
    docker-compose logs nginx
    ```
  - Проверьте статус всех контейнеров:
    ```bash
    docker-compose ps
    ```

#### Дополнительные команды:

- **Запуск контейнеров в фоновом режиме**:
  ```bash
  docker-compose up -d
  ```

- **Остановка контейнеров**:
  ```bash
  docker-compose down
  ```

- **Просмотр логов конкретного сервиса**:
  ```bash
  docker-compose logs <service-name>
  ```

#### Важные моменты:

- Убедитесь, что все сервисы правильно связаны через сеть `app-network`.
- Проверьте, что порты, используемые сервисами, не заняты другими приложениями на вашей машине.
- При изменении конфигурации `nginx.conf` перезапустите контейнер Nginx для применения изменений:
  ```bash
  docker-compose restart nginx
  ```
---

### 5.0.0: Настройка базовой авторизации для сервиса web-app и ограничение доступа к сервисам data-base-app и service-app

#### Описание задания:
Добавить базовую HTTP-авторизацию для web-app, чтобы защитить его от несанкционированного доступа, а также настроить data-base-app и service-app таким образом, чтобы они не были доступны из внешней сети.

#### Шаги выполнения задания:

**Добавить базовую авторизацию для web-app:**

  Настройте HTTP Basic Authentication для приложения `web-app`, чтобы доступ к нему был возможен только для авторизованных пользователей.
  Используйте конфигурацию Spring Security для настройки логина и пароля.
  Проверьте, что `web-app` запрашивает логин и пароль при каждом обращении к ресурсам.
```java
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .anyRequest().authenticated()
                .and()
            .httpBasic();  // Включаем базовую HTTP-авторизацию
    }
}

```
**Ограничить доступ к сервисам `data-base-app` и `service-app`:**

  Настройте docker-compose так, чтобы `data-base-app` и `service-app` были недоступны извне. Это можно сделать, используя сеть Docker и скрыв порты этих сервисов.
  Только `web-app` должен иметь возможность взаимодействовать с этими сервисами через внутреннюю сеть Docker.
```yaml
version: '3.8'

services:
  web-app:
    build: ./web-app
    ports:
      - "..."  # Открываем порт для web-app
    environment:
      - SPRING_PROFILES_ACTIVE=prod
    networks:
      - internal

  data-base-app:
    build: ./data-base-app
    networks:
      - internal
    # Порты не указываем, чтобы исключить доступ извне

  service-app:
    build: ./service-app
    networks:
      - internal
    # Порты не указываем, чтобы исключить доступ извне

networks:
  internal:
    driver: bridge
```

**Запустите Docker Compose и убедитесь, что:**
* При попытке открыть web-app в браузере или через curl требуется ввести логин и пароль. data-base-app и service-app не доступны извне, но web-app по-прежнему может с ними взаимодействовать.

**Создайте класс конфигурации безопасности WebSecurityConfig.java:**

  В web-app создайте файл WebSecurityConfig.java, который будет настраивать авторизацию и определять пользователя с помощью UserDetailsService.
  ```java
  import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
            .authorizeRequests()
            .anyRequest().authenticated()
            .and()
            .httpBasic(); // Включаем базовую авторизацию
  }

  @Bean
  @Override
  public UserDetailsService userDetailsService() {
    InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
    manager.createUser(User.withDefaultPasswordEncoder()
            .username("admin")
            .password("admin123")
            .roles("USER")
            .build());
    return manager;
  }
}
  ```

**Обновление конфигурации безопасности для использования кастомной страницы логина**

* Измените WebSecurityConfig.java, чтобы указать кастомную страницу входа (/login), которую мы добавим в виде Thymeleaf-шаблона:
```java
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .anyRequest().authenticated()
                .and()
            .formLogin()
                .loginPage("/login")   // Кастомная страница входа
                .permitAll()
                .and()
            .logout()
                .permitAll();
    }

    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withDefaultPasswordEncoder()
                .username("admin")
                .password("admin123")
                .roles("USER")
                .build());
        return manager;
    }
}

```

**Создание шаблона страницы логина на Thymeleaf**

* Добавьте HTML-файл login.html в папку src/main/resources/templates/ (путь, ожидаемый Thymeleaf).
* Шаблон login.html будет выглядеть следующим образом:
```java
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <title>Вход</title>
</head>
<body>
    <h2>Пожалуйста, войдите</h2>
    <form th:action="@{/login}" method="post">
        <div>
            <label for="username">Имя пользователя:</label>
            <input type="text" id="username" name="username" required />
        </div>
        <div>
            <label for="password">Пароль:</label>
            <input type="password" id="password" name="password" required />
        </div>
        <div>
            <button type="submit">Войти</button>
        </div>
        <div th:if="${param.error}">
            <p style="color:red;">Неправильное имя пользователя или пароль</p>
        </div>
        <div th:if="${param.logout}">
            <p style="color:green;">Вы успешно вышли</p>
        </div>
    </form>
</body>
</html>

```

Пояснение:

  Поле `th:action="@{/login}"` указывает, что данные формы будут отправлены на `URL /login`, который обрабатывается Spring Security.
  Если параметры error или logout присутствуют в URL, будут отображены сообщения об ошибке или успешном выходе соответственно.
  
---
### Задание 5.1.0: Ограничение доступа к ручкам `web-app` на основе ролей пользователей

В этом задании вы научитесь добавлять различные роли пользователей и ограничивать доступ к определённым эндпоинтам (`ручкам`) на основе этих ролей с использованием Spring Security.

#### Описание задания
Настройте приложение таким образом, чтобы к определённым URL могли обращаться только пользователи с конкретными ролями. Для этого добавим двух пользователей:
- **Admin** с ролью `ROLE_ADMIN` для доступа к административным страницам.
- **Client** с ролью `ROLE_CLIENT` для доступа к страницам клиента.

#### Шаги выполнения

1. **Обновите конфигурацию безопасности**  
   В `WebSecurityConfig.java` добавьте конфигурацию для ролей пользователей и настройте доступ к контроллерам по ролям.

   ```java
   import org.springframework.context.annotation.Bean;
   import org.springframework.context.annotation.Configuration;
   import org.springframework.security.config.annotation.web.builders.HttpSecurity;
   import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
   import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
   import org.springframework.security.core.userdetails.User;
   import org.springframework.security.core.userdetails.UserDetailsService;
   import org.springframework.security.provisioning.InMemoryUserDetailsManager;

   @Configuration
   @EnableWebSecurity
   public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

       @Override
       protected void configure(HttpSecurity http) throws Exception {
           http
               .authorizeRequests()
                   .antMatchers("/admin/**").hasRole("ADMIN")      // Доступ только для Admin
                   .antMatchers("/client/**").hasRole("CLIENT")    // Доступ только для Client
                   .anyRequest().authenticated()
                   .and()
               .formLogin()
                   .loginPage("/login")
                   .permitAll()
                   .and()
               .logout()
                   .permitAll();
       }

       @Bean
       @Override
       public UserDetailsService userDetailsService() {
           InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
           manager.createUser(User.withDefaultPasswordEncoder()
                   .username("admin")
                   .password("admin123")
                   .roles("ADMIN")
                   .build());
           manager.createUser(User.withDefaultPasswordEncoder()
                   .username("client")
                   .password("client123")
                   .roles("CLIENT")
                   .build());
           return manager;
       }
   }
   ```

  - **Пояснение к коду**:
    - `antMatchers("/admin/**").hasRole("ADMIN")` – только пользователи с ролью `ADMIN` могут обращаться к ручкам, начинающимся с `/admin`.
    - `antMatchers("/client/**").hasRole("CLIENT")` – только пользователи с ролью `CLIENT` могут обращаться к ручкам, начинающимся с `/client`.

2. **Создайте контроллеры с доступом по ролям**  
   В `web-app` создайте два контроллера, `AdminController` и `ClientController`, которые будут обрабатывать запросы для пользователей с разными ролями.

  - **AdminController**:
    ```java
    import org.springframework.stereotype.Controller;
    import org.springframework.web.bind.annotation.GetMapping;
    import org.springframework.web.bind.annotation.RequestMapping;

    @Controller
    @RequestMapping("/admin")
    public class AdminController {

        @GetMapping("/dashboard")
        public String adminDashboard() {
            return "admin-dashboard";  // Возвращает страницу для администратора
        }
    }
    ```

  - **ClientController**:
    ```java
    import org.springframework.stereotype.Controller;
    import org.springframework.web.bind.annotation.GetMapping;
    import org.springframework.web.bind.annotation.RequestMapping;

    @Controller
    @RequestMapping("/client")
    public class ClientController {

        @GetMapping("/home")
        public String clientHome() {
            return "client-home";  // Возвращает страницу для клиента
        }
    }
    ```

3. **Создайте Thymeleaf-шаблоны для ролей**  
   Добавьте страницы для каждой роли (`admin-dashboard.html` и `client-home.html`) в папку `src/main/resources/templates/` для отображения соответствующих страниц.

  - **admin-dashboard.html**:
    ```html
    <!DOCTYPE html>
    <html xmlns:th="http://www.thymeleaf.org">
    <head>
        <title>Admin Dashboard</title>
    </head>
    <body>
        <h1>Добро пожаловать, Администратор!</h1>
        <p>Эта страница доступна только пользователям с ролью ADMIN.</p>
    </body>
    </html>
    ```

  - **client-home.html**:
    ```html
    <!DOCTYPE html>
    <html xmlns:th="http://www.thymeleaf.org">
    <head>
        <title>Client Home</title>
    </head>
    <body>
        <h1>Добро пожаловать, Клиент!</h1>
        <p>Эта страница доступна только пользователям с ролью CLIENT.</p>
    </body>
    </html>
    ```

4. **Проверка работы**
  - Перейдите на `http://localhost:8091/admin/dashboard` и `http://localhost:8091/client/home`.
  - Войдите в систему под разными пользователями:
    - Пользователь `admin` (`admin/admin123`) должен иметь доступ к `/admin/dashboard`.
    - Пользователь `client` (`client/client123`) должен иметь доступ к `/client/home`.
  - Убедитесь, что пользователи не могут получить доступ к страницам, для которых у них нет прав.

#### Задание завершено
Теперь приложение предоставляет доступ к страницам на основе ролей пользователей.

---

### Итоговая программа обучения: создание и поддержка сервисов на Spring Boot с интеграцией Docker и основами безопасного доступа

#### Программа курса
Cтуденты прошли интенсивную практическую программу, охватывающую ключевые аспекты разработки современных Java приложений на основе Spring Boot. Обучение состояло из следующих этапов:

**Основы Spring Boot и создание REST API:**
  - Студенты освоили создание RESTful сервисов и базовых контроллеров.
  - Научились работать с аннотациями Spring, структурой контроллеров и маршрутизацией.
  - Использовали инструменты для тестирования API, такие как Postman.

**Основы работы с Базами данных и создание миграций:**
  - Освоили работу с `H2`, `PostgreSql` базами данных
  - Освоили ORM системы Hibernate в интеграции Spring Boot
  - Освоили язык запросов к БД `SQL` и создание на нем скриптов

**Работа с Thymeleaf для построения пользовательского интерфейса:**
  - Интеграция `Thymeleaf` с `Spring Boot` для создания форм и отображения данных.
  - Разработка страниц для взаимодействия с сущностями, таких как создание и просмотр данных, а также поддержка нескольких страниц.
  - Практическое использование шаблонов и обмен данными между `web-app` и `service-app` через `RestTemplate`.

**Тестирование:**
  - Написание юнит и интеграционных тестов для контроллеров, сервисов и базовых сценариев.
  - Проведение тестов с использованием `MockMvc` и Spring Test для обеспечения надёжной работы контроллеров.

**Docker и Docker-compose:**
  - Создание Docker файлов для контейнеризации модулей.
  - Развёртывание приложения с использованием `docker-compose`, интеграция с отдельными сервисами, такими как база данных и Liquidbase для управления миграциями.

**Настройка балансировки нагрузки и масштабирование контейнеров.**
  - Балансировака с помощью Nginx и `docker-compose`

**Безопасность и авторизация:**
  - Настройка базовой авторизации для приложения, добавление авторизации на основе ролей с помощью Spring Security.
  - Ограничение доступа к отдельным контроллерам в зависимости от ролей пользователей.
  - Настройка доступа к приложениям только изнутри сети, что повышает безопасность и изолирует сервисы от внешнего мира.

#### Итог обучения: подготовка к диплому
Студенты теперь уверенно владеют основами создания микросервисной архитектуры на `Spring Boot`, умеют работать с `Docker` и `Docker-compose`, а также понимают, как правильно добавлять уровень безопасности в своё приложение. Они готовы к выполнению дипломного проекта, в котором предстоит:
- Объединить все полученные знания, создать полноценное приложение и настроить его окружение.
- Применить техники работы с базой данных, безопасностью, тестированием и развертыванием, отточенные в ходе обучения.

С этим опытом студенты способны справиться с самостоятельной разработкой проекта, развернуть его в контейнерах, подключить роли и базовую авторизацию, обеспечить контейнеризацию и настройку взаимодействия между сервисами.

---

#### Рекомендации для дальнейшего развития:

Не забывайте о теории, обращаайтесь к документации и проверенным источникам, что бы закрывать пробелы в теории. Пречитывайте главы книг в которых у вас были пробелы или вы их обнаружили после прохождения собеседований 

Чтобы продолжить развивать конкурентные навыки, рекомендую:

**Изучить Spring Cloud для микросервисных архитектур:** 

Такие модули, как Spring Cloud Config, Eureka для регистрации сервисов и Ribbon для продвинутой балансировки.

**Освоить CI/CD практики:** 

Такие инструменты, как Jenkins или GitLab CI, помогут автоматически собирать и развёртывать приложения.

**Углубить знания в области безопасности:** 

Изучите JWT (JSON Web Tokens), OAuth 2.0 и Spring Security для авторизации и аутентификации, а также настройку API Gateway.

**Практика с Kubernetes:** 

Для продвинутого управления контейнерами и микросервисами.

Этот курс дал уверенную базу, и, углубляя знания в этих областях, студенты могут значительно повысить свою конкурентоспособность на рынке труда.