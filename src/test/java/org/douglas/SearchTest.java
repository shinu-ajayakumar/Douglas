package org.douglas;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utils.Assertions;
import utils.ExcelUtils;
import utils.PropertiesReader;
import utils.WebDriverManager;

import java.util.Map;

public class SearchTest extends BaseTest {

    @DataProvider(name = "excelData")
    public Object[][] excelDataProvider() {
        return ExcelUtils.readExcelData("TC_001");
    }

    @Test(dataProvider = "excelData")
    public void searchAndFilter(Map<String, String> data) {
        landingPage.goTo().acceptCookie();
        Assertions.stringContains(WebDriverManager.getDriver().getTitle(), PropertiesReader.getProperty("page.title"), "TITLE NOT EXPECTED");
        headerMenu.clickMenu(data.get("menu-item"));
        filterMenu.applyHighlights(data.get("filters"));
    }
}