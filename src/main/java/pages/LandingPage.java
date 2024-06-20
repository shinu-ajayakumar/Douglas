package pages;

import org.openqa.selenium.By;
import utils.ElementHelper;
import utils.WebDriverManager;

import static utils.ElementHelper.clickElement;
import static utils.WebDriverManager.goToURL;

public class LandingPage {

    private final By btn_acceptCookie = By.cssSelector("button[class*='accept-all']");

    public LandingPage goTo() {
        goToURL();
        return this;
    }

    public void acceptCookie() {
        clickElement(btn_acceptCookie);
    }
}
