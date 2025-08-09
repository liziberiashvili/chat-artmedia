package ge.chat.artmedia.tests.ui;

import ge.chat.artmedia.BaseTest;
import ge.chat.artmedia.pages.AgentAccountSetupPage;
import ge.chat.artmedia.pages.InboxPage;
import ge.chat.artmedia.pages.LoginPage;
import ge.chat.artmedia.utils.apiHelper.AuthHelper;
import ge.chat.artmedia.utils.apiHelper.InviteHelper;
import ge.chat.artmedia.utils.PropertiesReader;
import ge.chat.artmedia.utils.TestDataFactory;


import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class InviteTests extends BaseTest {
    private String userId;
    {
        useChat = false;
        useCrm = true;
    }

    @Test
    public void agentRegistrationFlow() {
        InviteHelper inviteHelper = new InviteHelper();
        inviteHelper.inviteAgent();
        String verificationLink = inviteHelper.getVerificationLink();

        String agentEmail = inviteHelper.getGeneratedEmail();

        String agentName = TestDataFactory.randomName();;
        String agentPassword = PropertiesReader.readTestData("crm.agent.password");
        userId = inviteHelper.getUserId();

        crmDriver.get(verificationLink);

        AgentAccountSetupPage agentAccountSetupPage = new AgentAccountSetupPage(crmDriver);
        agentAccountSetupPage.fillAgentRegistrationFields(agentName, agentPassword);

        LoginPage loginPage = new LoginPage(crmDriver);
        loginPage.login(agentEmail, agentPassword);

        InboxPage inboxPage = new InboxPage(crmDriver);
        inboxPage.dashboardIsVisible();

    }

    @AfterMethod
    public void deleteAgent(){
        String token = AuthHelper.getAuthTokenForRole("admin");
        InviteHelper.deleteUserById(userId, token);
    }
}
