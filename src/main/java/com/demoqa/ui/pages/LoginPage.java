package com.demoqa.ui.pages;

import com.demoqa.UserSession;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;

public class LoginPage extends AbstractPageObject {

    private static final String TITLE = "Login";
    private static final By LOGIN_BUTTON_LOCATOR = By.id("login");
    private static final By USER_NAME_FIELD_LOCATOR = By.id("userName");
    private static final By PASSWORD_FIELD_LOCATOR = By.id("password");

    public LoginPage(UserSession user) {
        super(user);
    }

    public LoginPage waitPageLoading() {
        waitPageLoading(TITLE);
        return this;
    }

    @Step("Check if 'Login' button is enabled.")
    public boolean isLoginButtonEnabled() {
        return driver.$(LOGIN_BUTTON_LOCATOR).isEnabled();
    }

    @Step("Click on the 'Login' button.")
    public BookStorePage clickLoginButton() {
        driver.$(LOGIN_BUTTON_LOCATOR).shouldBe(visible, enabled).click();
        return new BookStorePage(user);
    }

    @Step("Check if 'User Name' field is displayed.")
    public boolean isUserNameFieldDisplayed() {
        return driver.$(USER_NAME_FIELD_LOCATOR).isDisplayed();
    }

    @Step("Set value in 'User Name' field.")
    public LoginPage setValueInUserNameField(String userName) {
        driver.$(USER_NAME_FIELD_LOCATOR).shouldBe(visible, enabled).setValue(userName);
        return this;
    }

    @Step("Get value from 'User Name' field.")
    public String getValueFromUserNameField() {
        return driver.$(USER_NAME_FIELD_LOCATOR).shouldBe(visible, enabled).getValue();
    }

    @Step("Check if 'Password' field is displayed.")
    public boolean isPasswordFieldDisplayed() {
        return driver.$(PASSWORD_FIELD_LOCATOR).isDisplayed();
    }

    @Step("Set value in 'Password' field.")
    public LoginPage setValueInPasswordField(String userName) {
        driver.$(PASSWORD_FIELD_LOCATOR).shouldBe(visible, enabled).setValue(userName);
        return this;
    }

    @Step("Get value from 'Password' field.")
    public String getValueFromPasswordField() {
        return driver.$(PASSWORD_FIELD_LOCATOR).shouldBe(visible, enabled).getValue();
    }
}
