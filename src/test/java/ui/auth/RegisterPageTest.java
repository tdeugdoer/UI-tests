package ui.auth;

import data.RegisterData;
import messages.auth.RegisterExpectedResult;
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

        basePage.open(Constant.Url.BASE_URL);
    }

    @Test
    public void successRegister() {
        String message = openRegisterPage()
                .fillOutForm(username, email, password)
                .clickRegisterButton()
                .getSuccessRegisterMessage();

        Assert.assertEquals(message, RegisterExpectedResult.SUCCESS_REGISTER, FailMessage.MESSAGE_NOT_MATCH_EXPECTED);
    }

    @Test
    public void repeatRegister() {
        openRegisterPage()
                .fillOutForm(username, email, password)
                .clickRegisterButton()
                .clickLogoutButton();
        String message = openRegisterPage()
                .fillOutForm(username, email, password)
                .clickRegisterButton()
                .getErrorMessage();

        Assert.assertEquals(message, RegisterExpectedResult.REPEAT_REGISTER, FailMessage.MESSAGE_NOT_MATCH_EXPECTED);
    }

    @Test
    public void missingPasswordRegister() {
        String password = RegisterData.EMPTY_PASSWORD;

        String message = openRegisterPage()
                .fillOutForm(username, email, password)
                .clickRegisterButton()
                .getErrorMessage();

        Assert.assertEquals(message, RegisterExpectedResult.MISSING_PASSWORD_REGISTER, FailMessage.MESSAGE_NOT_MATCH_EXPECTED);
    }

    private RegisterPage openRegisterPage() {
        basePage.clickLoginButton();
        loginPage.clickRegisterButton();
        return registerPage;
    }

}
