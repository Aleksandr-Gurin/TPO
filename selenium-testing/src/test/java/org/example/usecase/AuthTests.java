package org.example.usecase;

import org.example.Utils;
import org.example.model.MainPage;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AuthTests {

    @BeforeAll
    public static void prepareDrivers() {
        Utils.prepareDrivers();
    }

    @Test
    @Order(1)
    void loginTest() {
        List<WebDriver> drivers = Utils.getDrivers();
        drivers.forEach(webDriver -> {
            MainPage mainPage = new MainPage(webDriver);
            webDriver.get(Utils.BASE_URL);
            webDriver.manage().window().setSize(new Dimension(1440, 875));
            Utils.waitUntilPageLoads(webDriver, 20);
            mainPage.doLogin();
            WebElement login = Utils.getElementBySelector(webDriver, By.xpath(".//*[@id=\"main\"]/div[1]/div[2]/a[2]/strong"));
            assertEquals(Utils.DISPLAY_NAME, login.getText());
            webDriver.quit();
        });
    }

    @Test
    @Order(2)
    void logoutTest() {
        List<WebDriver> drivers = Utils.getDrivers();
        drivers.parallelStream().forEach(webDriver -> {
            MainPage mainPage = new MainPage(webDriver);
            webDriver.get(Utils.BASE_URL);
            webDriver.manage().window().setSize(new Dimension(1440, 875));
            mainPage.doLogin();
            Utils.waitUntilPageLoads(webDriver, 20);
            mainPage.doLogout();
            Utils.waitUntilPageLoads(webDriver, 20);
            assertEquals("Войти", Utils.getElementBySelector(webDriver, By.xpath("//*[@id=\"main\"]/div[1]/div[1]/a/strong")).getText());
            webDriver.quit();
        });
    }

    @Test
    @Order(3)
    void wrongLoginTest() {
        List<WebDriver> drivers = Utils.getDrivers();
        drivers.parallelStream().forEach(webDriver -> {
            MainPage mainPage = new MainPage(webDriver);
            webDriver.get(Utils.BASE_URL);
            webDriver.manage().window().setSize(new Dimension(1440, 875));
            mainPage.doWrongLogin();
            WebElement wrongMessage = Utils.getElementBySelector(webDriver, By.xpath(".//*[@id=\"login_frm\"]/table/tbody/tr[3]/td/div"));
            assertTrue(wrongMessage.getText().equals("Неверный логин или пароль") || wrongMessage.getText().equals("Пожалуйста, поставьте флажок напротив \"Я человек\""));
            webDriver.quit();
        });
        drivers.forEach(WebDriver::quit);
    }
}

