package com.demoqa.bookStorePage;

import com.demoqa.CommonConfigurationTest;
import com.demoqa.UserSession;
import com.demoqa.ui.pages.BookStorePage;
import io.qameta.allure.Description;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.lang.reflect.Method;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotEquals;
import static org.testng.Assert.assertTrue;

public class CheckPreviousAndNextButtonsTest extends CommonConfigurationTest {

    private UserSession user;

    @BeforeMethod
    public void setUp(Method method) {
        user = new UserSession("User session: ");
    }

    @AfterMethod
    public void tearDown() {
        user.quit();
    }

    @Test(description = "Check 'Previous' and 'Next' buttons on 'Book Store' page.")
    @Description("Check rows per page drop-down on 'Book Store' page.")
    public void checkPreviousAndNextButtons() {
        BookStorePage bookStorePage = goToBookStorePage(user).waitPageLoading();

        assertTrue(bookStorePage.isNextButtonDisplayed(), "'Next' button is not displayed.");
        assertTrue(bookStorePage.isNextButtonEnabled(), "'Next' button is not enabled.");
        assertTrue(bookStorePage.isPreviousButtonDisplayed(), "'Previous' button is not displayed.");
        assertFalse(bookStorePage.isPreviousButtonEnabled(), "'Previous' button is enabled.");

        checkCurrentPageNumber(bookStorePage, "1");
        List<String> resultsFromSearchBox_1 = bookStorePage.getTextFromRowsInSearchBox();
        bookStorePage.clickNextButton();
        checkCurrentPageNumber(bookStorePage, "2");
        List<String> resultsFromSearchBox_2 = bookStorePage.getTextFromRowsInSearchBox();
        assertNotEquals(resultsFromSearchBox_2, resultsFromSearchBox_1);

        assertTrue(bookStorePage.isNextButtonDisplayed(), "'Next' button is not displayed.");
        assertFalse(bookStorePage.isNextButtonEnabled(), "'Next' button is enabled.");
        assertTrue(bookStorePage.isPreviousButtonDisplayed(), "'Previous' button is not displayed.");
        assertTrue(bookStorePage.isPreviousButtonEnabled(), "'Previous' button is not enabled.");

        bookStorePage.clickPreviousButton();
        checkCurrentPageNumber(bookStorePage, "1");
        List<String> resultsFromSearchBox_3 = bookStorePage.getTextFromRowsInSearchBox();
        assertEquals(resultsFromSearchBox_1, resultsFromSearchBox_3);
    }

    private void checkCurrentPageNumber(BookStorePage bookStorePage, String expectedNumberValue) {
        assertEquals(bookStorePage.getCurrentPageNumber(), expectedNumberValue,
                "Current page number is wrong.");
    }
}
