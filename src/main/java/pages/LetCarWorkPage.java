package pages;

import dto.Car;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class LetCarWorkPage extends BasePage {
    public LetCarWorkPage(WebDriver driver) {
        setDriver(driver);
        PageFactory.initElements(
                new AjaxElementLocatorFactory(driver, 10), this);
    }

    @FindBy(id = "pickUpPlace")
    WebElement inputCity;
    @FindBy(id = "make")
    WebElement inputManufacture;
    @FindBy(id = "model")
    WebElement inputModel;
    @FindBy(id = "year")
    WebElement inputYear;
    @FindBy(id = "fuel")
    WebElement selectFuel;
    @FindBy(id = "seats")
    WebElement inputSeats;
    @FindBy(id = "class")
    WebElement inputCarClass;
    @FindBy(id = "serialNumber")
    WebElement inputSerialNumber;
    @FindBy(id = "price")
    WebElement inputPrice;
    @FindBy(xpath = "//button[@class='dismissButton']")
    WebElement googleMapsBtnOk;
    @FindBy(xpath = "//button[@type='submit']")
    WebElement btnSubmit;
    @FindBy(xpath = "//div[text()=' Model is required ']")
    WebElement errorMessageModel;
    @FindBy(xpath = "//div[text()=' Price is required ']")
    WebElement errorMessagePrice;
    @FindBy(xpath = "//div[text()=' Number of seats is required ']")
    WebElement errorMessageSeats;


    public void typeAddNewCarForm(Car car) {
        inputCity.sendKeys(car.getCity());
        googleMapsBtnOk.click();
        inputManufacture.sendKeys(car.getManufacture());
        inputModel.sendKeys(car.getModel());
        inputYear.sendKeys(car.getYear());
        selectFuel.sendKeys(car.getFuel());
        inputSeats.sendKeys(car.getSeats()==null?"":car.getSeats().toString());
        inputCarClass.sendKeys(car.getCarClass());
        inputSerialNumber.sendKeys(car.getSerialNumber());
        inputPrice.sendKeys(car.getPricePerDay() + "");
        btnSubmit.click();
    }

    public boolean validateErrorModel() {
        return isElementPresent(errorMessageModel);
    }

    public boolean validateErrorPrice() {
        return isElementPresent(errorMessagePrice);
    }

    public boolean validateErrorCity() {
        return isElementPresent(errorMessageSeats);
    }
}