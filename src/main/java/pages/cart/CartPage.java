package pages.cart;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;

public class CartPage extends BasePage {
    @FindBy(xpath = "//a[@class='checkout-button button alt wc-forward']")
    private WebElement proceedToPaymentButton;

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public void clickProceedToPayment() {
        proceedToPaymentButton.click();
    }

}
