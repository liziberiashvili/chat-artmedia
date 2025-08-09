package ge.chat.artmedia.tests.ui;

import ge.chat.artmedia.BaseTest;
import ge.chat.artmedia.pages.InboxPage;
import ge.chat.artmedia.pages.LoginPage;
import ge.chat.artmedia.pages.PasswordChangePage;
import ge.chat.artmedia.pages.ProfilePage;
import ge.chat.artmedia.utils.apiHelper.AuthHelper;
import ge.chat.artmedia.utils.PropertiesReader;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CrmTests extends BaseTest {
    {
        useChat = false;
        useCrm = true;
    }

    @Test
    public void updatePasswordWithValidCredentials(){
        AuthHelper.authenticateUserViaApi(crmDriver, "admin");
        PasswordChangePage passwordChangePage = new PasswordChangePage(crmDriver);

        String password = PropertiesReader.readTestData("crm.admin.password");
        passwordChangePage.changePasswordWithValidCredentials(password, password);
        LoginPage loginPage = new LoginPage(crmDriver);
        String email = PropertiesReader.readTestData("crm.admin.email");
        loginPage.login(email, password);
    }

    @Test
    public void updatePasswordWithInvalidOldPassword(){
        AuthHelper.authenticateUserViaApi(crmDriver, "admin");
        PasswordChangePage passwordChangePage = new PasswordChangePage(crmDriver);

        String wrongPassword = PropertiesReader.readTestData("crm.admin.wrong.password");
        String correctPassword = PropertiesReader.readTestData("crm.admin.password");
        passwordChangePage.changePasswordWithInvalidOldPassword(wrongPassword, correctPassword);
    }

    @Test
    public void updatePasswordWithEmptyValues(){
        AuthHelper.authenticateUserViaApi(crmDriver, "admin");
        PasswordChangePage passwordChangePage = new PasswordChangePage(crmDriver);
        passwordChangePage.changePasswordWithEmptyFields();
    }

    @Test
    public void updatePasswordWithInvalidNewPassword(){
        AuthHelper.authenticateUserViaApi(crmDriver, "admin");
        PasswordChangePage passwordChangePage = new PasswordChangePage(crmDriver);

        String wrongPassword = PropertiesReader.readTestData("crm.admin.wrong.password");
        String correctPassword = PropertiesReader.readTestData("crm.admin.password");
        passwordChangePage.changePasswordWithInvalidNewPassword(correctPassword, wrongPassword);
    }

    @Test
    public void updateCrmLanguage(){
        AuthHelper.authenticateUserViaApi(crmDriver, "admin");
        ProfilePage profilePage = new ProfilePage(crmDriver);

        profilePage.updatePlatformLanguage();
    }

    @Test
    public void loginWithValidCredentials() {
        LoginPage loginPage = new LoginPage(crmDriver);
        String email = PropertiesReader.readTestData("crm.admin.email");
        String password = PropertiesReader.readTestData("crm.admin.password");
        loginPage.login(email, password);
        InboxPage inboxPage = new InboxPage(crmDriver);
        inboxPage.dashboardIsVisible();
    }

    @Test
    public void loginWithInvalidEmail(){
        LoginPage loginPage = new LoginPage(crmDriver);
        String email = PropertiesReader.readTestData("crm.admin.wrong.email");
        String password = PropertiesReader.readTestData("crm.admin.password");
        loginPage.login(email, password);
        String actualUrl = crmDriver.getCurrentUrl();
        String expectedUrl = "http://109.172.158.104:3011/login";
        Assert.assertEquals(actualUrl, expectedUrl);
        loginPage.errorToastIsVisible();

    }

    @Test
    public void loginWithInvalidPassword(){
        LoginPage loginPage = new LoginPage(crmDriver);
        String email = PropertiesReader.readTestData("crm.admin.email");
        String password = PropertiesReader.readTestData("crm.admin.wrong.password");
        loginPage.login(email, password);
        String actualUrl = crmDriver.getCurrentUrl();
        String expectedUrl = "http://109.172.158.104:3011/login";
        Assert.assertEquals(actualUrl, expectedUrl);
        loginPage.errorToastIsVisible();

    }

    @Test
    public void validateInboxNavigationLinks(){
        AuthHelper.authenticateUserViaApi(crmDriver, "admin");
        InboxPage inboxPage = new InboxPage(crmDriver);
        inboxPage.verifyInboxNavButtonsRedirect();
    }



}
