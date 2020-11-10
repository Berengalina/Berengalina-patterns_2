package ru.netology.web;


import com.github.javafaker.Faker;

import java.util.Locale;

public class DataHelper {
    private DataHelper() {}

    public static class Registration {
        private Registration() {}

        public static RegistrationDto generateData(String locale) {
            Faker faker = new Faker(new Locale("ru"));
            return new RegistrationDto(
                    faker.name().username(),
                    faker.internet().password(),
                    "active"

            );
        }
    }
}