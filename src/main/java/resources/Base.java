//package resources;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.nio.file.Paths;
//import java.util.Properties;
//import java.time.Duration;
//import org.apache.commons.io.FileUtils;
//import org.openqa.selenium.OutputType;
//import org.openqa.selenium.TakesScreenshot;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.firefox.FirefoxDriver;
//
//public class Base {
//    
//    public WebDriver driver;  
//    public Properties prop;
//
//    public WebDriver initializeBrowser() throws IOException {
//        if (driver == null) {  
//            prop = new Properties();
//            String projectDir = System.getProperty("user.dir");
//            String filePath = Paths.get(projectDir, "src", "main", "java", "resources", "data.properties").toString();
//            FileInputStream fis = new FileInputStream(filePath);
//            prop.load(fis);
//            String browserName = prop.getProperty("browser");
//            System.out.println("Selected Browser: " + browserName);
//
//            if (browserName.equalsIgnoreCase("Chrome")) {
//                driver = new ChromeDriver();
//            }
//            else if (browserName.equalsIgnoreCase("firefox")) {
//                driver = new FirefoxDriver();
//            } else {
//                throw new IllegalArgumentException("Browser not supported: " + browserName);
//            }
//            
//            driver.manage().window().maximize();
//
//            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
//            driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
//        }
//        
//        return driver;
//    }
//
//    public void closeDriver() {
//        if (driver != null) {
//            driver.quit();
//            driver = null; 
//        }
//    }
//
//    public String getScreenshot(String methodName, WebDriver driver) throws IOException {
//        System.out.println("Taking screenshot for method: " + methodName);
//        File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
//        String screenshotPath = System.getProperty("user.dir") + "\\reports\\" + methodName + ".png";
//        File destination = new File(screenshotPath);
//        if (!destination.getParentFile().exists()) {
//            destination.getParentFile().mkdirs();
//        }
//        FileUtils.copyFile(source, destination);
//        return screenshotPath;
//    }
//}


package resources;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import pageObjects.LandingPage;
import pageObjects.LoginPage;
import pageObjects.ProductPage;

public class Base {

    public WebDriver driver;
    public ConfigFileReader configReader;
    public LoginPage loginPage;
    public LandingPage landingPage;
    public PageActions pageActions;
    public ProductPage productPage;

    public Base() {
        configReader = ConfigFileReader.getInstance(); 
    }
     
    
    public WebDriver initializeBrowser() throws IOException {
        if (driver == null) {
            String browserName = configReader.getBrowser();
            System.out.println("Selected Browser: " + browserName);

            if (browserName.equalsIgnoreCase("Chrome")) {
                driver = new ChromeDriver();
            } else if (browserName.equalsIgnoreCase("firefox")) {
                driver = new FirefoxDriver();
            } else {
                throw new IllegalArgumentException("Browser not supported: " + browserName);
            }

            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
            
        }

        return driver;
    }

    public void closeDriver() {
        if (driver != null) {
        	driver.manage().deleteAllCookies();
            driver.quit();
            driver = null;
        }
    }

    public String getScreenshot(String methodName, WebDriver driver) throws IOException {
        System.out.println("Taking screenshot for method: " + methodName);
        File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String screenshotPath = System.getProperty("user.dir") + "\\reports\\" + methodName + ".png";
        File destination = new File(screenshotPath);
        if (!destination.getParentFile().exists()) {
            destination.getParentFile().mkdirs();
        }
        FileUtils.copyFile(source, destination);
        return screenshotPath;
    }
}
