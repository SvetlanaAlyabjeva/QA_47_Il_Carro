package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class LoginPage extends BasePage {
    public LoginPage(WebDriver driver) {
        setDriver(driver);
        PageFactory.initElements(
                new AjaxElementLocatorFactory(driver, 10), this);
    }
    @FindBy(id = "email")
    WebElement inputEmail;
    @FindBy(id = "password")
    WebElement inputPassword;
    @FindBy(xpath = "//button[text() = 'Y’alla!']")
    WebElement btnYalla;
    @FindBy(xpath = "//div[text() = ' Password is required ']")
    WebElement messageErrorPassword;
    @FindBy(xpath = "//div[text() = ' Email is required ']")
    WebElement messageErrorEmail;
    @FindBy(xpath = "//div[@class='error']/div")
    WebElement messageErrorFormatEmail;



    public void typeLoginForm(String email, String password){
        inputEmail.sendKeys(email);
        inputPassword.sendKeys(password);
        btnYalla.click();
    }

    public boolean validateMessageErrorPassword(){
        return isElementPresent(messageErrorPassword);
    }
    public boolean validateMessageErrorEmail(){
        return isElementPresent(messageErrorEmail);
    }
    public boolean validateMessageErrorFormatEmail(){
        return isElementPresent(messageErrorFormatEmail);
    }

}

