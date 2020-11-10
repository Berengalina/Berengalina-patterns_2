package ru.netology.web;

import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;


public class LoginPage1 {

    @Test
    void shouldLogin(){
        open("http://localhost:9999");
        $("[data-test-id=login] .input__control").setValue(DataHelper.Registration.registerValidUser("ru").getLogin());
        $("[data-test-id=password] .input__control").setValue(DataHelper.Registration.registerValidUser("ru").getPassword());
        $("[data-test-id=action-login] .button__text").click();
        $("h2").shouldHave(text("Личный кабинет")).shouldBe(visible);
    }
}