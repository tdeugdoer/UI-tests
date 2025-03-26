package pages.auth;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.BasePage;

public class LoginPage extends BasePage {
    private final By registerButton = By.xpath("//button[@class='custom-register-button']");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void clickRegisterButton() {
        driver.findElement(registerButton).click();
    }

}
