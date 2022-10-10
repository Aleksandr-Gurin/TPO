package org.example.model;

import org.example.Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class AccountPage extends Page {
    public enum Fields{
        DisplayName(".//div[@id='main']/div[4]/div/form/table/tbody/tr[2]/td[2]/input"),
        ICQ(".//*[@id=\"main\"]/div[4]/div/form/table/tbody/tr[5]/td[2]/input"),
        MainPage(".//*[@id=\"main\"]/div[4]/div/form/table/tbody/tr[6]/td[2]/input");

        public final String xpath;
        Fields(String xpath){
            this.xpath = xpath;
        }
    }

    public AccountPage(WebDriver driver) {
        super(driver);
    }


//    public void changeDisplayName(String name) {
////        Utils.getElementBySelector(driver, By.xpath(".//div[@id='main']/div[4]/div/form/table/tbody/tr[2]/td[2]/input")).click();
//        WebElement displayNameField = Utils.getElementBySelector(driver, By.xpath(".//div[@id='main']/div[4]/div/form/table/tbody/tr[2]/td[2]/input"));
//        displayNameField.clear();
//        displayNameField.sendKeys(name);
//        WebElement passField = Utils.getElementBySelector(driver, By.xpath(".//div[@id='main']/div[4]/div/form/table/tbody/tr/td[2]/input"));
//        passField.clear();
//        passField.sendKeys(Utils.CORRECT_PASSWORD);
//        Utils.getElementBySelector(driver, By.xpath(".//div[@id='main']/div[4]/div/form/table/tbody/tr[7]/td[2]/div/input")).click();
//    }

    public void changeField(Fields f, String value){
        WebElement field = Utils.getElementBySelector(driver, By.xpath(f.xpath));
        field.clear();
        field.sendKeys(value);
        WebElement passField = Utils.getElementBySelector(driver, By.xpath(".//div[@id='main']/div[4]/div/form/table/tbody/tr/td[2]/input"));
        passField.clear();
        passField.sendKeys(Utils.CORRECT_PASSWORD);
        Utils.getElementBySelector(driver, By.xpath(".//div[@id='main']/div[4]/div/form/table/tbody/tr[7]/td[2]/div/input")).click();

    }
}

