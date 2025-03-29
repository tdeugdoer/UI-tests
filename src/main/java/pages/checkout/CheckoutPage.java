package pages.checkout;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.BasePage;
import pages.cart.formData.AuthorizationFormData;
import pages.cart.formData.CheckoutFormData;

import java.time.Duration;

public class CheckoutPage extends BasePage {
    @FindBy(xpath = "//a[@class='showlogin']")
    private WebElement authorizationButton;

    @FindBy(id = "username")
    private WebElement usernameInput;

    @FindBy(id = "password")
    private WebElement passwordInput;

    @FindBy(xpath = "//button[@name='login']")
    private WebElement loginButton;

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

    @FindBy(xpath = "//h2[@class='post-title']")
    private WebElement postTitle;

    public CheckoutPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public String getPostTitle() {
        return postTitle.getText();
    }

    public String tryGetExpectedPostTitle(String expectedPostTitle, Duration timeout) {
        new WebDriverWait(driver, timeout)
                .until(ExpectedConditions.textToBePresentInElement(postTitle, expectedPostTitle));
        return getPostTitle();
    }

    public CheckoutPage fillOutAuthorizationForm(AuthorizationFormData formData) {
        enterUsername(formData.getUsername());
        enterPassword(formData.getPassword());
        return this;
    }

    public CheckoutPage fillOutOrderDatails(CheckoutFormData formData) {
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

    public CheckoutPage enterUsername(String username) {
        usernameInput.clear();
        usernameInput.sendKeys(username);
        return this;
    }

    public CheckoutPage enterPassword(String password) {
        passwordInput.clear();
        passwordInput.sendKeys(password);
        return this;
    }

    public CheckoutPage enterFirstName(String firstName) {
        firstNameInput.clear();
        firstNameInput.sendKeys(firstName);
        return this;
    }

    public CheckoutPage enterLastName(String lastName) {
        lastNameInput.clear();
        lastNameInput.sendKeys(lastName);
        return this;
    }

    public CheckoutPage selectCountry(String country) {
        Select countryDropdown = new Select(countrySelect);
        countryDropdown.selectByVisibleText(country);
        return this;
    }

    public CheckoutPage enterAddress(String address) {
        addressInput.clear();
        addressInput.sendKeys(address);
        return this;
    }

    public CheckoutPage enterCity(String city) {
        cityInput.clear();
        cityInput.sendKeys(city);
        return this;
    }

    public CheckoutPage enterState(String state) {
        stateInput.clear();
        stateInput.sendKeys(state);
        return this;
    }

    public CheckoutPage enterPostcode(String postcode) {
        postcodeInput.clear();
        postcodeInput.sendKeys(postcode);
        return this;
    }

    public CheckoutPage enterPhone(String phone) {
        phoneInput.clear();
        phoneInput.sendKeys(phone);
        return this;
    }

    public CheckoutPage enterOrderDate(String orderDate) {
        orderDateInput.clear();
        orderDateInput.sendKeys(orderDate);
        return this;
    }

    public CheckoutPage clickAuthorizationButton() {
        authorizationButton.click();
        return this;
    }

    public CheckoutPage clickLoginButton() {
        loginButton.click();
        return this;
    }

    public CheckoutPage selectPaymentOnDeliveryRadio() {
        paymentOnDeliveryRadioRadio.click();
        return this;
    }

    public CheckoutPage clickTermsCheckbox() {
        termsCheckbox.click();
        return this;
    }

    public CheckoutPage clickPlaceOrderButton() {
        placeOrderButton.click();
        return this;
    }

}
