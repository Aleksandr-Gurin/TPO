package org.example.usecase;

import org.example.Utils;
import org.example.model.Header;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoyaltyTests {
    @BeforeAll
    public static void prepareDrivers() {
        Utils.prepareDrivers();
    }

    @Test
    void checkGoToUPoints() {
        List<WebDriver> drivers = Utils.getDrivers();
        drivers.forEach(webDriver -> {
            webDriver.get(Utils.BASE_URL);
            Header header = new Header(webDriver);
            header.goToLoyalty();
            JavascriptExecutor executor = (JavascriptExecutor) webDriver;
            WebElement ok = Utils.getElementBySelector(webDriver, By.xpath(".//*[@id=\"member_menu\"]/div[1]/div/a[1]"));
            executor.executeScript("arguments[0].click();", ok);

            WebElement firstResult = new WebDriverWait(webDriver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.elementToBeClickable(By.xpath(".//*[@id=\"member_menu\"]/div[1]/div/a[1]")));
            firstResult.click();
            Utils.waitUntilPageLoads(webDriver, 10);

            WebElement text = Utils.getElementBySelector(webDriver, By.xpath(".//*[@id=\"main\"]/div[4]/div[2]/div/div/p[1]"));
            assertEquals("U-Points - are Points you get for your files (uploaded by you to Depositfiles.com earlier) being downloaded.", text.getText());
            webDriver.quit();
        });
    }

    @Test
    void checkGoToDPoints() {
        List<WebDriver> drivers = Utils.getDrivers();
        drivers.forEach(webDriver -> {
            webDriver.get(Utils.BASE_URL);
            Header header = new Header(webDriver);
            header.goToLoyalty();
            JavascriptExecutor executor = (JavascriptExecutor) webDriver;
            WebElement ok = Utils.getElementBySelector(webDriver, By.xpath(".//*[@id=\"member_menu\"]/div[1]/div/a[2]"));
            executor.executeScript("arguments[0].click();", ok);

            WebElement firstResult = new WebDriverWait(webDriver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.elementToBeClickable(By.xpath(".//*[@id=\"member_menu\"]/div[1]/div/a[2]")));
            firstResult.click();
            Utils.waitUntilPageLoads(webDriver, 10);

            WebElement text = Utils.getElementBySelector(webDriver, By.xpath(".//*[@id=\"main\"]/div[4]/div[2]/div/div/p[1]"));
            assertEquals("D-Points - are Points you get for downloading files", text.getText());
            webDriver.quit();
        });
    }


    @Test
    void checkGoToPaymentStatus() {
        List<WebDriver> drivers = Utils.getDrivers();
        drivers.forEach(webDriver -> {
            Header header = new Header(webDriver);
            webDriver.get(Utils.BASE_URL);
            header.goToLoyalty();
            WebElement firstResult = new WebDriverWait(webDriver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.elementToBeClickable(By.xpath(".//*[@id=\"member_menu\"]/div[1]/div/a[1]")));
            firstResult.click();
            Utils.waitUntilPageLoads(webDriver, 10);

            WebElement text = Utils.getElementBySelector(webDriver, By.xpath(".//*[@id=\"main\"]/div[4]/div[2]/div/div/p[1]"));
            assertEquals("The Loyalty Program - is a complex of campaigns aimed to encourage users of Depositfiles.com service.", text.getText());
            webDriver.quit();
        });
    }
}
