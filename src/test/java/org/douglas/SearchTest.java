package org.douglas;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utils.ExcelUtils;
import utils.WebDriverManager;

import java.util.Map;

public  class SearchTest extends BaseTest{

    @DataProvider(name = "excelData")
    public Object[][] excelDataProvider() {
        return ExcelUtils.readExcelData("TC_001");
    }

    @BeforeTest
    public void openApplication(){
        landingPage.goTo().acceptCookie();
    }

    @AfterTest
    public void closeBrowser(){
        WebDriverManager.quitBrowser();
    }

    @Test(dataProvider = "excelData")
    public void searchAndFilter(Map<String, String> data){
        headerMenu.clickMenu(data.get("menu-item"));
        filterMenu.applyHighlights(data.get("filters"));
    }
}