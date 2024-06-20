package org.douglas;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import pages.FilterMenu;
import pages.HeaderMenu;
import pages.LandingPage;
import utils.WebDriverManager;

public class BaseTest {
    LandingPage landingPage = new LandingPage();
    HeaderMenu headerMenu = new HeaderMenu();
    FilterMenu filterMenu = new FilterMenu();
}
