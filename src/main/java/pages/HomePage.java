package pages;

import dev.failsafe.Timeout;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class HomePage extends BasePage{

    public HomePage(WebDriver driver){
        setDriver(driver);
        driver.get("https://ilcarro.web.app/search");
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
    }
    @FindBy(xpath = "//a[text()=' Log in ']")
    WebElement btnLoginHeader;
    //WebElement getBtnLoginHeader = driver.findElement(By.xpath("//a[text()=' Log in ']"));

    public void clickBtnLoginHeader(){
        btnLoginHeader.click();
    }
}
