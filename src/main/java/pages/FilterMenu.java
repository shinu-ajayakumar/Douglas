package pages;

import org.openqa.selenium.By;
import utils.ElementHelper;

public class FilterMenu {

    private final By dd_highlights = By.xpath("//div[text()='Highlights']");
    private final By btn_applyFilter = By.cssSelector(".facet__menu button");

    public void applyHighlights(String filters) {
        ElementHelper.clickElement(dd_highlights);
        for (String filter : filters.split(",")) {
            ElementHelper.clickElement(By.xpath("//a/div/div[text()='" + filter + "']"));
        }
        ElementHelper.clickElement(btn_applyFilter);
    }
}
