package ge.chat.artmedia.ui;

import ge.chat.artmedia.setup.BaseTest;
import ge.chat.artmedia.pages.AgentAccountSetupPage;
import ge.chat.artmedia.pages.InboxPage;
import ge.chat.artmedia.pages.LoginPage;
import ge.chat.artmedia.setup.SuiteSetup;
import ge.chat.artmedia.utils.TestDataFactory;


import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class InviteTests extends BaseTest {

    {
        useChat = false;
        useCrm = true;
    }

    @BeforeMethod
    public void setupAgent() {
        SuiteSetup suiteSetup = new SuiteSetup();
        suiteSetup.ensureAgentExists();
    }

    @Test
    public void agentRegistrationFlow() {
        crmDriver.get(SuiteSetup.verificationLink);

        AgentAccountSetupPage agentAccountSetupPage = new AgentAccountSetupPage(crmDriver);
        agentAccountSetupPage.fillAgentRegistrationFields(TestDataFactory.randomName(), SuiteSetup.agentPassword);

        LoginPage loginPage = new LoginPage(crmDriver);
        loginPage.login(SuiteSetup.agentEmail, SuiteSetup.agentPassword);

        InboxPage inboxPage = new InboxPage(crmDriver);
        inboxPage.dashboardIsVisible();

    }
}
