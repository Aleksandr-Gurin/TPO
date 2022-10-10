package org.example.usecase;

import org.example.Utils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CookieTest {
    @BeforeAll
    public static void prepareDrivers() {
        Utils.prepareDrivers();
    }

    @Test
    void checkCookie() {
        List<WebDriver> drivers = Utils.getDrivers();
        drivers.forEach(webDriver -> {
            webDriver.get(Utils.BASE_URL);
            WebElement text = Utils.getElementBySelector(webDriver, By.xpath(".//*[@id=\"cookie_popup\"]/div"));
            assertEquals("Этот сайт (как и многие другие) использует файлы куки. Пользуясь Услугами, Вы соглашаетесь с использованием файлов куки. Пожалуйста, прочитайте Политику куки, если Вас интересуют подробности.", text.getText());
            Utils.getElementBySelector(webDriver, By.xpath(".//*[@id=\"cookie_popup\"]/div/a[2]/span")).click();
            webDriver.quit();
        });
    }

}
