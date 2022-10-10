package org.example.model;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.example.Utils;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.time.Duration;

public class MainPage extends Page {

    public MainPage(WebDriver driver) {
        super(driver);
    }

    public void doLogin() {
        tryLogin(Utils.CORRECT_LOGIN, Utils.CORRECT_PASSWORD);
        Utils.waitUntilPageLoads(driver, 20);
    }

    public void doWrongLogin() {
        tryLogin(Utils.CORRECT_LOGIN, Utils.WRONG_PASSWORD);
    }

    public void doLogout() {
        Actions actions = new Actions(driver);
        actions.moveToElement(Utils.getElementBySelector(driver, By.xpath(".//div[@id='main']/div/div[2]/a[2]/strong"))).click().perform();
        actions.moveToElement(Utils.getElementBySelector(driver, By.xpath(".//div[@id='main']/div/div[2]/div/ul/li[3]/a"))).click().perform();
        Utils.waitUntilPageLoads(driver, 10);
    }

    private void tryLogin(CharSequence login, CharSequence password) {
        WebElement loginButton = Utils.getElementBySelector(driver, By.xpath(".//*[@id=\"main\"]/div[1]/div[1]/a/strong"));
        loginButton.click();
        WebElement loginInput = Utils.getElementBySelector(driver, By.xpath(".//*[@id=\"login_frm\"]/table/tbody/tr[4]/td/input"));
        WebElement loginPassword = Utils.getElementBySelector(driver, By.xpath(".//*[@id=\"login_frm\"]/table/tbody/tr[5]/td/input"));
        WebElement authButton = Utils.getElementBySelector(driver, By.xpath(".//*[@id=\"login_btn\"]"));
        loginInput.clear();
        loginPassword.clear();
        loginInput.sendKeys(login);
        loginPassword.sendKeys(password);
        authButton.click();
    }
}

