package ru.netology.delivery;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selectors;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import ru.netology.delivery.data.DataGenerator;
import ru.netology.delivery.data.Registration;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

class CardDeliveryTest {

    @BeforeEach
    void setUp() {
        Configuration.browserSize = "1920x1080";
        Configuration.holdBrowserOpen = true; // Для отладки
        open("http://localhost:9999");
    }

    @Test
    @DisplayName("Запланировать встречу с последующим перепланированием")
    void shouldPlanMeetingAndReplan() {
        var user = Registration.generateUser("ru");
        var firstDate = DataGenerator.generateDate(4);
        var secondDate = DataGenerator.generateDate(7);

        // Первая планировка
        $("[data-test-id='city'] input").setValue(user.getCity());
        $("[data-test-id='date'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(firstDate);
        $("[data-test-id='name'] input").setValue(user.getName());
        $("[data-test-id='phone'] input").setValue(user.getPhone());
        $("[data-test-id='agreement']").click();
        $(Selectors.withText("Запланировать")).click();

        $("[data-test-id='success-notification']")
                .shouldBe(visible, Duration.ofSeconds(15))
                .shouldHave(text("Успешно запланирована на " + firstDate));

        $("[data-test-id='success-notification'] button").click();

        // Перепланировка
        $("[data-test-id='date'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(secondDate);
        $(Selectors.withText("Запланировать")).click();

        $("[data-test-id='replan-notification'] button").click();

        $("[data-test-id='success-notification']")
                .shouldHave(exactText("Успешно запланирована на " + secondDate));
    }
}
