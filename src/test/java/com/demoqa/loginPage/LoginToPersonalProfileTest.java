package com.demoqa.loginPage;

import com.demoqa.CommonConfigurationTest;
import com.demoqa.ui.pages.BookStorePage;
import com.demoqa.ui.pages.LoginPage;
import io.qameta.allure.Description;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

@Slf4j
public class LoginToPersonalProfileTest extends CommonConfigurationTest {

    @Test(description = "Login to 'Personal Profile' via 'Login' button.")
    @Description("Check login to 'Personal Profile' via 'Login' button.")
    public void loginToPersonalProfileViaLoginButton() {
        BookStorePage bookStorePage = goToBookStorePage(user).waitPageLoading();
        assertTrue(bookStorePage.isLoginButtonEnabled(),
                "'Login' button is not enabled on 'Book Store' page.");
        LoginPage loginPage = bookStorePage.clickLoginButton().waitPageLoading();

        checkUserNameFieldOnLoginPage(loginPage);
        checkPasswordFieldOnLoginPage(loginPage);
        clickLoginAndCheckUserNameValue(loginPage);
    }

    @Test(description = "Login to 'Personal Profile' via left menu.")
    @Description("Check login to 'Personal Profile' via left menu.")
    public void loginToPersonalProfileViaLeftMenu() {
        BookStorePage bookStorePage = goToBookStorePage(user).waitPageLoading();
        assertTrue(bookStorePage.isLeftBarTabEnabled(),
                "'Login' tab is not enabled on 'Book Store' page.");
        LoginPage loginPage = bookStorePage.selectLeftBarTab().waitPageLoading();

        checkUserNameFieldOnLoginPage(loginPage);
        checkPasswordFieldOnLoginPage(loginPage);
        clickLoginAndCheckUserNameValue(loginPage);
    }

    private void checkUserNameFieldOnLoginPage(LoginPage loginPage) {
        assertTrue(loginPage.isUserNameFieldDisplayed(),
                "'User Name' field is not displayed on 'Login' page.");
        loginPage.setValueInUserNameField(userName);
        assertEquals(loginPage.getValueFromUserNameField(), userName,
                "'User name' value in the 'User Name' field is wrong.");
    }

    private void checkPasswordFieldOnLoginPage(LoginPage loginPage) {
        assertTrue(loginPage.isPasswordFieldDisplayed(),
                "'Password' field is not displayed on 'Login' page.");
        loginPage.setValueInPasswordField(password);
        assertEquals(loginPage.getValueFromPasswordField(), password,
                "'Password' value in the 'Password' field is wrong.");
    }

    private void clickLoginAndCheckUserNameValue(LoginPage loginPage) {
        assertTrue(loginPage.isLoginButtonEnabled(),
                "'Login' button is not enabled on 'Login' page.");
        BookStorePage bookStorePage = loginPage.clickLoginButton();
        assertEquals(bookStorePage.getUserNameValue(), userName,
                "'User Name' value on 'Book Store' page after login is wrong.");
    }
}
