package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;

public class WebDriverManager {

    public static void goToURL(){
        getDriver().get(PropertiesReader.getProperty("base.url"));
    }
    private static WebDriver driver = null;

    public static WebDriver getDriver(){
        try {
            if(driver == null) {
                driver = initializeDriver();
            } return driver;
        } catch (Exception e){
            e.printStackTrace();
        }
        return driver;
    }

    private static WebDriver initializeDriver() {
        WebDriver driver = null;
        try {
            String browserName = PropertiesReader.getProperty("browser");
            switch (browserName) {
                case "chrome":
                    driver = new ChromeDriver();
                    break;
                case "firefox":
                    driver = new FirefoxDriver();
                    break;
                default:
                    driver = new ChromeDriver();
            }
            driver.manage().window().maximize();
        } catch (Exception e){
            e.printStackTrace();
        }
        return driver;
    }

    public static void quitBrowser() {
        try {
            Thread.sleep(2000);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        if(driver != null) {
            try {
                driver.close();
                driver.quit();
                driver = null;
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
