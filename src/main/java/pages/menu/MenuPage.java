package pages.menu;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.BasePage;

import java.util.List;

@Getter
public class MenuPage extends BasePage {
    @FindBy(xpath = "//li[@class='cat-item cat-item-30']/a")
    private WebElement pizzaCategoryButton;

    @FindBy(xpath = "//p[@class='woocommerce-result-count']")
    private WebElement resultCount;

    @FindBy(xpath = "//ul[@class='products columns-4']/li")
    private List<WebElement> catalogCards;

    public MenuPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

}
