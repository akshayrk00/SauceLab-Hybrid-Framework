package com.pages;

import base.DriverManager;
import com.test.LoginTest;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.Log;
import utils.WaitUtils;
import utils.YamlLocatorReader;

import java.util.ArrayList;
import java.util.List;

public class AddToCartPage {

    private WebDriver driver;
    private static final Logger log = Log.getLogger(LoginTest.class);

    private WaitUtils waitUtils;


    public AddToCartPage() {
        this.driver = DriverManager.getDriver();

        waitUtils = new WaitUtils(driver);
    }

    public void addItemsToCart(){

        List<WebElement> elements = driver.findElements(YamlLocatorReader.getLocator("AddToCartButton"));
        for (WebElement ele : elements){
            ele.click();
        }
    }

    public List<String> getTitleOfAddedItems(){
        List<String> elementsTitle = new ArrayList<>();
        List<WebElement> elements = driver.findElements(YamlLocatorReader.getLocator("TitleOfItermsAfterAddingToCart"));
        for (int i = 0; i< elements.size(); i++){
            waitUtils.waitForAllElementsVisible(elements, 10);
            elementsTitle.add(elements.get(i).getText());
        }
        return elementsTitle;
    }

    public void TapOnCartIcon(){
        waitUtils.waitAndClick(driver.findElement(YamlLocatorReader.getLocator("Cart_Icon")), 10);
    }

    public List<String> getTitleOfElementsInCart(){
        List<String> elementsTitle = new ArrayList<>();
        List<WebElement> elements = driver.findElements(YamlLocatorReader.getLocator("Title_of_items_in_Cart"));
        for (int i = 0; i< elements.size(); i++){
            waitUtils.waitForAllElementsVisible(elements, 10);
            elementsTitle.add(elements.get(i).getText());
        }
        return elementsTitle;

    }

}
