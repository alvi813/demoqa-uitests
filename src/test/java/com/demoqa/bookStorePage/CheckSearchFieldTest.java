package com.demoqa.bookStorePage;

import com.demoqa.CommonConfigurationTest;
import com.demoqa.ui.pages.BookStorePage;
import io.qameta.allure.Description;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class CheckSearchFieldTest extends CommonConfigurationTest {

    private final String searchValue = "Java";

    @Test(description = "Check rows per page drop-down on 'Book Store' page.")
    @Description("Check rows per page drop-down on 'Book Store' page.")
    public void checkRowsPerPageDropDown() {
        BookStorePage bookStorePage = goToBookStorePage(user).waitPageLoading();

        assertTrue(bookStorePage.isSearchBoxDisplayed(), "'Search' box is not displayed.");
        assertTrue(bookStorePage.isSearchBoxEnabled(), "'Search' box is not enabled.");

        bookStorePage.setValueInSearchBox(searchValue);
        assertEquals(bookStorePage.getValueFromSearchBox(), searchValue, "The value in 'Search' box is wrong.");

        List<String> resultsFromSearchBox = bookStorePage.getTextFromRowsInSearchBox();

        // since there may be empty lines at the end, need to filter:
        final Pattern emptyRegExp = Pattern.compile("\\s+");
        Matcher resultsValidation;
        List<String> notEmptyResultsFromSearchBox = new ArrayList<>();
        for (String book : resultsFromSearchBox) {
            resultsValidation = emptyRegExp.matcher(book);
            if (!resultsValidation.matches()) {
                notEmptyResultsFromSearchBox.add(book);
            }
        }

        for (String book : notEmptyResultsFromSearchBox) {
            assertTrue(book.contains(searchValue),
                    String.format("Search result '%s' does not contain search value '%s'.", book, searchValue));
        }
    }
}
