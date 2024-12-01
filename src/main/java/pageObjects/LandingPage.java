package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPage {
	public WebDriver driver;
	
	public LandingPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
   	 	this.driver = driver;

	}

	@FindBy(className = "app_logo")
    public WebElement logoProducts;

    @FindBy(id = "shopping_cart_container")
    public WebElement shoppingCart;

    @FindBy(className = "bm-burger-button")
    public WebElement reactMenu;

    @FindBy(id = "logout_sidebar_link")
    public WebElement LogoutButton;

    @FindBy(className = "title")
    public WebElement title;

    @FindBy(id = "about_sidebar_link")
    public WebElement aboutButton;

    @FindBy(className = "inventory_item_price")
    public WebElement itemPrice;

    @FindBy (className = "header_secondary_container")
    public WebElement titleCart;

    @FindBy(id = "continue-shopping")
    public WebElement continueShopping;

    @FindBy(id = "checkout")
    public WebElement checkOutButton;


    public void clickOnReactMenu() {
        reactMenu.click();
    }

    public void clickOnLogoutButton() {
        LogoutButton.click();
    }

    public void clickOnAboutButton() {
        aboutButton.click();
    }

    public void clickOnItem(String name) {
    	
    	WebElement ele = driver.findElement(By.xpath("//div[@class='inventory_item_name ' and text()='"+name+"']"));
    	ele.click();
    }

    public void clickOnCart(){
        shoppingCart.click();
    }

    public void clickOnContinueShoppingButton(){
        continueShopping.click();
    }
    public void clickOnCheckOutButton(){
        checkOutButton.click();
    }
}
