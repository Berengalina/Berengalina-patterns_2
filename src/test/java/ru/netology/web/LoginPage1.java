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


    @Test
    void shouldLogin(){
        open("http://localhost:9999");
        $("[data-test-id=login] .input__control").setValue(DataHelper.Registration.registerUser().getLogin());
        $("[data-test-id=password] .input__control").setValue(DataHelper.Registration.registerUser().getPassword());
        $("[data-test-id=action-login] .button__text").click();
        $("h2").shouldHave(text("Личный кабинет")).shouldBe(visible);
    }
}