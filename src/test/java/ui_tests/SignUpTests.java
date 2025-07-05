package ui_tests;

import dto.UserLombok;
import manager.ApplicationManager;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import pages.SignUpPage;
import utils.HeaderMenuItem;
import utils.RetryAnalyzer;
import utils.TestNGListener;

import static utils.RandomUtils.*;

import static pages.BasePage.*;
@Listeners(TestNGListener.class)

public class SignUpTests extends ApplicationManager {
    HomePage homePage;
    SignUpPage signUpPage;

    @BeforeMethod
    public void goToLoginPage() {
        homePage = new HomePage(getDriver());
        homePage.clickBtnLoginHeader();
        signUpPage = clickButtonsOnHeader(HeaderMenuItem.SIGN_UP);
    }

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void signUpPositiiveTest() {
        UserLombok user = UserLombok.builder()
                .firstName("booby")
                .lastName("boom")
                .username(generateEmail(7))
                .password("Password123!")
                .build();
        signUpPage.typeSignUpForm(user);
        signUpPage.clickCheckbox();
        signUpPage.clickBtnYalla();
        Assert.assertTrue(signUpPage.validatePopUpMessage("Registered"));
    }

    @Test
    public void signUpNegativeTest_WOcheckBox() {
        UserLombok user = UserLombok.builder()
                .firstName("booby")
                .lastName("boom")
                .username(generateEmail(7))
                .password("Password123!")
                .build();
        signUpPage.typeSignUpForm(user);
        //signUpPage.clickCheckbox();
        signUpPage.clickBtnYalla();
        Assert.assertFalse(signUpPage.btnYallaIsEnabled());
    }

    @Test
    public void signUpNegativeTest_WOname() {
        UserLombok user = UserLombok.builder()
                .firstName("")
                .lastName("boom")
                .username(generateEmail(7))
                .password("Password123!")
                .build();
        signUpPage.typeSignUpForm(user);
        signUpPage.clickCheckbox();
        signUpPage.clickBtnYalla();
        Assert.assertTrue(signUpPage.validateErrorMessage("Name is required"));
    }

    @Test
    public void signUpNegativeTest_WOLastname() {
        UserLombok user = UserLombok.builder()
                .firstName("Billy")
                .lastName("")
                .username(generateEmail(7))
                .password("Password123!")
                .build();
        signUpPage.typeSignUpForm(user);
        signUpPage.clickCheckbox();
        signUpPage.clickBtnYalla();
        Assert.assertTrue(signUpPage.validateErrorMessage("Last name is required"));
    }

    @Test
    public void signUpNegativeTest_WOLastemail() {
        UserLombok user = UserLombok.builder()
                .firstName("Billy")
                .lastName("Boom")
                .username("")
                .password("Password123!")
                .build();
        signUpPage.typeSignUpForm(user);
        signUpPage.clickCheckbox();
        signUpPage.clickBtnYalla();
        Assert.assertTrue(signUpPage.validateErrorMessage("Email is required"));
    }

    @Test
    public void signUpNegativeTest_WOPassword() {
        UserLombok user = UserLombok.builder()
                .firstName("Billy")
                .lastName("Boom")
                .username(generateEmail(7))
                .password("")
                .build();
        signUpPage.typeSignUpForm(user);
        signUpPage.clickCheckbox();
        signUpPage.clickBtnYalla();
        Assert.assertTrue(signUpPage.validateErrorMessage("Password is required"));
    }

    @Test
    public void signUpNegativeTest_WrongFormatMail() {
        UserLombok user = UserLombok.builder()
                .firstName("booby")
                .lastName("boom")
                //.username(generateEmail(7))
                .username("123gmail.com")
                .password("Password123!")
                .build();
        signUpPage.typeSignUpForm(user);
        signUpPage.clickCheckbox();
        signUpPage.clickBtnYalla();
        Assert.assertTrue(signUpPage.validateErrorMessage("Wrong email format"));
    }

    @Test
    public void signUpNegativeTest_WrongFormatPassShort() {
        UserLombok user = UserLombok.builder()
                .firstName("booby")
                .lastName("boom")
                .username(generateEmail(7))
                .password("Pad123!")
                .build();
        signUpPage.typeSignUpForm(user);
        signUpPage.clickCheckbox();
        signUpPage.clickBtnYalla();
        Assert.assertTrue(signUpPage.validateErrorMessage("Password must contain minimum 8 symbols"));
    }
     @Test
    public void signUpNegativeTest_WrongFormatPassSymbols () {
        UserLombok user = UserLombok.builder()
                .firstName("booby")
                .lastName("boom")
                .username(generateEmail(7))
                .password("Password1234")
                .build();
        signUpPage.typeSignUpForm(user);
        signUpPage.clickCheckbox();
        signUpPage.clickBtnYalla();
        Assert.assertTrue(signUpPage.validateErrorMessage("Password must contain 1 uppercase letter, 1 lowercase letter, 1 number and one special symbol of [@$#^&*!]"));
    }

}
