package ui;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterSuite;

public abstract class BasePageTest {
    public final WebDriver driver = new EdgeDriver();

    @AfterSuite(alwaysRun = true)
    public void quitDriver() {
        driver.quit();
    }

}
