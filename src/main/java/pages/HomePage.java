package pages;

import dev.failsafe.Timeout;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import java.time.LocalDate;

public class HomePage extends BasePage {

    public HomePage(WebDriver driver) {
        setDriver(driver);
        driver.get("https://ilcarro.web.app/search");
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
    }

    @FindBy(xpath = "//a[text()=' Log in ']")
    WebElement btnLoginHeader;
    //WebElement getBtnLoginHeader = driver.findElement(By.xpath("//a[text()=' Log in ']"));

    @FindBy(id = "city")
    WebElement inputCity;
    @FindBy(id = "dates")
    WebElement inputDates;
    @FindBy(xpath = "//button[@type='submit']")
    WebElement btnYalla;

    //calendar//
    @FindBy(xpath = "//button[@aria-label='Choose month and year']")
    WebElement btnMonthYear;


    public void clickBtnLoginHeader() {
        btnLoginHeader.click();
    }

    public void typeSearchForm(String city, LocalDate startDate, LocalDate endDate) {
        inputCity.sendKeys(city);
        inputDates.sendKeys(dateToString(startDate) + " - " + dateToString(endDate));
        removeDisabledBtnSearch();
        btnYalla.click();
    }

    private String dateToString(LocalDate date) {
        return (date.getMonthValue()) + "/" + date.getDayOfMonth() + "/" + date.getYear();
    }

    public void typeSearchFormCalendar(String city, LocalDate startDate, LocalDate endDate) {
        inputCity.sendKeys(city);
        inputDates.click();
        typeYearMonthDay(startDate);
        typeYearMonthDay(endDate);
        removeDisabledBtnSearch();
        btnYalla.click();
    }

    private void typeYearMonthDay(LocalDate date) {
        btnMonthYear.click();
        String year = Integer.toString(date.getYear()); //2025
        driver.findElement(By.xpath("//div[contains(text(), '" + year + "')]")).click();
        String month = date.getMonth().toString();
        System.out.println(month.substring(0, 3));
        driver.findElement(By.xpath("//div[contains(text(), '" + month.substring(0, 3) + "')]")).click();
        String day = String.valueOf(date.getDayOfMonth());
        driver.findElement(By.xpath("//div[contains(text(), '" + day + "')]")).click();
    }
}
