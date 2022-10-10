package org.example.usecase;

import org.example.Utils;
import org.example.model.Header;
import org.example.model.MainPage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PaymentTests {
    @BeforeAll
    public static void prepareDrivers() {
        Utils.prepareDrivers();
    }

    @Test
    void checkGoToPaymentStatus() {
        List<WebDriver> drivers = Utils.getDrivers();
        drivers.forEach(webDriver -> {
            MainPage mainPage = new MainPage(webDriver);
            webDriver.get(Utils.BASE_URL);
            mainPage.doLogin();
            Header header = new Header(webDriver);
            header.goToPayment();

            JavascriptExecutor executor = (JavascriptExecutor) webDriver;
            WebElement ok = Utils.getElementBySelector(webDriver, By.xpath(".//*[@id=\"member_menu\"]/div[1]/div/a[2]"));
            executor.executeScript("arguments[0].click();", ok);


            WebElement firstResult = new WebDriverWait(webDriver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.elementToBeClickable(By.xpath(".//*[@id=\"member_menu\"]/div[1]/div/a[2]")));
            firstResult.click();

            WebElement text = Utils.getElementBySelector(webDriver, By.xpath(".//*[@id=\"box_small\"]/div/ins"));
            assertEquals("+\n" + "Преимущества Gold аккаунта", text.getText());
            webDriver.quit();
        });
    }
}
