package com.aut.tests;

import com.aut.pages.GoogleSearchPage;
import com.aut.pages.IMDBPage;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class IMDBRatingTest {

    @Parameters({"movieName", "linkToBeClickOn", "textInsideAnchorTag"})
    @Test
    public void verifyRatingsOfTheMovie(String movieName, String linkToBeClickOn, String textInsideAnchorTag) throws Exception {
        GoogleSearchPage search = new GoogleSearchPage();
        search.getMeGooglePage();
        search.doSearch(movieName);
        search.findTheExpectedLinkOnSearchResultPage(linkToBeClickOn, textInsideAnchorTag);
        IMDBPage imdb = new IMDBPage(search.getDriver());
        Assert.assertTrue(imdb.isAtIMDBPage(), "Not at the right place");
        String expectedRatings = imdb.getRatingsOfTheMovie();
        Assert.assertEquals("7.0", expectedRatings, "");
    }
}
