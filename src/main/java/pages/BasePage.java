package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.HeaderMenuItem;

public abstract class BasePage {

    static WebDriver driver;

    public static void setDriver(WebDriver wd) {
        driver = wd;
    }

    @FindBy(xpath = "//div[@class='dialog-container']")
    WebElement popUpMessage;

    public boolean validatePopUpMessage(String text) {
        return isTextElementPresent(popUpMessage, text);
    }


    public void pause(int time) {
        try {
            Thread.sleep(time * 1000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }


    public static <T extends BasePage> T clickButtonsOnHeader(HeaderMenuItem headerMenuItem) {
        WebElement element = driver.findElement(By.xpath(headerMenuItem.getLocator()));
        element.click();
        switch (headerMenuItem) {
            case LOGIN -> {
                return (T) new LoginPage(driver);
            }
            case SEARCH -> {
                return (T) new HomePage(driver);
            }
            case TERMS -> {
                return (T) new TermsOfUsePage(driver);
            }
            case SIGN_UP -> {
                return (T) new SignUpPage(driver);
            }
            case LET_CAR_WORK -> {
                return (T) new LetCarWorkPage(driver);
            }
            default -> throw new IllegalArgumentException("Invalid parameter headerMenuItem");
        }

    }

    public boolean isTextElementPresent(WebElement element, String text) {
        return element.getText().contains(text);
    }

    public boolean isElementPresent(WebElement element) {
        return element.isDisplayed();
    }

    public boolean elementIsEnabled(WebElement element) {
        return element.isEnabled();
    }

}
