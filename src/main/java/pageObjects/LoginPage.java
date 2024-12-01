package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	public WebDriver driver;
	
	public LoginPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
   	 	this.driver = driver;

	}

	@FindBy(className = "login_logo")
    public WebElement logo;

    @FindBy(id = "user-name")
    public WebElement UsernameField;

    @FindBy(id = "password")
    public  WebElement PasswordField;

    @FindBy(id = "login-button")
    public  WebElement LogInButton;

    @FindBy (className = "error-button")
    public WebElement Notification;
    
    @FindBy (xpath = "//div[@class='error-message-container error']")
    public WebElement errorMessage;

    public String expectedNotification = "Epic sadface: Sorry, this user has been locked out.";

    public void inputUsername (String username){
        UsernameField.sendKeys(username);
    }

    public void inputPassword(String password){
        PasswordField.sendKeys(password);
    }

    public void clickOnLoginButton() {
        LogInButton.click();
    }
    
    public String errorMessage() {
    	return errorMessage.getText();
    }
    
    public WebElement getLogInLogo() {
    	return logo;
    }
    
    public WebElement getLogInButton() {
    	return LogInButton;
    }

}
