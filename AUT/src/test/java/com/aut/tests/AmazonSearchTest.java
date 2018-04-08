package com.aut.tests;

import com.aut.pages.AmazonHomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AmazonSearchTest {

    @Test
    void verifySearch() throws Exception {
        AmazonHomePage amazon = new AmazonHomePage();
        amazon.getMeAmazonPage();
        Assert.assertTrue(amazon.isAtAmazonPage());
        amazon.doSearchBySelectingCategory("Electronics","iphone 6",20,500);
        amazon.getInFoFromSearchPage();
        amazon.selectFirstLinkInSearchPage();
    }
}
