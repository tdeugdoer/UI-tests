package pages.checkout;

import formData.CheckoutFormData;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class CheckoutDataFillingPage extends CheckoutBasePage {
    @FindBy(id = "billing_first_name")
    private WebElement firstNameInput;

    @FindBy(id = "billing_last_name")
    private WebElement lastNameInput;

    @FindBy(id = "billing_country")
    private WebElement countrySelect;

    @FindBy(id = "billing_address_1")
    private WebElement addressInput;

    @FindBy(id = "billing_city")
    private WebElement cityInput;

    @FindBy(id = "billing_state")
    private WebElement stateInput;

    @FindBy(id = "billing_postcode")
    private WebElement postcodeInput;

    @FindBy(id = "billing_phone")
    private WebElement phoneInput;

    @FindBy(id = "order_date")
    private WebElement orderDateInput;

    @FindBy(id = "payment_method_cod")
    private WebElement paymentOnDeliveryRadioRadio;

    @FindBy(id = "terms")
    private WebElement termsCheckbox;

    @FindBy(id = "place_order")
    private WebElement placeOrderButton;

    public CheckoutDataFillingPage(WebDriver driver) {
        super(driver);
    }

    public CheckoutDataFillingPage fillOutOrderDatails(CheckoutFormData formData) {
        enterFirstName(formData.getFirstName());
        enterLastName(formData.getLastName());
        selectCountry(formData.getCountry());
        enterAddress(formData.getAddress());
        enterCity(formData.getCity());
        enterState(formData.getState());
        enterPostcode(formData.getPostcode());
        enterPhone(formData.getPhone());
        return this;
    }

    public CheckoutDataFillingPage enterFirstName(String firstName) {
        firstNameInput.clear();
        firstNameInput.sendKeys(firstName);
        return this;
    }

    public CheckoutDataFillingPage enterLastName(String lastName) {
        lastNameInput.clear();
        lastNameInput.sendKeys(lastName);
        return this;
    }

    public CheckoutDataFillingPage selectCountry(String country) {
        Select countryDropdown = new Select(countrySelect);
        countryDropdown.selectByVisibleText(country);
        return this;
    }

    public CheckoutDataFillingPage enterAddress(String address) {
        addressInput.clear();
        addressInput.sendKeys(address);
        return this;
    }

    public CheckoutDataFillingPage enterCity(String city) {
        cityInput.clear();
        cityInput.sendKeys(city);
        return this;
    }

    public CheckoutDataFillingPage enterState(String state) {
        stateInput.clear();
        stateInput.sendKeys(state);
        return this;
    }

    public CheckoutDataFillingPage enterPostcode(String postcode) {
        postcodeInput.clear();
        postcodeInput.sendKeys(postcode);
        return this;
    }

    public CheckoutDataFillingPage enterPhone(String phone) {
        phoneInput.clear();
        phoneInput.sendKeys(phone);
        return this;
    }

    public CheckoutDataFillingPage enterOrderDate(String orderDate) {
        orderDateInput.clear();
        orderDateInput.sendKeys(orderDate);
        return this;
    }

    public CheckoutDataFillingPage selectPaymentOnDeliveryRadio() {
        paymentOnDeliveryRadioRadio.click();
        return this;
    }

    public CheckoutDataFillingPage clickTermsCheckbox() {
        termsCheckbox.click();
        return this;
    }

    public CheckoutDataFillingPage clickPlaceOrderButton() {
        placeOrderButton.click();
        return this;
    }

}
