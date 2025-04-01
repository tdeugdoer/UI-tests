package ui.order;

import formData.AuthorizationFormData;
import formData.CheckoutFormData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.BasePage;
import pages.cart.CartPage;
import pages.checkout.CheckoutAuthorizationPage;
import pages.checkout.CheckoutBasePage;
import pages.checkout.CheckoutDataFillingPage;
import pages.menu.MenuPage;
import ui.BasePageTest;
import utils.DateUtils;
import utils.TestConstant;
import utils.data.CheckoutData;
import utils.data.LoginData;
import utils.messages.checkout.CheckoutExpectedResult;
import utils.messages.fail.FailMessage;

public class CheckoutBasePageTest extends BasePageTest {
    private final BasePage basePage = new BasePage(driver);
    private final MenuPage menuPage = new MenuPage(driver);
    private final CartPage cartPage = new CartPage(driver);
    private final CheckoutBasePage checkoutBasePage = new CheckoutBasePage(driver);
    private final CheckoutAuthorizationPage checkoutAuthorizationPage = new CheckoutAuthorizationPage(driver);
    private final CheckoutDataFillingPage checkoutDataFillingPage = new CheckoutDataFillingPage(driver);

    private static CheckoutFormData getCheckoutFormData() {
        return CheckoutFormData.builder()
                .firstName(CheckoutData.FIRST_NAME)
                .lastName(CheckoutData.LAST_NAME)
                .country(CheckoutData.COUNTRY)
                .address(CheckoutData.ADDRESS)
                .city(CheckoutData.CITY)
                .state(CheckoutData.STATE)
                .postcode(CheckoutData.POSTCODE)
                .phone(CheckoutData.PHONE)
                .build();
    }

    private static AuthorizationFormData getAuthorizationFormData() {
        return AuthorizationFormData.builder()
                .username(LoginData.EXISTING_EMAIL)
                .password(LoginData.EXISTING_PASSWORD)
                .build();
    }

    @BeforeMethod
    public void setUp() {
        basePage.open(TestConstant.BASE_URL);
    }

    @Test
    public void testPizzaOrder() {
        AuthorizationFormData authorizationFormData = getAuthorizationFormData();
        CheckoutFormData checkoutFormData = getCheckoutFormData();

        String tomorrowString = DateUtils.getTomorrowDateString();

        basePage.clickPizzaButton();
        menuPage.addToCartFirstProduct();
        basePage.clickLinkToCart();
        cartPage.clickProceedToPayment();
        checkoutAuthorizationPage
                .clickAuthorizationButton()
                .fillOutAuthorizationForm(authorizationFormData)
                .clickLoginButton();
        checkoutDataFillingPage
                .fillOutOrderDatails(checkoutFormData)
                .enterOrderDate(tomorrowString)
                .selectPaymentOnDeliveryRadio()
                .clickTermsCheckbox()
                .clickPlaceOrderButton();

        String message = checkoutBasePage
                .tryGetExpectedPostTitle(CheckoutExpectedResult.SUCCESSFUL_ORDER_COMPLETION, TestConstant.DEFAULT_EXPLICIT_DURATION);

        Assert.assertEquals(message, CheckoutExpectedResult.SUCCESSFUL_ORDER_COMPLETION, FailMessage.MESSAGE_NOT_MATCH_EXPECTED);
    }

}
