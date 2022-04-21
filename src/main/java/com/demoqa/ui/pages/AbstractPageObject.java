package com.demoqa.ui.pages;

import com.codeborne.selenide.SelenideDriver;
import com.demoqa.UserSession;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import java.time.Duration;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;

public class AbstractPageObject {

    public static final String TITLE_LOCATOR = "//div[@class='main-header'][text()='%s']";
    public static final String LEFT_BAR_TAB_LOCATOR = "//ul[@class='menu-list']//span[text()='%s']";
    private static final Duration PAGE_LOADING_TIMEOUT = Duration.ofSeconds(5);
    protected final UserSession user;
    protected final SelenideDriver driver;

    public AbstractPageObject(UserSession user) {
        this.user = user;
        this.driver = user.getDriver();
    }

    @Step("Waiting for page {this} load.")
    public AbstractPageObject waitPageLoading(String titleName) {
        try {
            driver.$(By.xpath(String.format(TITLE_LOCATOR, titleName))).should(visible, PAGE_LOADING_TIMEOUT);
        } catch (AssertionError e) {
            throw new AssertionError(String.format("'%s' page wasn't loaded within %d seconds.",
                    titleName, PAGE_LOADING_TIMEOUT.getSeconds()), e);
        }
        return this;
    }

    @Step("Check if left bar {this} tab enabled.")
    public boolean isLeftBarTabEnabled(String tabName) {
        return driver.$(By.xpath(String.format(LEFT_BAR_TAB_LOCATOR, tabName))).isEnabled();
    }

    @Step("Select left bar {this} tab.")
    public void selectLeftBarTab(String tabName) {
        driver.$(By.xpath(String.format(LEFT_BAR_TAB_LOCATOR, tabName))).shouldBe(visible, enabled).click();
    }
}
