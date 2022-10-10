package org.example.model;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.example.Utils;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Header extends Page {

    public Header(WebDriver driver) {
        super(driver);
    }

    public void goToAccount() {
        Utils.getElementBySelector(driver, By.xpath(".//div[@id='main']/div/div[2]/a[2]/strong")).click();
        Utils.waitUntilPageLoads(driver, 10);
        Utils.getElementBySelector(driver, By.xpath(".//div[@id='main']/div/div[2]/div/ul/li/ul/li/a")).click();
        Utils.waitUntilPageLoads(driver, 10);

    }

    public void goToFiles() {
        Utils.getElementBySelector(driver, By.xpath(".//*[@id=\"main\"]/div[1]/ul/li[2]")).click();
        Utils.waitUntilPageLoads(driver, 10);
    }

    public void goToPayment() {
        Utils.getElementBySelector(driver, By.xpath(".//*[@id=\"main\"]/div[1]/ul/li[3]")).click();
        Utils.waitUntilPageLoads(driver, 10);
    }

    public void goToLoyalty() {
//        Utils.getElementBySelector(driver, By.xpath(".//*[@id=\"main\"]/div/ul/li[4]")).click();
        WebElement firstResult = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"main\"]/div/ul/li[4]")));
        firstResult.click();
        Utils.waitUntilPageLoads(driver, 10);
    }

    public void goToTickets() {
        Utils.getElementBySelector(driver, By.xpath(".//*[@id=\"main\"]/div[1]/ul/li[5]")).click();
        Utils.waitUntilPageLoads(driver, 10);
    }

    public void goToUpload() {
        Utils.getElementBySelector(driver, By.xpath(".//*[@id=\"main\"]/div[1]/ul/li[1]/a")).click();
        Utils.waitUntilPageLoads(driver, 10);
    }
}

