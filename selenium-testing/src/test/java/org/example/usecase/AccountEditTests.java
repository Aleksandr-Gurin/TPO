package org.example.usecase;

import org.example.Utils;
import org.example.model.AccountPage;
import org.example.model.Header;
import org.example.model.MainPage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AccountEditTests {

    @BeforeAll
    public static void prepareDrivers() {
        Utils.prepareDrivers();

    }

    @Test
    void editDisplayName() {
        List<WebDriver> drivers = Utils.getDrivers();
        drivers.parallelStream().forEach(webDriver -> {
            Header header = new Header(webDriver);
            MainPage mainPage = new MainPage(webDriver);
            AccountPage accountPage = new AccountPage(webDriver);
            webDriver.get(Utils.BASE_URL);
            webDriver.manage().window().setSize(new Dimension(1440, 875));
            mainPage.doLogin();
            header.goToAccount();
            accountPage.changeField(AccountPage.Fields.DisplayName, "god2022");
            WebElement login = Utils.getElementBySelector(webDriver, By.xpath(".//*[@id=\"main\"]/div[1]/div[2]/a[2]/strong"));
            assertEquals("god2022", login.getText());
            accountPage.changeField(AccountPage.Fields.DisplayName, "god228");
            login = Utils.getElementBySelector(webDriver, By.xpath(".//*[@id=\"main\"]/div[1]/div[2]/a[2]/strong"));
            assertEquals("god228", login.getText());
        });
        drivers.forEach(WebDriver::quit);
    }

    @Test
    void editICQ() {
        checkField(AccountPage.Fields.ICQ, "icq1_test", "icq2_test");
    }

    @Test
    void editMainPage() {
        checkField(AccountPage.Fields.MainPage, "page1_test", "page2_test");
    }

    private void checkField(AccountPage.Fields f, String value1, String value2) {
        List<WebDriver> drivers = Utils.getDrivers();
        drivers.forEach(webDriver -> {
            MainPage mainPage = new MainPage(webDriver);
            AccountPage accountPage = new AccountPage(webDriver);
            webDriver.get(Utils.BASE_URL);
            webDriver.manage().window().setSize(new Dimension(1440, 875));
            mainPage.doLogin();
            Header header = new Header(webDriver);
            header.goToAccount();
            //
            accountPage.changeField(f, value1);
            header.goToAccount();
            WebElement field = Utils.getElementBySelector(webDriver, By.xpath(f.xpath));
            assertEquals(value1, field.getAttribute("value"));
            //
            accountPage.changeField(f, value2);
            header.goToAccount();
            field = Utils.getElementBySelector(webDriver, By.xpath(f.xpath));
            assertEquals(value2, field.getAttribute("value"));
        });
        drivers.forEach(WebDriver::quit);
    }
}

