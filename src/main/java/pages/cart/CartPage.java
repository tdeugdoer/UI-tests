package pages.cart;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.BasePage;

public class CartPage extends BasePage {
    @FindBy(xpath = "//a[@class='checkout-button button alt wc-forward']")
    private WebElement proceedToPaymentButton;

    public CartPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void clickProceedToPayment() {
        proceedToPaymentButton.click();
    }

}
