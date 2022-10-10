package org.example.usecase;

import org.example.Utils;
import org.example.model.Header;
import org.example.model.MainPage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FileTests {
    @BeforeAll
    public static void prepareDrivers() {
        Utils.prepareDrivers();
    }

    @Test
    void addAndRemoveFolder() {
        List<WebDriver> drivers = Utils.getDrivers();
        drivers.forEach(webDriver -> {
            Header header = new Header(webDriver);
            MainPage mainPage = new MainPage(webDriver);
            webDriver.get(Utils.BASE_URL);
            webDriver.manage().window().setSize(new Dimension(1440, 875));
            mainPage.doLogin();
            header.goToFiles();
//            Utils.getElementBySelector(webDriver, By.xpath(".//*[@id=\"df_share\"]/div[1]/div[1]/a[1]/span")).click();

            WebElement create_f = Utils.getElementBySelector(webDriver, By.xpath(".//*[@id=\"df_share\"]/div[1]/div[1]/a[1]/span"));
            JavascriptExecutor executor = (JavascriptExecutor) webDriver;
            executor.executeScript("arguments[0].click();", create_f);

            WebElement nameField = Utils.getElementBySelector(webDriver, By.xpath(".//*[@id=\"depositbox\"]/div[1]/div[2]/div/input"));
            nameField.clear();
            nameField.sendKeys("test_folder");

            WebElement ok = Utils.getElementBySelector(webDriver, By.xpath(".//*[@id=\"depositbox\"]/div[1]/div[2]/div/div"));
            executor.executeScript("arguments[0].click();", ok);

            WebElement folder = Utils.getElementBySelector(webDriver, By.xpath(".//*[@id=\"df_share\"]/div[1]/ul/li[2]/ul/li[1]/a"));
            assertEquals("test_folder 0", folder.getText());

            Actions actions = new Actions(webDriver);
            actions.contextClick(folder).perform();
            actions.contextClick(folder).perform();
            Utils.waitUntilPageLoads(webDriver, 10);
            Utils.getElementBySelector(webDriver, By.xpath(".//ul[@id='folderMenu']/li[3]/a")).click();
            Utils.waitUntilPageLoads(webDriver, 10);
            Utils.getElementBySelector(webDriver, By.xpath(".//div[@id='depositbox']/div/div[2]/div/label[2]/input")).click();
            Utils.getElementBySelector(webDriver, By.xpath(".//div[@id='depositbox']/div/div[2]/div/div")).click();

            webDriver.quit();
        });
    }
}
