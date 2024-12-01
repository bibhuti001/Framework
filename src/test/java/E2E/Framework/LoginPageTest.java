package E2E.Framework;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.*;

import pageObjects.LoginPage;
import resources.Base;
import resources.CustomWebElement;
import resources.PageActions;

public class LoginPageTest extends Base {

    @BeforeClass
    void setup() throws IOException {
    	initializeBrowser();
        loginPage = new LoginPage(driver);
        pageActions = new PageActions(driver);
        driver.get(configReader.getUrl());
    }

    @BeforeMethod
    public void resetToLoginPage() {
        driver.get(configReader.getUrl());
    }

    @DataProvider(name = "validLoginData")
    public Object[][] validLoginData() {
        return new Object[][]{
            {configReader.getValidUser(), configReader.getPassword()}
        };
    }

    @DataProvider(name = "invalidUsernameData")
    public Object[][] invalidUsernameData() {
        return new Object[][]{
            {configReader.getInvalidUser(), configReader.getPassword()}
        };
    }

    @DataProvider(name = "invalidPasswordData")
    public Object[][] invalidPasswordData() {
        return new Object[][]{
            {configReader.getValidUser(), configReader.getInvalidPassword()}
        };
    }

    @Test(priority = 1, dataProvider = "validLoginData")
    public void verifyThatStandardUserCanLogInWithValidInputs(String username, String password) {
        loginPage.inputUsername(username);
        loginPage.inputPassword(password);
        loginPage.clickOnLoginButton();

        Assert.assertEquals(pageActions.getPageTitle(), "Swag Labs", "Login failed for valid credentials.");
    }

    @Test(priority = 2, dataProvider = "invalidUsernameData")
    public void verifyThatStandardUserCannotLogInWithInvalidUsername(String username, String password) {
        loginPage.inputUsername(username);
        loginPage.inputPassword(password);
        loginPage.clickOnLoginButton();

        Assert.assertTrue(loginPage.UsernameField.isDisplayed(), "Username field should be visible after failed login.");
        Assert.assertTrue(loginPage.PasswordField.isDisplayed(), "Password field should be visible after failed login.");

        String expectedError = "Epic sadface: Username and password do not match any user in this service";
        Assert.assertEquals(loginPage.errorMessage(), expectedError, "Error message mismatch.");
    }

    @Test(priority = 3, dataProvider = "invalidPasswordData")
    public void verifyThatStandardUserCannotLogInWithInvalidPassword(String username, String password) {
        loginPage.inputUsername(username);
        loginPage.inputPassword(password);
        loginPage.clickOnLoginButton();

        Assert.assertTrue(loginPage.UsernameField.isDisplayed(), "Username field should be visible after failed login.");
        Assert.assertTrue(loginPage.PasswordField.isDisplayed(), "Password field should be visible after failed login.");

        String expectedError = "Epic sadface: Username and password do not match any user in this service";
        Assert.assertEquals(loginPage.errorMessage(), expectedError, "Error message mismatch.");
    }
    
    @Test(priority = 4, dataProvider = "validLoginData")
    public void verifyScreenshotCaptureFunctionality(String username, String password) {
    	 loginPage.inputUsername(username);
         loginPage.inputPassword(password);
         loginPage.clickOnLoginButton();
         
         Assert.fail("This test is just check the screenshot capture functionality");
    }
    
    @Test(priority = 5)
    public void verifyCustomMethods() {
    	CustomWebElement customLoginButton = pageActions.getCustomWebElement(loginPage.LogInButton); 
    	CustomWebElement customLoginLogo = pageActions.getCustomWebElement(loginPage.getLogInLogo()); 
    	String buttonText = customLoginButton.getAttribute("value"); 
    	String logoText = customLoginLogo.getText().replace("Custom Text: ", "");
    	
    	Assert.assertEquals(buttonText, "Login", "Text Mismatch");
    	Assert.assertEquals(logoText, "Swag Labs", "Text Mismatch");
    }

    @AfterClass
    void teardown() {
        closeDriver();
    }
}
