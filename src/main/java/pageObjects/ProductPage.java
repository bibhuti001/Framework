package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductPage {
	public WebDriver driver;

	
	public ProductPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
   	 	this.driver = driver;

    }

    @FindBy (css = ".inventory_details_name.large_size")
    public WebElement item1;

    @FindBy(id = "add-to-cart")
    public WebElement addToCartButton;

    @FindBy(className = "shopping_cart_badge")
    public WebElement cartAfterAddingItem;

    @FindBy (id = "remove")
    public WebElement removeButton;



    public void clickOnAddToCartButton(){
        addToCartButton.click();
    }

    public void clickOnRemoveButton(){
        removeButton.click();
    }

}
