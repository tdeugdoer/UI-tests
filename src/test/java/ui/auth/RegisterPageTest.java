package ui.auth;

import data.RegisterData;
import messages.auth.RegisterMessage;
import messages.fail.FailMessage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.BasePage;
import pages.auth.LoginPage;
import pages.auth.RegisterPage;
import ui.BasePageTest;
import utils.Constant;
import utils.StringGenerator;

public class RegisterPageTest extends BasePageTest {
    private final BasePage basePage = new BasePage(driver);
    private final LoginPage loginPage = new LoginPage(driver);
    private final RegisterPage registerPage = new RegisterPage(driver);
    private String username;
    private String email;
    private String password;

    @BeforeMethod
    public void setUp() {
        username = StringGenerator.getUniqueString();
        email = StringGenerator.getUniqueString() + Constant.EMAIL_SUFFIX;
        password = StringGenerator.getUniqueString();
    }

    @Test
    public void successRegister() {
        basePage.open(Constant.Url.BASE_URL);
        String message = openRegisterPage()
                .fillOutForm(username, email, password)
                .clickRegisterButton()
                .getSuccessRegisterMessage();

        Assert.assertEquals(message, RegisterMessage.SUCCESS_REGISTER, FailMessage.MESSAGE_NOT_MATCH_EXPECTED);
    }

    @Test
    public void repeatRegister() {
        basePage.open(Constant.Url.BASE_URL);
        openRegisterPage()
                .fillOutForm(username, email, password)
                .clickRegisterButton()
                .clickLogoutButton();
        String message = openRegisterPage()
                .fillOutForm(username, email, password)
                .clickRegisterButton()
                .getErrorMessage();

        Assert.assertEquals(message, RegisterMessage.REPEAT_REGISTER, FailMessage.MESSAGE_NOT_MATCH_EXPECTED);
    }

    @Test
    public void missingPasswordRegister() {
        String password = RegisterData.EMPTY_PASSWORD;

        basePage.open(Constant.Url.BASE_URL);
        String message = openRegisterPage()
                .fillOutForm(username, email, password)
                .clickRegisterButton()
                .getErrorMessage();

        Assert.assertEquals(message, RegisterMessage.MISSING_PASSWORD_REGISTER, FailMessage.MESSAGE_NOT_MATCH_EXPECTED);
    }

    private RegisterPage openRegisterPage() {
        basePage.clickLoginButton();
        loginPage.clickRegisterButton();
        return registerPage;
    }

}
