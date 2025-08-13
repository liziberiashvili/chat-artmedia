package ge.chat.artmedia.setup;

import ge.chat.artmedia.utils.PropertiesReader;
import ge.chat.artmedia.utils.Utils;
import ge.chat.artmedia.utils.apiHelper.AuthHelper;
import ge.chat.artmedia.utils.apiHelper.InviteHelper;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

public class SuiteSetup {
    public static String userId;
    public static String agentEmail;
    public static String agentPassword;
    public static String verificationLink;

    private static boolean agentCreated = false;

    @BeforeSuite
    public void createAgent() {
        InviteHelper inviteHelper = new InviteHelper();
        inviteHelper.inviteAgent();

        userId = inviteHelper.getUserId();
        agentEmail = inviteHelper.getGeneratedEmail();
        agentPassword = PropertiesReader.readTestData("crm.agent.password");
        verificationLink = inviteHelper.getVerificationLink();
        agentCreated = true;
        Utils.logInfo("Create test agent with userId: " + userId);
    }

    @BeforeMethod
    public void ensureAgentExists() {
        if (!agentCreated) {
            InviteHelper inviteHelper = new InviteHelper();
            inviteHelper.inviteAgent();


            userId = inviteHelper.getUserId();
            agentEmail = inviteHelper.getGeneratedEmail();
            agentPassword = PropertiesReader.readTestData("crm.agent.password");
            verificationLink = inviteHelper.getVerificationLink();
            System.out.println(userId);
            System.out.println(agentEmail);
            System.out.println(verificationLink);

            agentCreated = true;
            Utils.logInfo("Create test agent (via ensureAgentExists) with userId: " + userId);
            System.out.println(agentCreated);

        }
    }

    @AfterSuite
    public void deleteAgent() {
        if (userId != null) {
            String token = AuthHelper.getAuthTokenForRole("admin");
            InviteHelper.deleteUserById(userId, token);
            Utils.logInfo("Delete test agent with userId: " + userId);
        }
    }

}