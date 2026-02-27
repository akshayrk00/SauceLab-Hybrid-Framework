package com.pages;

import base.DriverManager;
import com.test.LoginTest;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.Log;
import utils.YamlLocatorReader;

import java.util.List;

public class LoginPage {

    private WebDriver driver;
    private static final Logger log = Log.getLogger(LoginTest.class);


    public LoginPage() {
        this.driver = DriverManager.getDriver();
    }

    public void enterUsername(String username) {
        driver.findElement(
                YamlLocatorReader.getLocator("username")
        ).sendKeys(username);
        log.info("Username is entered: "+username);
    }

    public void enterPassword(String password) {
        driver.findElement(
                YamlLocatorReader.getLocator("password")
        ).sendKeys(password);
        log.info("password is entered: "+password);
    }

    public void clickLogin() {
        driver.findElement(
                YamlLocatorReader.getLocator("loginButton")
        ).click();
        log.info("login button is clicked");
    }

    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLogin();
    }

    public boolean isLogoDisplayed(){
        return driver.findElement(YamlLocatorReader.getLocator("dashboard")).isDisplayed();
    }

    public String getErrorMessage(){
        System.out.println(driver.findElement(YamlLocatorReader.getLocator("invalidCredentialsErrorMessge")).getText()+" "+ "is displayed");
        return driver.findElement(YamlLocatorReader.getLocator("invalidCredentialsErrorMessge")).getText();

    }


}
