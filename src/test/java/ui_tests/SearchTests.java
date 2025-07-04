package ui_tests;

import manager.ApplicationManager;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.ResultsPage;

import java.time.LocalDate;

public class SearchTests extends ApplicationManager {
    @Test
    public void searchPositiveTest(){
        HomePage homePage = new HomePage(getDriver());
        homePage.typeSearchForm("Haifa", LocalDate.of(2025, 7, 25),LocalDate.of(2025,8,25));
        Assert.assertTrue(new ResultsPage(getDriver()).validateUrl("results"));
    }
    @Test
    public void searchPositiveTestCalendar(){
        HomePage homePage = new HomePage(getDriver());
        homePage.typeSearchFormCalendar("Haifa", LocalDate.of(2025, 7, 25),LocalDate.of(2025,8,25));
       Assert.assertTrue(new ResultsPage(getDriver()).validateUrl("results"));
    }
    @Test(expectedExceptions = {org.openqa.selenium.NoSuchElementException.class})
    public void searchNegativeTestCalendar(){
        HomePage homePage = new HomePage(getDriver());
        homePage.typeSearchFormCalendar("Haifa", LocalDate.of(2025, 5, 25),LocalDate.of(2025,8,25));

    }
}
