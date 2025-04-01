package ui.catalog;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.BasePage;
import pages.menu.MenuPage;
import ui.BasePageTest;
import utils.TestConstant;
import utils.messages.catalog.MenuExpectedResult;
import utils.messages.fail.FailMessage;

import java.util.List;

public class MenuPageTest extends BasePageTest {
    private final BasePage basePage = new BasePage(driver);
    private final MenuPage menuPage = new MenuPage(driver);

    @BeforeMethod
    public void setUp() {
        basePage.open(TestConstant.BASE_URL);
    }

    @Test
    public void menuCategoryFiltration() {
        basePage.clickMenuButton();
        menuPage.clickPizzaCategoryButton();

        String resultCount = menuPage.getResultCount();
        Integer cardsCount = menuPage.getMenuCardsSize();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(resultCount, "MenuExpectedResult.COUNT_CARD_MESSAGE", FailMessage.CARD_COUNT_NOT_MATCH_EXPECTED);
        softAssert.assertEquals(cardsCount, MenuExpectedResult.EXPECTED_CARD_COUNT, FailMessage.CARD_COUNT_NOT_MATCH_EXPECTED);
        softAssert.assertAll();
    }

    @Test
    public void menuCategoryFiltrationViaDropdown() {
        basePage.clickPizzaButton();

        String resultCount = menuPage.getResultCount();
        Integer cardsCount = menuPage.getMenuCardsSize();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(resultCount, MenuExpectedResult.COUNT_CARD_MESSAGE, FailMessage.CARD_COUNT_NOT_MATCH_EXPECTED);
        softAssert.assertEquals(cardsCount, MenuExpectedResult.EXPECTED_CARD_COUNT, FailMessage.CARD_COUNT_NOT_MATCH_EXPECTED);
        softAssert.assertAll();
    }

    @Test
    public void menuPriceFiltration() {
        Integer expectedMinPrice = 300;
        Integer expectedMaxPrice = 480;

        basePage.clickMenuButton();
        List<Float> menuCardsPrices = menuPage
                .changeMinPrice(expectedMinPrice)
                .changeMaxPrice(expectedMaxPrice)
                .clickPriceFilteringButton()
                .getMenuCardsPrices();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(menuCardsPrices.stream()
                .allMatch(price -> price >= expectedMinPrice), FailMessage.MENU_NOT_FILTERED_BY_MIN_PRICE);
        softAssert.assertTrue(menuCardsPrices.stream()
                .allMatch(price -> price <= expectedMaxPrice), FailMessage.MENU_NOT_FILTERED_BY_MAX_PRICE);
        softAssert.assertAll();
    }

    @Test
    public void menuPriceAscSorting() {
        basePage.clickMenuButton();
        menuPage.sortMenuByAscPrice();

        List<Float> menuCardsPrices = menuPage.getMenuCardsPrices();

        Assert.assertEquals(menuCardsPrices, menuCardsPrices.stream()
                .sorted()
                .toList(), FailMessage.MENU_NOT_SORTED_BY_PRICE_ASC);
    }

}
