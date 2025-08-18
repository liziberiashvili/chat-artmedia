package ge.chat.artmedia.setup;

import ge.chat.artmedia.utils.DriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {
    protected WebDriver chatDriver;
    protected WebDriver crmDriver;

    protected boolean useChat = false;
    protected boolean useCrm = false;

    /**
     * Initializes WebDriver instances for CRM and/or chat before each test.
     */
    @BeforeMethod
    public void setup() {
        if (useCrm) {
            crmDriver = DriverManager.getCrmDriver();
            crmDriver.manage().window().maximize();
            crmDriver.get("http://109.172.158.104:3011/login");
        }
        if (useChat) {
            chatDriver = DriverManager.getChatDriver();

            chatDriver.get("http://109.172.158.104:3011/widget-api/widget-test.html");
        }
    }

    /**
     * Quits all active WebDriver instances after each test.
     */
    @AfterMethod
    public void tearDown() {
        DriverManager.quitCrmDriver();
        DriverManager.quitChatDriver();

    }
}
