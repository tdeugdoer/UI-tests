package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

public class BasePage {
    protected final WebDriver driver;
    private final By loginButton = By.xpath("//a[@class='account' and @href='http://pizzeria.skillbox.cc/my-account/']");
    private final By logoutButton = By.xpath("//a[@class='logout']");
    private final By menuButton = By.id("menu-item-389");
    private final By pizzaButton = By.xpath("//li[@id='menu-item-390']/a");

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    public void open(String url) {
        driver.get(url);
    }

    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }

    public void clickLogoutButton() {
        driver.findElement(logoutButton).click();
    }

    public void clickMenuButton() {
        driver.findElement(menuButton).click();
    }

    public void clickPizzaButton() {
        new Actions(driver)
                .moveToElement(driver.findElement(menuButton))
                .pause(500)
                .perform();
        driver.findElement(pizzaButton).click();
    }

}
