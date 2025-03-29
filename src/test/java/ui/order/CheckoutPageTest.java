package ui.order;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.BasePage;
import pages.cart.CartPage;
import pages.cart.formData.AuthorizationFormData;
import pages.cart.formData.CheckoutFormData;
import pages.checkout.CheckoutPage;
import pages.menu.MenuPage;
import ui.BasePageTest;
import utils.TestConstant;
import utils.data.CheckoutData;
import utils.data.LoginData;
import utils.messages.checkout.CheckoutExpectedResult;
import utils.messages.fail.FailMessage;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CheckoutPageTest extends BasePageTest {
    private final BasePage basePage = new BasePage(driver);
    private final MenuPage menuPage = new MenuPage(driver);
    private final CartPage cartPage = new CartPage(driver);
    private final CheckoutPage checkoutPage = new CheckoutPage(driver);

    @BeforeMethod
    public void setUp() {
        basePage.open(TestConstant.BASE_URL);
    }

    @Test
    public void testPizzaOrder() {
        AuthorizationFormData authorizationFormData = AuthorizationFormData.builder()
                .username(LoginData.EXISTING_EMAIL)
                .password(LoginData.EXISTING_PASSWORD)
                .build();
        CheckoutFormData checkoutFormData = CheckoutFormData.builder()
                .firstName(CheckoutData.FIRST_NAME)
                .lastName(CheckoutData.LAST_NAME)
                .country(CheckoutData.COUNTRY)
                .address(CheckoutData.ADDRESS)
                .city(CheckoutData.CITY)
                .state(CheckoutData.STATE)
                .postcode(CheckoutData.POSTCODE)
                .phone(CheckoutData.PHONE)
                .build();

        LocalDate tomorrow = LocalDate.now().plusDays(1);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String tomorrowString = tomorrow.format(formatter);

        basePage.clickPizzaButton();
        menuPage.addToCartFirstProduct();
        basePage.clickLinkToCart();
        cartPage.clickProceedToPayment();

        String message = checkoutPage
                .clickAuthorizationButton()
                .fillOutAuthorizationForm(authorizationFormData)
                .clickLoginButton()
                .fillOutOrderDatails(checkoutFormData)
                .enterOrderDate(tomorrowString)
                .selectPaymentOnDeliveryRadio()
                .clickTermsCheckbox()
                .clickPlaceOrderButton()
                .tryGetExpectedPostTitle(CheckoutExpectedResult.SUCCESSFUL_ORDER_COMPLETION, TestConstant.DEFAULT_EXPLICIT_DURATION);

        Assert.assertEquals(message, CheckoutExpectedResult.SUCCESSFUL_ORDER_COMPLETION, FailMessage.MESSAGE_NOT_MATCH_EXPECTED);
    }

}
