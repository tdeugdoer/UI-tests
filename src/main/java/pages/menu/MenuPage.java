package pages.menu;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.json.JsonException;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;
import utils.Constant;

import java.util.List;

public class MenuPage extends BasePage {
    @FindBy(xpath = "//li[@class='cat-item cat-item-30']/a")
    private WebElement pizzaCategoryButton;

    @FindBy(xpath = "//p[@class='woocommerce-result-count']")
    private WebElement resultCount;

    @FindBy(xpath = "//ul[@class='products columns-4']/li")
    private List<WebElement> menuCards;

    @FindBy(xpath = "//div[@class='price-cart']/descendant::bdi")
    private List<WebElement> menuCardsPrices;

    @FindBy(xpath = "//a[@class='button product_type_simple add_to_cart_button ajax_add_to_cart']")
    private List<WebElement> addToCartButtons;

    @FindBy(xpath = "//span[contains(@class, 'ui-slider-handle ui-state-default ui-corner-all')][1]")
    private WebElement minPriceSlider;

    @FindBy(xpath = "//span[contains(@class, 'ui-slider-handle ui-state-default ui-corner-all')][2]")
    private WebElement maxPriceSlider;

    @FindBy(xpath = "//span[@class='from']")
    private WebElement minPriceValue;

    @FindBy(xpath = "//span[@class='to']")
    private WebElement maxPriceValue;

    @FindBy(xpath = "//div[@class='price_slider_amount']/button[@type='submit']")
    private WebElement priceFilteringButton;

    @FindBy(xpath = "//option[@value='price']")
    private WebElement priceAscOrderingOption;

    public MenuPage(WebDriver driver) {
        super(driver);
    }

    public String getResultCount() {
        return resultCount.getText();
    }

    public Integer getMenuCardsSize() {
        return menuCards.size();
    }

    public List<Float> getMenuCardsPrices() {
        return menuCardsPrices.stream()
                .map(WebElement::getText)
                .map(this::parseFloatPriceValue)
                .toList();
    }

    public MenuPage clickPizzaCategoryButton() {
        pizzaCategoryButton.click();
        return this;
    }

    public MenuPage clickPriceFilteringButton() {
        priceFilteringButton.click();
        return this;
    }

    public MenuPage addToCartFirstProduct() {
        if (!addToCartButtons.isEmpty()) {
            addToCartButtons.getFirst().click();
        } else throw new IllegalStateException(Constant.ExceptionMessage.UNABLE_ADD_TO_CART_ERROR);

        return this;
    }

    public MenuPage sortMenuByAscPrice() {
        priceAscOrderingOption.click();
        return this;
    }

    public MenuPage changeMinPrice(Integer newValue) {
        if (newValue < 0) {
            throw new IllegalStateException(Constant.ExceptionMessage.NEGATIVE_PRICE_ERROR);
        }

        if (newValue > parseIntPriceValue(minPriceValue.getText())) {
            increasePriceValueToValue(minPriceSlider, minPriceValue, newValue);
        } else throw new IllegalStateException(Constant.ExceptionMessage.REDUCTION_MIN_PRICE_ERROR);

        return this;
    }

    public MenuPage changeMaxPrice(Integer newValue) {
        if (newValue < 0) {
            throw new IllegalStateException(Constant.ExceptionMessage.NEGATIVE_PRICE_ERROR);
        }

        if (newValue < parseIntPriceValue(maxPriceValue.getText())) {
            reducePriceValueToValue(maxPriceSlider, maxPriceValue, newValue);
        } else throw new IllegalStateException(Constant.ExceptionMessage.INCREASE_MAX_PRICE_ERROR);

        return this;
    }

    private void increasePriceValueToValue(WebElement slider, WebElement priceValue, Integer newValue) {
        while (!parseIntPriceValue(priceValue.getText()).equals(newValue)) {
            moveRightSlider(slider);
        }
    }

    private void reducePriceValueToValue(WebElement slider, WebElement priceValue, Integer newValue) {
        while (!parseIntPriceValue(priceValue.getText()).equals(newValue)) {
            moveLeftSlider(slider);
        }
    }

    private void moveRightSlider(WebElement slider) {
        try {
            new Actions(driver)
                    .dragAndDropBy(slider, 1, 0)
                    .perform();
        } catch (JsonException e) {
            throw new IllegalStateException(Constant.ExceptionMessage.SLIDER_OUT_OF_BOUNDS_ERROR);
        }

    }

    private void moveLeftSlider(WebElement slider) {
        try {
            new Actions(driver)
                    .clickAndHold(slider)
                    .sendKeys(Keys.ARROW_LEFT)
                    .release()
                    .perform();
        } catch (JsonException e) {
            throw new IllegalStateException(Constant.ExceptionMessage.SLIDER_OUT_OF_BOUNDS_ERROR);
        }

    }

    private Integer parseIntPriceValue(String priceValue) {
        return Integer.parseInt(priceValue.replace("₽", ""));
    }

    private Float parseFloatPriceValue(String priceValue) {
        return Float.parseFloat(priceValue
                .replace(",", ".")
                .replace("₽", ""));
    }

}
