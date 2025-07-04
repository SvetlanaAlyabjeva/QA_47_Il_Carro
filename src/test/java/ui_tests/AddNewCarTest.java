package ui_tests;

import data_provider.CarDP;
import dto.Car;
import manager.ApplicationManager;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LetCarWorkPage;
import pages.LoginPage;
import utils.Fuel;
import utils.HeaderMenuItem;
import utils.TestNGListener;

import static pages.BasePage.*;
import static utils.RandomUtils.*;

@Listeners(TestNGListener.class)
public class AddNewCarTest extends ApplicationManager {
    LoginPage loginPage;
    LetCarWorkPage letCarWorkPage;

    @BeforeMethod
    public void login() {
        new HomePage(getDriver());
        loginPage = clickButtonsOnHeader(HeaderMenuItem.LOGIN);
        loginPage.typeLoginForm("email1@mail.ru", "ema31il@Mail.ru");
        letCarWorkPage = clickButtonsOnHeader(HeaderMenuItem.LET_CAR_WORK);
    }

    @Test
    public void addNewCarPositiveTest() {
        Car car = Car.builder()
                .city("Haifa")
                .manufacture("Opel")
                .model("Astra")
                .year("2020")
                .fuel(Fuel.HYBRID.getValue())
                .seats(4)
                .carClass("C")
                .serialNumber("Opel" + generateString(7))
                .pricePerDay(100.77)
                .about("about")
                .image("photoKatya.jpeg")
                .build();
        letCarWorkPage.typeAddNewCarForm(car);
    }

    @Test(dataProvider = "addNewCarDP", dataProviderClass = CarDP.class)
    public void addNewCarPositiveTestDP(Car car) {
        letCarWorkPage.typeAddNewCarForm(car);
    }

    @Test
    public void addNewCarNegativeTest_emptyModel() {
        Car car = Car.builder()
                .city("Haifa")
                .manufacture("Opel")
                .model("")
                .year("2020")
                .fuel("Gas")
                .seats(4)
                .carClass("C")
                .serialNumber("Opel" + generateString(7))
                .pricePerDay(100.77)
                .about("about")
                .build();
        letCarWorkPage.typeAddNewCarForm(car);
        Assert.assertTrue(letCarWorkPage.validateErrorModel());
    }

    @Test
    public void addNewCarNegativeTest_emptyPrice() {
        Car car = Car.builder()
                .city("Haifa")
                .manufacture("Opel")
                .model("Astra")
                .year("2020")
                .fuel("Gas")
                .seats(4)
                .carClass("C")
                .serialNumber("Opel" + generateString(7))

                .about("about")
                .build();
        letCarWorkPage.typeAddNewCarForm(car);
        Assert.assertTrue(letCarWorkPage.validateErrorPrice());
    }

    @Test
    public void addNewCarNegativeTest_emptySeats() {
        Car car = Car.builder()
                .city("Haifa")
                .manufacture("Opel")
                .model("Astra")
                .year("2020")
                .fuel("Gas")

                .carClass("C")
                .serialNumber("Opel" + generateString(7))
                .pricePerDay(100.77)
                .about("about")
                .build();
        letCarWorkPage.typeAddNewCarForm(car);
        Assert.assertTrue(letCarWorkPage.validateErrorCity());
    }

    @Test(dataProvider = "addNewCarDPFile", dataProviderClass = CarDP.class)
    public void addNewCarNegativeTest(Car car) {
        logger.info("test data >>>" + car);
        letCarWorkPage.typeAddNewCarForm(car);
        Assert.assertFalse(letCarWorkPage.isEnabledSubmitBtn());
    }
}
