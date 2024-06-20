package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ElementHelper {

    private static final int explicitTimeout = Integer.parseInt(PropertiesReader.getProperty("explicit.wait"));
    public static void clickElement(By byElement) {
        try {
            new WebDriverWait(WebDriverManager.getDriver(), Duration.ofSeconds(explicitTimeout)).until(ExpectedConditions.elementToBeClickable(byElement));
            highLightElement(byElement);
            WebDriverManager.getDriver().findElement(byElement).click();
        } catch (Exception e) {
            e.printStackTrace();
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
            Thread.sleep(250);
            js.executeScript("arguments[0].setAttribute('style', arguments[1]);", webElement, "border: 2px solid red; border-style: dashed;");
            Thread.sleep(250);
            js.executeScript("arguments[0].setAttribute('style', arguments[1]);", webElement, originalStyle);
            Thread.sleep(250);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
