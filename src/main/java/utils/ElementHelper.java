package utils;

import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import report.ReportTestManager;

import java.time.Duration;

public class ElementHelper {

    private static final int explicitTimeout = Integer.parseInt(PropertiesReader.getProperty("explicit.wait"));
    public static void clickElement(By byElement) {
        try {
            new WebDriverWait(WebDriverManager.getDriver(), Duration.ofSeconds(explicitTimeout)).until(ExpectedConditions.elementToBeClickable(byElement));
            Thread.sleep(500);
            highLightElement(byElement);
            String text = WebDriverManager.getDriver().findElement(byElement).getText();
            WebDriverManager.getDriver().findElement(byElement).click();
            Thread.sleep(500);
            ReportTestManager.getTest().log(Status.INFO, "Clicked element : " + text);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("FAILED TO CLICK : " + byElement);
        }
    }

    private static void highLightElement(By elementBy) {
        try {
            WebElement webElement = WebDriverManager.getDriver().findElement(elementBy);
            String originalStyle = webElement.getAttribute("style");
            JavascriptExecutor js = (JavascriptExecutor) WebDriverManager.getDriver();
            js.executeScript("arguments[0].scrollIntoView({block: \"center\",inline: \"center\",behavior: \"smooth\"});", webElement);
            Thread.sleep(500);
            js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');", webElement);
            Thread.sleep(100);
            js.executeScript("arguments[0].setAttribute('style', arguments[1]);", webElement, "border: 2px solid red; border-style: dashed;");
            Thread.sleep(100);
            js.executeScript("arguments[0].setAttribute('style', arguments[1]);", webElement, originalStyle);
            Thread.sleep(100);
        } catch (Exception e){
            //e.printStackTrace();
        }
    }
}
