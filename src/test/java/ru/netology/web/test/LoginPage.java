package ru.netology.web.test;

import lombok.val;
import org.junit.jupiter.api.Test;
import ru.netology.web.data.DataHelper;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;


public class LoginPage {

    @Test
    void shouldLogin(){
        val user = DataHelper.registerValidUser("ru");
        open("http://localhost:9999");
        $("[data-test-id=login] .input__control").setValue(user.getLogin());
        $("[data-test-id=password] .input__control").setValue(user.getPassword());
        $("[data-test-id=action-login] .button__text").click();
        $("h2").shouldHave(text("Личный кабинет")).shouldBe(visible);
    }

    @Test
    void shouldNotLoginBlock(){
        val user = DataHelper.registerBlockedUser("ru");
        open("http://localhost:9999");
        $("[data-test-id=login] .input__control").setValue(user.getLogin());
        $("[data-test-id=password] .input__control").setValue(user.getPassword());
        $("[data-test-id=action-login] .button__text").click();
        $("[data-test-id=error-notification] .notification__content").shouldHave(text("Пользователь заблокирован")).shouldBe(visible);
    }

    @Test
    void shouldNotLoginWrongLogin(){
        val user = DataHelper.registerWrongLoginUser("ru");
        open("http://localhost:9999");
        $("[data-test-id=login] .input__control").setValue(user.getLogin());
        $("[data-test-id=password] .input__control").setValue(user.getPassword());
        $("[data-test-id=action-login] .button__text").click();
        $("[data-test-id=error-notification] .notification__content").shouldHave(text("Неверно указан логин или пароль")).shouldBe(visible);
    }

    @Test
    void shouldNotLoginWrongPassword(){
        val user = DataHelper.registerWrongPasswordUser("ru");
        open("http://localhost:9999");
        $("[data-test-id=login] .input__control").setValue(user.getLogin());
        $("[data-test-id=password] .input__control").setValue(user.getPassword());
        $("[data-test-id=action-login] .button__text").click();
        $("[data-test-id=error-notification] .notification__content").shouldHave(text("Неверно указан логин или пароль")).shouldBe(visible);
    }

    @Test
    void shouldNotLoginNotExistUser(){
        val user = DataHelper.registerNotExistUser("ru");
        open("http://localhost:9999");
        $("[data-test-id=login] .input__control").setValue(user.getLogin());
        $("[data-test-id=password] .input__control").setValue(user.getPassword());
        $("[data-test-id=action-login] .button__text").click();
        $("[data-test-id=error-notification] .notification__content").shouldHave(text("Неверно указан логин или пароль")).shouldBe(visible);
    }
}