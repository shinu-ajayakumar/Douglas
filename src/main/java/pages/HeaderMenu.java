package pages;

import org.openqa.selenium.By;
import utils.ElementHelper;
import utils.WebDriverManager;

public class HeaderMenu {
    public void clickMenu(String menuItem) {
        ElementHelper.clickElement(By.xpath("//span[@role='navigation']//a[text()='"+menuItem+"']"));
    }
}
