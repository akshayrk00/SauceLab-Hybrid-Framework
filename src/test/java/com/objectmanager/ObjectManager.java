package com.objectmanager;


import com.pages.AddToCartPage;
import com.pages.LoginPage;

public class ObjectManager {

    private static LoginPage loginPage;
    private static AddToCartPage addToCartPage;

    // Getter for LoginPage
    public static LoginPage getLoginPage() {
        if (loginPage == null) {
            loginPage = new LoginPage();
        }
        return loginPage;
    }

    // Getter for AddToCartPage
    public static AddToCartPage getAddToCartPage() {
        if (addToCartPage == null) {
            addToCartPage = new AddToCartPage();
        }
        return addToCartPage;
    }
}