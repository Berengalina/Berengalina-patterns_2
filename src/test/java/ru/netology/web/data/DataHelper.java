package ru.netology.web.data;

import com.github.javafaker.Faker;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import lombok.val;

import java.util.Locale;

import static io.restassured.RestAssured.given;

public class DataHelper {
    private DataHelper() {
    }

    public static RequestSpecification requestSpec = new RequestSpecBuilder()
            .setBaseUri("http://localhost")
            .setPort(9999)
            .setAccept(ContentType.JSON)
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();


    public static RegistrationDto registerValidUser(String locale) {
        Faker faker = new Faker((new Locale("ru")));
        val validUser = new RegistrationDto(
                faker.name().username(),
                faker.internet().password(),
                "active"
        );
        setUp(validUser);
        return validUser;
    }

    public static RegistrationDto registerBlockedUser(String locale) {
        Faker faker = new Faker((new Locale("ru")));
        val validUser = new RegistrationDto(
                faker.name().username(),
                faker.internet().password(),
                "blocked"
        );
        setUp(validUser);
        return validUser;
    }

    public static RegistrationDto registerWrongLoginUser(String locale) {
        Faker faker = new Faker((new Locale("ru")));
        val validUser = new RegistrationDto(
                faker.name().username(),
                "password",
                "active"
        );
        val invalidUser = new RegistrationDto(
                faker.name().username(),
                "password",
                "active"
        );
        setUp(validUser);
        return invalidUser;
    }

    public static RegistrationDto registerWrongPasswordUser(String locale) {
        Faker faker = new Faker((new Locale("ru")));
        val validUser = new RegistrationDto(
                "Анна.Белоусова",
                faker.internet().password(),
                "active"
        );
        val invalidUser = new RegistrationDto(
                "Анна.Белоусова",
                faker.internet().password(),
                "active"
        );
        setUp(validUser);
        return invalidUser;
    }

    public static RegistrationDto registerNotExistUser(String locale) {
        Faker faker = new Faker((new Locale("ru")));
        val validUser = new RegistrationDto(
                faker.name().username(),
                faker.internet().password(),
                "active"
        );
        return validUser;
    }

    public static void setUp(RegistrationDto registeredDto) {
        given() // "дано"
                .spec(requestSpec) // указываем, какую спецификацию используем
                .body(registeredDto) // передаём в теле объект, который будет преобразован в JSON
                .when() // "когда"
                .post("/api/system/users") // на какой путь, относительно BaseUri отправляем запрос
                .then() // "тогда ожидаем"
                .statusCode(200); // код 200 OK
    }

}
