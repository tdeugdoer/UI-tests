package ui.catalog;

import messages.catalog.MenuExpectedResult;
import messages.fail.FailMessage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.BasePage;
import pages.menu.MenuPage;
import ui.BasePageTest;
import utils.Constant;

import java.util.List;

public class MenuPageTest extends BasePageTest {
    private final BasePage basePage = new BasePage(driver);
    private final MenuPage menuPage = new MenuPage(driver);

    @BeforeMethod
    public void setUp() {
        basePage.open(Constant.Url.BASE_URL);
    }

    @Test
    public void menuCategoryFiltration() {
        basePage.clickMenuButton();
        menuPage.clickPizzaCategoryButton();

        String resultCount = menuPage.getResultCount();
        Integer cardsCount = menuPage.getMenuCardsSize();

        Assert.assertEquals(resultCount, MenuExpectedResult.COUNT_CARD_MESSAGE, FailMessage.CARD_COUNT_NOT_MATCH_EXPECTED);
        Assert.assertEquals(cardsCount, MenuExpectedResult.EXPECTED_CARD_COUNT, FailMessage.CARD_COUNT_NOT_MATCH_EXPECTED);
    }

    @Test
    public void menuCategoryFiltrationViaDropdown() {
        basePage.clickPizzaButton();

        String resultCount = menuPage.getResultCount();
        Integer cardsCount = menuPage.getMenuCardsSize();

        Assert.assertEquals(resultCount, MenuExpectedResult.COUNT_CARD_MESSAGE, FailMessage.CARD_COUNT_NOT_MATCH_EXPECTED);
        Assert.assertEquals(cardsCount, MenuExpectedResult.EXPECTED_CARD_COUNT, FailMessage.CARD_COUNT_NOT_MATCH_EXPECTED);
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


        Assert.assertTrue(menuCardsPrices.stream()
                .allMatch(price -> price >= expectedMinPrice), FailMessage.MENU_NOT_FILTERED_BY_MIN_PRICE);
        Assert.assertTrue(menuCardsPrices.stream()
                .allMatch(price -> price <= expectedMaxPrice), FailMessage.MENU_NOT_FILTERED_BY_MAX_PRICE);
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
