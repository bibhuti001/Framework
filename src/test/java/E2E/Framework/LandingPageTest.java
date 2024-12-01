package E2E.Framework;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageObjects.LandingPage;
import pageObjects.LoginPage;
import pageObjects.ProductPage;
import resources.Base;
import resources.PageActions;

public class LandingPageTest extends Base {
	
    @BeforeClass
    void setup() throws IOException {
    	initializeBrowser();
        loginPage = new LoginPage(driver);
        landingPage = new LandingPage(driver);
        pageActions = new PageActions(driver);
        productPage = new ProductPage(driver);
        driver.get(configReader.getUrl());
    }

    @BeforeMethod
    public void resetToLoginPage() {
        driver.get(configReader.getUrl());
        standardUserlandingPageLogin();
    }

    public void standardUserlandingPageLogin() {
        String validUsername = configReader.getValidUser();
        String validPassword = configReader.getPassword();
        loginPage.inputUsername(validUsername);
        loginPage.inputPassword(validPassword);
        loginPage.clickOnLoginButton();
    }

    @DataProvider(name = "itemNames")
    public Object[][] itemNames() {
        return new Object[][] {
            {"Sauce Labs Backpack"},
            {"Sauce Labs Bike Light"},
            {"Sauce Labs Bolt T-Shirt"}
        };
    }

    @Test(priority = 1)
    public void verifyThatStandardUserCanGoTolandingPage() {
        Assert.assertTrue(landingPage.logoProducts.isDisplayed(), "Logo not displayed on landing page.");
        Assert.assertTrue(landingPage.reactMenu.isDisplayed(), "React menu not displayed.");
        Assert.assertTrue(landingPage.shoppingCart.isDisplayed(), "Shopping cart not displayed.");
        Assert.assertTrue(landingPage.title.isDisplayed(), "Title not displayed.");
    }

    @Test(priority = 2, dataProvider = "itemNames")
    public void verifyUserCanClickOnItem(String itemName) {
        landingPage.clickOnItem(itemName);
        pageActions.waitForElementVisibility(productPage.addToCartButton, 10);
        Assert.assertTrue(productPage.addToCartButton.isDisplayed(), "Add to Cart button not displayed.");
    }
    static int count=0;
    @Test(priority = 3, dataProvider = "itemNames")
    public void verifyThatStandardUserCanClickOnAddToCartButton(String itemName) {
        landingPage.clickOnItem(itemName);
        pageActions.waitForElementVisibility(productPage.addToCartButton, 10);
        productPage.clickOnAddToCartButton();
        String cart = pageActions.getElementText(productPage.cartAfterAddingItem);
        Assert.assertEquals(cart, String.valueOf(++count), "Cart count is not 1 after adding an item.");
    }

    @Test(priority = 4, dataProvider = "itemNames")
    public void verifyThatStandardUserCanRemoveItemFromCart(String itemName) {
        landingPage.clickOnItem(itemName);
        pageActions.waitForElementVisibility(productPage.removeButton, 10);
        productPage.clickOnRemoveButton();
        Assert.assertTrue(productPage.addToCartButton.isDisplayed(), "Add to Cart button not displayed after removing item.");
    }

    @Test(priority = 5)
    public void verifyThatUserCanGoToCart() {
        landingPage.clickOnCart();
        pageActions.waitForElementVisibility(landingPage.titleCart, 10);
        String expectedTitle = "Your Cart";
        Assert.assertEquals(pageActions.getElementText(landingPage.titleCart), expectedTitle, "Cart title mismatch.");
        Assert.assertTrue(landingPage.continueShopping.isDisplayed(), "Continue Shopping button not displayed.");
        Assert.assertTrue(landingPage.checkOutButton.isDisplayed(), "Checkout button not displayed.");
    }

    @Test(priority = 6)
    public void verifyThatUserCanClickOnContinueShoppingButton() {
        landingPage.clickOnCart();
        pageActions.waitForElementVisibility(landingPage.titleCart, 10);
        landingPage.clickOnContinueShoppingButton();
        Assert.assertTrue(landingPage.logoProducts.isDisplayed(), "Logo not displayed on returning to products page.");
        Assert.assertTrue(landingPage.reactMenu.isDisplayed(), "React menu not displayed.");
        Assert.assertTrue(landingPage.shoppingCart.isDisplayed(), "Shopping cart not displayed.");
    }

    @AfterMethod
    void clearCookies() {
        driver.manage().deleteAllCookies();
    }

    @AfterClass
    void teardown() {
        closeDriver();
    }
}
