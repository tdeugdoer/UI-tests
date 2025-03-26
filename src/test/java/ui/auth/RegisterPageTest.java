package ui.auth;

import messages.auth.RegisterMessage;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.BasePage;
import pages.auth.LoginPage;
import pages.auth.RegisterPage;
import ui.BasePageTest;
import utils.Constant;
import utils.RegisterData;
import utils.StringGenerator;

public class RegisterPageTest extends BasePageTest {
    private final BasePage basePage = new BasePage(driver);
    private final LoginPage loginPage = new LoginPage(driver);
    private final RegisterPage registerPage = new RegisterPage(driver);

    @Test
    public void successRegister() {
        String username = StringGenerator.getUniqueString();
        String password = StringGenerator.getUniqueString();
        String email = StringGenerator.getUniqueString() + Constant.EMAIL_SUFFIX;

        basePage.open(Constant.URL.BASE_URL);
        String message = openRegisterPageAndFillOutFields(username, email, password)
                .clickRegisterButton()
                .getSuccessRegisterMessage();

        Assert.assertEquals(message, RegisterMessage.SUCCESS_REGISTER);
    }

    @Test
    public void repeatRegister() {
        String username = StringGenerator.getUniqueString();
        String password = StringGenerator.getUniqueString();
        String email = StringGenerator.getUniqueString() + Constant.EMAIL_SUFFIX;

        basePage.open(Constant.URL.BASE_URL);
        openRegisterPageAndFillOutFields(username, email, password)
                .clickRegisterButton()
                .clickLogoutButton();
        String message = openRegisterPageAndFillOutFields(username, email, password)
                .clickRegisterButton()
                .getErrorMessage();

        Assert.assertEquals(message, RegisterMessage.REPEAT_REGISTER);
    }

    @Test
    public void missingPasswordRegister() {
        String username = StringGenerator.getUniqueString();
        String email = StringGenerator.getUniqueString() + Constant.EMAIL_SUFFIX;
        String password = RegisterData.EMPTY_PASSWORD;

        basePage.open(Constant.URL.BASE_URL);
        String message = openRegisterPageAndFillOutFields(username, email, password)
                .clickRegisterButton()
                .getErrorMessage();

        Assert.assertEquals(message, RegisterMessage.MISSING_PASSWORD_REGISTER);
    }

    private RegisterPage openRegisterPageAndFillOutFields(String username, String email, String password) {
        basePage.clickLoginButton();
        loginPage.clickRegisterButton();
        return registerPage.fillOutForm(username, email, password);
    }

}
