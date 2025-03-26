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
        menuPage.getPizzaCategoryButton().click();
        String resultCount = menuPage.getResultCount().getText();
        Integer cardsCount = menuPage.getCatalogCards().size();

        Assert.assertEquals(resultCount, MenuExpectedResult.COUNT_CARD_MESSAGE, FailMessage.CARD_COUNT_NOT_MATCH_EXPECTED);
        Assert.assertEquals(cardsCount, MenuExpectedResult.EXPECTED_CARD_COUNT, FailMessage.CARD_COUNT_NOT_MATCH_EXPECTED);
    }

    @Test
    public void menuCategoryFiltrationViaDropdown() {
        basePage.clickPizzaButton();
        String resultCount = menuPage.getResultCount().getText();
        Integer cardsCount = menuPage.getCatalogCards().size();

        Assert.assertEquals(resultCount, MenuExpectedResult.COUNT_CARD_MESSAGE, FailMessage.CARD_COUNT_NOT_MATCH_EXPECTED);
        Assert.assertEquals(cardsCount, MenuExpectedResult.EXPECTED_CARD_COUNT, FailMessage.CARD_COUNT_NOT_MATCH_EXPECTED);
    }

}
