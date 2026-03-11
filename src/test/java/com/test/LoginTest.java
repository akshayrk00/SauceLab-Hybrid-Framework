package com.test;

import browserConfig.BaseTest;
import com.objectmanager.ObjectManager;
import com.pages.LoginPage;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.Log;

import java.util.Map;

public class LoginTest extends BaseTest {


    LoginPage loginPage;

    @Test
    public void validateLoginFunctionalityWithValidCredentials() throws InterruptedException {


        System.out.println("Started Testing");

        Map<String, String> data = getTestData();

        String username = data.get("username");
        String password = data.get("password");

        loginPage = ObjectManager.getLoginPage();
        loginPage.login(username, password);
        Thread.sleep(2000);
        Assert.assertTrue(loginPage.isLogoDisplayed());


        System.out.println("Login executed successfully");
    }

    @Test
    public void validateLoginFunctionalityWithInvalidCredentials() {

        Map<String, String> data = getTestData();

        String username = data.get("username");
        String password = data.get("password");
        String errorMessage = data.get("errormessage");

        System.out.println(errorMessage);

        loginPage = ObjectManager.getLoginPage();
        loginPage.login(username, password);
        Assert.assertEquals(errorMessage, loginPage.getErrorMessage());

        System.out.println("Second login test executed");
    }

    @Test
    public void validateLoginFunctionalitywithUsernameAndPasswordEmpty(){

        Map<String, String> data = getTestData();

        String username = data.get("username");
        String password = data.get("password");
        String errorMessage = data.get("errormessage");


        loginPage = ObjectManager.getLoginPage();
        loginPage.login(username, password);
        Assert.assertEquals(errorMessage, loginPage.getErrorMessage());
    }

    @Test
    public void validateLoginFunctionalityWithLockedUser(){

        Map<String, String> data = getTestData();

        String username = data.get("username");
        String password = data.get("password");
        String errorMessage = data.get("errormessage");


        loginPage = ObjectManager.getLoginPage();
        loginPage.login(username, password);
        Assert.assertEquals(errorMessage, loginPage.getErrorMessage());
    }
}
