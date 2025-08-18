package ge.chat.artmedia.pages;

import ge.chat.artmedia.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AgentAccountSetupPage extends BasePage {
    public AgentAccountSetupPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(name = "name")
    WebElement nameField;

    @FindBy(name = "password")
    WebElement passwordField;

    @FindBy(xpath = "//label[input[@name='terms']]")
    WebElement termsCheckbox;

    @FindBy(xpath = "//button[@type='submit']")
    WebElement signUpButton;

    /**
     * Fills agent registration form with given credentials, submits it, and verifies redirect to login page.
     */
    public void fillAgentRegistrationFields(String email, String password) {
        sendText(nameField, email);
        sendText(passwordField, password);
        clickToElement(termsCheckbox);
        clickToElement(signUpButton);

        String actualUrl = waitForUrlToContainAndReturn("/login");
        String expectedUrl = "http://109.172.158.104:3011/login";
        assertEquals(actualUrl, expectedUrl);
    }
}
