package org.example.usecase;

import org.example.Utils;
import org.example.model.Header;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HeaderTests {
    @BeforeAll
    public static void prepareDrivers() {
        Utils.prepareDrivers();
    }

    @Test
    void checkGoToPayment() {
        List<WebDriver> drivers = Utils.getDrivers();
        drivers.forEach(webDriver -> {
            Header header = new Header(webDriver);
            webDriver.get(Utils.BASE_URL);

            header.goToPayment();
            WebElement text = Utils.getElementBySelector(webDriver, By.xpath(".//*[@id=\"main\"]/div[4]/div/div/div[1]/ins"));
            assertEquals("Стать GOLD пользователем сейчас!", text.getText());
            webDriver.quit();
        });
    }

    @Test
    void checkGoToLoality() {
        List<WebDriver> drivers = Utils.getDrivers();
        drivers.forEach(webDriver -> {
            Header header = new Header(webDriver);
            webDriver.get(Utils.BASE_URL);
            header.goToLoyalty();
            WebElement text = Utils.getElementBySelector(webDriver, By.xpath(".//*[@id=\"main\"]/div[4]/div[2]/div/div/h1/strong"));
            assertEquals("Loyalty Program!", text.getText());
            webDriver.quit();
        });
    }

    @Test
    void checkGoToTickets() {
        List<WebDriver> drivers = Utils.getDrivers();
        drivers.forEach(webDriver -> {
            Header header = new Header(webDriver);
            webDriver.get(Utils.BASE_URL);
            header.goToTickets();
            WebElement text = Utils.getElementBySelector(webDriver, By.xpath(".//*[@id=\"main\"]/div[4]/div[2]/div/div[1]/div[1]/div[1]"));
            assertEquals("Служба поддержки", text.getText());
            webDriver.quit();
        });
    }


    @Test
    void checkGoToUpload() {
        List<WebDriver> drivers = Utils.getDrivers();
        drivers.forEach(webDriver -> {
            Header header = new Header(webDriver);
            webDriver.get(Utils.BASE_URL);
            header.goToUpload();
            WebElement text = Utils.getElementBySelector(webDriver, By.xpath(".//*[@id=\"member_menu\"]/div[1]/div/a[1]"));
            assertEquals("Загрузить", text.getText());
            webDriver.quit();
        });
    }
}
