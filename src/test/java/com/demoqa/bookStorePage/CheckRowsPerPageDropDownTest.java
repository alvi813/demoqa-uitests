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
import static org.testng.Assert.assertTrue;

public class CheckRowsPerPageDropDownTest extends CommonConfigurationTest {

    private UserSession user;

    @BeforeMethod
    public void setUp(Method method) {
        user = new UserSession("User session: ");
    }

    @AfterMethod
    public void tearDown() {
        user.quit();
    }

    @Test(description = "Check rows per page drop-down on 'Book Store' page.")
    @Description("Check rows per page drop-down on 'Book Store' page.")
    public void checkRowsPerPageDropDown() {
        BookStorePage bookStorePage = goToBookStorePage(user).waitPageLoading();

        assertTrue(bookStorePage.isRowsPerPageDropDownIsDisplayed(),
                "Rows per page' drop-down is not displayed.");
        assertTrue(bookStorePage.isRowsPerPageDropDownIsEnabled(),
                "Rows per page' drop-down is not enabled.");

        List<String> rowsPerPageValues = bookStorePage.getValuesFromRowsPerPageDropDown();
        bookStorePage.clickRowsPerPageDropDown();
        for (String rowValue : rowsPerPageValues) {
            assertTrue(bookStorePage.isValueFromRowsPerPageDropDownDisplayed(rowValue),
                    String.format("Row '%s' from 'Rows per page' drop-down is not displayed.", rowValue));
        }
        // to wrap 'Rows per page' drop-down:
        bookStorePage.clickRowsPerPageDropDown();

        for (String rowValue : rowsPerPageValues) {
            bookStorePage.clickRowsPerPageDropDown();
            bookStorePage.selectValueFromRowsPerPageDropDown(rowValue);
            assertEquals(String.valueOf(bookStorePage.getNumberOfRowsInSearchBox()),
                    rowValue.replaceAll("\\D+", ""), "Number of rows in 'Search' box is wrong.");
        }
    }
}
