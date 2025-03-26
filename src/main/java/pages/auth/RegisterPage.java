package pages.auth;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.BasePage;

public class RegisterPage extends BasePage {
    private final By usernameField = By.id("reg_username");
    private final By emailField = By.id("reg_email");
    private final By passwordField = By.id("reg_password");
    private final By registerButton = By.xpath("//button[@name='register']");
    private final By successRegisterMessage = By.xpath("//div[@class='content-page']/div");
    private final By errorMessage = By.xpath("//ul[@class='woocommerce-error']/li");

    public RegisterPage(WebDriver driver) {
        super(driver);
    }

    public RegisterPage fillOutForm(String username, String email, String password) {
        driver.findElement(usernameField).sendKeys(username);
        driver.findElement(emailField).sendKeys(email);
        driver.findElement(passwordField).sendKeys(password);
        return this;
    }

    public RegisterPage clickRegisterButton() {
        driver.findElement(registerButton).click();
        return this;
    }

    public String getSuccessRegisterMessage() {
        return driver.findElement(successRegisterMessage).getText();
    }

    public String getErrorMessage() {
        return driver.findElement(errorMessage).getText();
    }

}
