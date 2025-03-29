package pages.checkout;

import formData.AuthorizationFormData;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CheckoutAuthorizationPage extends CheckoutBasePage {
    @FindBy(xpath = "//a[@class='showlogin']")
    private WebElement authorizationButton;

    @FindBy(id = "username")
    private WebElement usernameInput;

    @FindBy(id = "password")
    private WebElement passwordInput;

    @FindBy(xpath = "//button[@name='login']")
    private WebElement loginButton;

    public CheckoutAuthorizationPage(WebDriver driver) {
        super(driver);
    }

    public CheckoutAuthorizationPage fillOutAuthorizationForm(AuthorizationFormData formData) {
        enterUsername(formData.getUsername());
        enterPassword(formData.getPassword());
        return this;
    }

    public CheckoutAuthorizationPage enterUsername(String username) {
        usernameInput.clear();
        usernameInput.sendKeys(username);
        return this;
    }

    public CheckoutAuthorizationPage enterPassword(String password) {
        passwordInput.clear();
        passwordInput.sendKeys(password);
        return this;
    }

    public CheckoutAuthorizationPage clickAuthorizationButton() {
        authorizationButton.click();
        return this;
    }

    public void clickLoginButton() {
        loginButton.click();
    }

}
