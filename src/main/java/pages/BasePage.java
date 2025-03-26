package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BasePage {
    protected final WebDriver driver;
    private final By loginButton = By.xpath("//a[@class='account' and @href='http://pizzeria.skillbox.cc/my-account/']");
    private final By logoutButton = By.xpath("//a[@class='logout']");

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    public void open(String url) {
        driver.get(url);
    }

    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }

    public BasePage clickLogoutButton() {
        driver.findElement(logoutButton).click();
        return this;
    }

}
