package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {
    protected final WebDriver driver;
    private final By loginPageButton = By.xpath("//a[@class='account' and @href='http://pizzeria.skillbox.cc/my-account/']");
    private final By logoutButton = By.xpath("//a[@class='logout']");
    private final By menuButton = By.id("menu-item-389");
    private final By pizzaButton = By.xpath("//li[@id='menu-item-390']/a");
    private final By linkToCart = By.xpath("//a[@class='cart-contents wcmenucart-contents']");

    public BasePage(WebDriver driver) {
        this.driver = driver;
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    public void open(String url) {
        driver.get(url);
    }

    public void clickLoginPageButton() {
        driver.findElement(loginPageButton).click();
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

    public void clickLinkToCart() {
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .ignoring(StaleElementReferenceException.class)
                .until(d -> {
                    d.findElement(linkToCart).click();
                    return true;
                });
    }

}
