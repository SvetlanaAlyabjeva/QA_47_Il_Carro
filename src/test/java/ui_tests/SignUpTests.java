package ui_tests;

import dto.UserLombok;
import manager.ApplicationManager;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import pages.SignUpPage;
import utils.HeaderMenuItem;

import static utils.RandomUtils.*;

import static pages.BasePage.*;

public class SignUpTests extends ApplicationManager {
    HomePage homePage;
    SignUpPage signUpPage;

    @BeforeMethod
    public void goToLoginPage() {
        homePage = new HomePage(getDriver());
        homePage.clickBtnLoginHeader();
        signUpPage = clickButtonsOnHeader(HeaderMenuItem.SIGN_UP);
    }

    @Test
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
        Assert.assertTrue(signUpPage.validateErrorMessage("Name is requirereed"));
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

}
