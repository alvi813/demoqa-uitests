package com.demoqa;

import com.codeborne.selenide.SelenideConfig;

import com.codeborne.selenide.SelenideDriver;
import io.qameta.allure.Step;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;


import java.util.logging.Level;

@Slf4j
public class UserSession {

    @Getter
    private final SelenideDriver driver;
    @Getter
    private final String testName;

    public UserSession(String testMethodName) {
        testName = testMethodName + LocalDateTime.now();
        driver = new SelenideDriver(getSelenideConfigProperties());
        log.info("Driver was created");
    }

    protected SelenideConfig getSelenideConfigProperties() {
        LoggingPreferences logPrefs = new LoggingPreferences();
        logPrefs.enable(LogType.PERFORMANCE, Level.ALL);
        logPrefs.enable(LogType.BROWSER, Level.ALL);

        SelenideConfig config = new SelenideConfig();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox");
        config.browserCapabilities().setCapability(ChromeOptions.CAPABILITY, options);
        config.browserCapabilities().setCapability("goog:loggingPrefs", logPrefs);

        return config;
    }

    public void openBrowser(String url) {
        driver.open(url);
        driver.getWebDriver().manage().window().maximize();
    }

    @Step("Call driver's quit() method.")
    public void quit() {
        log.info("Quits this driver, closing every associated window.");
        if (driver.hasWebDriverStarted()) {
            driver.getWebDriver().quit();
        } else {
            log.warn("Driver is already closed.");
        }
    }
}
