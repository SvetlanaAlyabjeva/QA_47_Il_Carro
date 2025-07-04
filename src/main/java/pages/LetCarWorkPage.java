package pages;

import dto.Car;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.Select;
import utils.Fuel;
import utils.WDListener;

import java.io.File;

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
    @FindBy(id = "photos")
    WebElement inputFile;
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
        //pause(3);
        googleMapsBtnOk.click();
        inputManufacture.sendKeys(car.getManufacture());
        inputModel.sendKeys(car.getModel());
        inputYear.sendKeys(car.getYear());
        typeFuel(car.getFuel());
        //selectFuel.sendKeys(car.getFuel());
        inputSeats.sendKeys(car.getSeats()==null?"":car.getSeats().toString());
        inputCarClass.sendKeys(car.getCarClass());
        inputSerialNumber.sendKeys(car.getSerialNumber());
        inputPrice.sendKeys(car.getPricePerDay() + "");
        //addPhoto(car.getImage());
        btnSubmit.click();
    }

    private void addPhoto(String fileName) {
        inputFile.sendKeys(new File("src/test/resources/photos" + File.separator + fileName).getAbsolutePath());
    }

    private void typeFuel(String fuel){
        Select select = new Select(selectFuel);
        select.selectByValue(fuel);
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
    public boolean isEnabledSubmitBtn(){
        return elementIsEnabled(btnSubmit);
    }
}