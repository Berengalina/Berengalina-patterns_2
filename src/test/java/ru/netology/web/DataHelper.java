package ru.netology.web;

import com.github.javafaker.Faker;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import java.util.Locale;

import static io.restassured.RestAssured.given;

public class DataHelper {
    private DataHelper() {}

    public static class Registration {
        private Registration() {}

        public static RequestSpecification requestSpec = new RequestSpecBuilder()
                .setBaseUri("http://localhost")
                .setPort(9999)
                .setAccept(ContentType.JSON)
                .setContentType(ContentType.JSON)
                .log(LogDetail.ALL)
                .build();


        public static RegistrationDto generateData(String locale) {
            Faker faker = new Faker(new Locale("ru"));
                given() // "дано"
                        .spec(requestSpec) // указываем, какую спецификацию используем
                        .body(new RegistrationDto (faker.name().username(),faker.internet().password(),"active" ) ) // передаём в теле объект, который будет преобразован в JSON
                        .when() // "когда"
                        .post("/api/system/users") // на какой путь, относительно BaseUri отправляем запрос
                        .then() // "тогда ожидаем"
                        .statusCode(200); // код 200 OK

             return

//
//                     new RegistrationDto(
//                    faker.name().username(),
//                    faker.internet().password(),
//                    "active"

            );
        }
    }
}