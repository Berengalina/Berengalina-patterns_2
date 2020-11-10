package ru.netology.web;


import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static io.restassured.RestAssured.given;


public class LoginPage1 {


    public static RequestSpecification requestSpec = new RequestSpecBuilder()
            .setBaseUri("http://localhost")
            .setPort(9999)
            .setAccept(ContentType.JSON)
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();

    @BeforeAll
    static void setUpAll() {
        // сам запрос
        given() // "дано"
                .spec(requestSpec) // указываем, какую спецификацию используем
                .body(DataHelper.Registration.generateData("ru"))  // передаём в теле объект, который будет преобразован в JSON
                .when() // "когда"
                .post("/api/system/users") // на какой путь, относительно BaseUri отправляем запрос
                .then() // "тогда ожидаем"
                .statusCode(200); // код 200 OK
    }

    @Test
    void shouldLogin(){
        open("http://localhost:9999");
        $("[data-test-id=login] .input__control").setValue(DataHelper.Registration.generateData("ru"));
        $("[data-test-id=password] .input__control").setValue(DataHelper.Registration.generateData("ru"));
        $("[data-test-id=action-login] .button__text").click();
        $("h2").shouldHave(text("Личный кабинет")).shouldBe(visible);
    }
}