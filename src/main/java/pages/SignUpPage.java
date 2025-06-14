package pages;

import dto.UserLombok;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.testng.Assert;

import java.util.List;

public class SignUpPage extends BasePage {
    public SignUpPage(WebDriver driver) {
        setDriver(driver);
        PageFactory.initElements(
                new AjaxElementLocatorFactory(driver, 10), this);
    }

    @FindBy(id = "name")
    WebElement inputName;
    @FindBy(id = "lastName")
    WebElement inputLastName;
    @FindBy(id = "email")
    WebElement inputEmail;
    @FindBy(id = "password")
    WebElement inputPassword;
    @FindBy(xpath = "//label[@for='terms-of-use']")
    WebElement checkbox;
    @FindBy(xpath = "//button[@type='submit']")
    WebElement btnYalla;
    @FindBy(xpath = "//div[@class='error']")
    List<WebElement> messageErrorNameList;


    public void typeSignUpForm(UserLombok user) {
        inputName.sendKeys(user.getFirstName());
        inputLastName.sendKeys(user.getLastName());
        inputEmail.sendKeys(user.getUsername());
        inputPassword.sendKeys(user.getPassword());
    }

    public void clickCheckbox() {
        int width = checkbox.getRect().getWidth();
        int height = checkbox.getRect().getHeight();
        System.out.println(width + "X" + height);
        Actions actions = new Actions(driver);
        actions.moveToElement(checkbox, -width / 20 * 9, 0).click().perform();
    }

    public void clickBtnYalla() {
        btnYalla.click();

    }

    public boolean btnYallaIsEnabled() {
        return elementIsEnabled(btnYalla);
    }

    public boolean validateErrorMessage(String text) {
        for (WebElement e : messageErrorNameList) {
            //System.out.println(e.getText());
            if (e.getText().contains(text))
                return true;
        }
        return false;
    }
}