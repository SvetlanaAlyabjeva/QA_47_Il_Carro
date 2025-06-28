package manager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.EventFiringDecorator;
import org.openqa.selenium.support.events.WebDriverListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utils.WDListener;

import java.time.Duration;
import java.time.LocalDateTime;

public class ApplicationManager {
    private WebDriver driver;

    public Logger logger = LoggerFactory.getLogger(ApplicationManager.class);

    public WebDriver getDriver() {
        return driver;
    }

    @BeforeMethod
    public void setup() {
        //logger.info("Start test>>"+ LocalDateTime.now());
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        //new realization WebDriverListener in Selenium4
        WebDriverListener webDriverListener = new WDListener();
        driver = new EventFiringDecorator<>(webDriverListener).decorate(driver);
    }

    @AfterMethod
    public void tearDown() {
        logger.info("End test>>"+ LocalDateTime.now());
        // if(driver!=null)
        //  driver.quit();
    }
}
