package resources;

import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.time.Duration;
import java.util.List;
import java.util.Set;
import org.openqa.selenium.support.ui.Select;

public class PageActions{

    private WebDriver driver;

    public PageActions(WebDriver driver) {
    	 PageFactory.initElements(driver, this);  
    	 this.driver = driver;
    }

    public void waitForElementVisibility(WebElement element, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void waitForElementToBeClickable(WebElement element, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public void clickElement(WebElement element, int timeoutInSeconds) {
        waitForElementToBeClickable(element, timeoutInSeconds);
        element.click();
    }

    public void sendKeysToElement(WebElement element, String text, int timeoutInSeconds) {
        waitForElementVisibility(element, timeoutInSeconds);
        element.sendKeys(text);
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public void scrollToElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }
    
    public void waitForElementInvisibility(WebElement element, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        wait.until(ExpectedConditions.invisibilityOf(element));
    }

    public boolean isElementDisplayed(WebElement element) {
    	boolean webelement = false;
    	     try {
    	            webelement = element.isDisplayed();
    	        } catch (Exception e) {

    	      }
    	        return webelement;
    }

    public boolean isElementEnabled(WebElement element) {
        return element.isEnabled();
    }

    public boolean isElementSelected(WebElement element) {
        return element.isSelected();
    }

    public String getElementAttribute(WebElement element, String attribute) {
        return element.getDomAttribute(attribute);
    }

    public String getElementText(WebElement element) {
        return element.getText();
    }
    
    public CustomWebElement getCustomWebElement(WebElement element) { 
    	return new CustomWebElement(element); 
    	} 
    }

	