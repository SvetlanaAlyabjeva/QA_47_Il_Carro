package ui_tests;

import dto.UserLombok;
import manager.ApplicationManager;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import static utils.RandomUtils.*;

public class LoginTests extends ApplicationManager {

    HomePage homePage;
    LoginPage loginPage;

    @BeforeMethod
    public void goToLoginPage(){
        homePage = new HomePage(getDriver());
        homePage.clickBtnLoginHeader();
        loginPage = new LoginPage(getDriver());
    }

    @Test
    public void loginPositiveTest(){
        HomePage homePage = new HomePage(getDriver());
        homePage.clickBtnLoginHeader();
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.typeLoginForm("email1@mail.ru", "ema31il@Mail.ru");
    }

    @Test
    public void loginPositiveTestLombok(){
        UserLombok user = UserLombok.builder()
                .username("email1@mail.ru")
                .password("ema31il@Mail.ru")
                    .build();
        loginPage.typeLoginForm(user.getUsername(), user.getPassword());
        Assert.assertTrue(loginPage.validatePopUpMessage("Logged in success"), "test upal");
    }

    @Test
    public void loginNegativeTest_unregUser(){
        UserLombok user = UserLombok.builder()
                .username(generateEmail(10))
                .password("ema31il@Mail.ru")
                .build();
        loginPage.typeLoginForm(user.getUsername(), user.getPassword());
        Assert.assertTrue(loginPage.validatePopUpMessage("Login or Password incorrect"), "test upal");
    }

    @Test
    public void loginNegativeTest_emptyPassword(){
        UserLombok user = UserLombok.builder()
                .username("email1@mail.ru")
                .password("")
                .build();
        loginPage.typeLoginForm(user.getUsername(), user.getPassword());
        Assert.assertTrue(loginPage.validateMessageErrorPassword(), "loginNegativeTest_emptyPassword");
    }

    @Test
    public void loginNegativeTest_wrongPassword(){
        UserLombok user = UserLombok.builder()
                .username("email1@mail.ru")
                .password("123")
                .build();
        loginPage.typeLoginForm(user.getUsername(), user.getPassword());
        Assert.assertTrue(loginPage.validatePopUpMessage("Login or Password incorrect"), "loginNegativeTest_wrongPassword");
    }

    @Test
    public void loginNegativeTest_emptyEmail(){
        UserLombok user = UserLombok.builder()
                .username("")
                .password("ema31il@Mail.ru")
                .build();
        loginPage.typeLoginForm(user.getUsername(), user.getPassword());
        Assert.assertTrue(loginPage.validateMessageErrorEmail(), "loginNegativeTest_emptyEmail");
    }

    @Test
    public void loginNegativeTest_wrongFormatEmail(){
        UserLombok user = UserLombok.builder()
                .username("email1mail.ru")
                .password("ema31il@Mail.ru")
                .build();
        loginPage.typeLoginForm(user.getUsername(), user.getPassword());
        Assert.assertTrue(loginPage.validateMessageErrorFormatEmail(), "loginNegativeTest_wrongFormatEmail");
    }
}
