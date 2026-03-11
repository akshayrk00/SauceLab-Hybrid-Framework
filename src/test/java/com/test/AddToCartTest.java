package com.test;

import browserConfig.BaseTest;
import com.objectmanager.ObjectManager;
import com.pages.AddToCartPage;
import com.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

public class AddToCartTest extends BaseTest {

    LoginPage loginPage;
    AddToCartPage addToCartPage;

    @Test
    public void validateAddedItemsToCart() throws InterruptedException {
        System.out.println("Started Testing");

        Map<String, String> data = getTestData();

        String username = data.get("username");
        String password = data.get("password");

         loginPage = ObjectManager.getLoginPage();
        addToCartPage = ObjectManager.getAddToCartPage();
        loginPage.login(username, password);
        Thread.sleep(2000);
        Assert.assertTrue(loginPage.isLogoDisplayed());

        addToCartPage.addItemsToCart();
        List<String> elementsfromHomePage = addToCartPage.getTitleOfAddedItems();
        addToCartPage.TapOnCartIcon();
        List<String> elementsFromCart = addToCartPage.getTitleOfElementsInCart();
        Assert.assertEquals(elementsfromHomePage, elementsFromCart);
        System.out.println("ElementsFrom HomePAge: "+elementsfromHomePage);
        System.out.println("elements From Cart: "+elementsFromCart);

    }

}
