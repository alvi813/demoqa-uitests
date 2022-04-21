package com.demoqa.ui.pages;

import com.demoqa.UserSession;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import java.util.List;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;

public class BookStorePage extends AbstractPageObject {

    private static final String TITLE = "Book Store";
    private static final By LOGIN_BUTTON_LOCATOR = By.id("login");
    private static final By USER_NAME_VALUE_LOCATOR = By.id("userName-value");
    private static final By ROWS_PER_PAGE_DROPDOWN_LIST_LOCATOR = By.xpath("//select[@aria-label='rows per page']");
    private static final By ROWS_PER_PAGE_DROPDOWN_LIST_VALUES_LOCATOR =
            By.xpath("//select[@aria-label='rows per page']/option");
    private static final String ROW_PER_PAGE_VALUE = "./option[text()='%s']";
    private static final By SEARCH_BOX_ROW_LOCATOR = By.xpath("//div[@class='books-wrapper']//div[@role='rowgroup']");
    private static final By NEXT_BUTTON_LOCATOR = By.xpath("//div[@class='-next']//button");
    private static final By PREVIOUS_BUTTON_LOCATOR = By.xpath("//div[@class='-previous']//button");
    private static final By CURRENT_PAGE_NUMBER_LOCATOR = By.xpath("//input[@aria-label='jump to page']");
    private static final By SEARCH_BOX_LOCATOR = By.id("searchBox");


    public BookStorePage(UserSession user) {
        super(user);
    }

    public BookStorePage waitPageLoading() {
        waitPageLoading(TITLE);
        return this;
    }

    @Step("Check if 'Login' button enabled.")
    public boolean isLoginButtonEnabled() {
        return driver.$(LOGIN_BUTTON_LOCATOR).isEnabled();
    }

    @Step("Click on the 'Login' button.")
    public LoginPage clickLoginButton() {
        driver.$(LOGIN_BUTTON_LOCATOR).shouldBe(visible, enabled).click();
        return new LoginPage(user);
    }

    @Step("Get 'User Name' value.")
    public String getUserNameValue() {
        return driver.$(USER_NAME_VALUE_LOCATOR).shouldBe(visible, enabled).getText();
    }

    public boolean isLeftBarTabEnabled() {
        return isLeftBarTabEnabled("Login");
    }

    public LoginPage selectLeftBarTab() {
        selectLeftBarTab("Login");
        return new LoginPage(user);
    }

    @Step("Check if 'Rows per page' drop-down is displayed.")
    public boolean isRowsPerPageDropDownIsDisplayed() {
        return driver.$(ROWS_PER_PAGE_DROPDOWN_LIST_LOCATOR).isDisplayed();
    }

    @Step("Check if 'Rows per page' drop-down is enabled.")
    public boolean isRowsPerPageDropDownIsEnabled() {
        return driver.$(ROWS_PER_PAGE_DROPDOWN_LIST_LOCATOR).isEnabled();
    }

    @Step("Click on the 'Rows per page' drop-down.")
    public BookStorePage clickRowsPerPageDropDown() {
        driver.$(ROWS_PER_PAGE_DROPDOWN_LIST_LOCATOR).shouldBe(visible, enabled).click();
        return this;
    }

    @Step("Get all 'Rows per page' drop-down values.")
    public List<String> getValuesFromRowsPerPageDropDown() {
        return driver.$$(ROWS_PER_PAGE_DROPDOWN_LIST_VALUES_LOCATOR).texts();
    }

    @Step("Get all 'Rows per page' drop-down values.")
    public boolean isValueFromRowsPerPageDropDownDisplayed(String rowPerPageValue) {
        return driver.$(ROWS_PER_PAGE_DROPDOWN_LIST_LOCATOR)
                .$(By.xpath(String.format(ROW_PER_PAGE_VALUE, rowPerPageValue))).isDisplayed();
    }

    @Step("Select value from 'Rows per page' drop-down.")
    public BookStorePage selectValueFromRowsPerPageDropDown(String rowValue) {
        driver.$(ROWS_PER_PAGE_DROPDOWN_LIST_LOCATOR).$(By.xpath(String.format(ROW_PER_PAGE_VALUE, rowValue))).click();
        return this;
    }

    @Step("Get number of rows in 'Search' box.")
    public int getNumberOfRowsInSearchBox() {
        return driver.$$(SEARCH_BOX_ROW_LOCATOR).size();
    }

    @Step("Get number of rows in 'Search' box.")
    public List<String> getTextFromRowsInSearchBox() {
        return driver.$$(SEARCH_BOX_ROW_LOCATOR).texts();
    }

    @Step("Check if 'Next' button is displayed.")
    public boolean isNextButtonDisplayed() {
        return driver.$(NEXT_BUTTON_LOCATOR).isDisplayed();
    }

    @Step("Check if 'Next' button is enabled.")
    public boolean isNextButtonEnabled() {
        return driver.$(NEXT_BUTTON_LOCATOR).isEnabled();
    }

    @Step("Check if 'Previous' button is displayed.")
    public boolean isPreviousButtonDisplayed() {
        return driver.$(PREVIOUS_BUTTON_LOCATOR).isDisplayed();
    }

    @Step("Check if 'Previous' button is enabled.")
    public boolean isPreviousButtonEnabled() {
        return driver.$(PREVIOUS_BUTTON_LOCATOR).isEnabled();
    }

    @Step("Click on the 'Next' button.")
    public BookStorePage clickNextButton() {
        driver.$(NEXT_BUTTON_LOCATOR).shouldBe(enabled, visible).click();
        return this;
    }

    @Step("Click on the 'Previous' button.")
    public BookStorePage clickPreviousButton() {
        driver.$(PREVIOUS_BUTTON_LOCATOR).shouldBe(enabled, visible).click();
        return this;
    }

    @Step("Get current page number.")
    public String getCurrentPageNumber() {
        return driver.$(CURRENT_PAGE_NUMBER_LOCATOR).shouldBe(visible).getValue();
    }

    @Step("Check if 'Search' box is displayed.")
    public boolean isSearchBoxDisplayed() {
        return driver.$(SEARCH_BOX_LOCATOR).isDisplayed();
    }

    @Step("Check if 'Search' box is enabled.")
    public boolean isSearchBoxEnabled() {
        return driver.$(SEARCH_BOX_LOCATOR).isEnabled();
    }

    @Step("Set value in 'Search' box.")
    public BookStorePage setValueInSearchBox(String searchValue) {
        driver.$(SEARCH_BOX_LOCATOR).shouldBe(visible, enabled).setValue(searchValue);
        return this;
    }

    @Step("Get value from 'Search' box.")
    public String getValueFromSearchBox() {
        return driver.$(SEARCH_BOX_LOCATOR).shouldBe(visible, enabled).getValue();
    }
}
