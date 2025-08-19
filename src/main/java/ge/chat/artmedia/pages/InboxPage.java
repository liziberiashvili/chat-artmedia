package ge.chat.artmedia.pages;

import ge.chat.artmedia.BasePage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class InboxPage extends BasePage {
    public InboxPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "inbox_0")
    WebElement latestInbox;

    @FindBy(id = "profile_more_action")
    WebElement moreActionButton;

    @FindBy(id = "ban_user_action")
    WebElement banUserButton;

    @FindBy(id = "ban_user_submit")
    WebElement submitBtn;

    @FindBy(id = "ban_user_submit")
    WebElement submitButton;

    @FindBy(id = "logout_action")
    WebElement logoutButton;

    @FindBy(id = "profile_email")
    WebElement userEmail;

    @FindBy(id = "profile_name")
    WebElement userName;

    @FindBy(id = "typing_indicator")
    WebElement typingIndicator;

    @FindBy(id = "join_inbox_action")
    WebElement joinButton;

    @FindBy(id = "join_inbox_submit")
    WebElement inboxJoinSubmitButton;

    @FindBy(id = "chat_input_field")
    WebElement chatInputField;

    @FindBy(id = "chat_send_action")
    WebElement sendButton;

    @FindBy(id = "solve_inbox_action")
    WebElement solveButton;

    @FindBy(id = "solve_inbox_submit")
    WebElement inboxSolveSubmitButton;

    @FindBy(xpath = "//img[@alt='attachment']")
    WebElement attachment;

    @FindBy(id = "your inbox")
    WebElement yourInboxButton;

    @FindBy(id = "all inbox")
    WebElement allInboxButton;

    @FindBy(id = "unassigned")
    WebElement unassignedButton;

    @FindBy(id = "pending")
    WebElement pendingButton;

    @FindBy(id = "solved")
    WebElement solvedButton;

    @FindBy(css = "input[type='file']")
    WebElement fileUploadButton;

    @FindBy(xpath = "//img[@alt='Preview']")
    WebElement previewPoint;


    /**
     * Verifies that the latest inbox element is visible.
     */
    public void inboxIsCreated() {
        assertElementIsVisible(latestInbox);
    }

    /**
     * Blocks a user and confirms the ban.
     */
    public void blockUser() {

        clickToElement(moreActionButton);
        clickToElement(banUserButton);
        clickToElement(submitBtn);
        clickToElement(submitButton);

    }

    /**
     * Verifies that the dashboard is visible by checking the logout button.
     */
    public void dashboardIsVisible() {
        assertElementIsVisible(logoutButton);
    }


    /**
     * Retrieves the email of the user after confirming inbox is created.
     */
    public String getUserEmail() {
        inboxIsCreated();
        return getText(userEmail);
    }

    /**
     * Retrieves the displayed username.
     */
    public String getUserName() {
        return getText(userName);
    }

    /**
     * Verifies that the typing indicator is visible in the CRM chat.
     */
    public void typingIndicatorIsVisibleInCrm() {
        assertElementIsVisible(typingIndicator);
    }

    /**
     * Types a message in the CRM chat after joining the inbox.
     */
    public void typingAMessage(String message) {
        clickToElement(joinButton);
        clickToElement(inboxJoinSubmitButton);
        sendText(chatInputField, message);
    }

    /**
     * Sends a message in the CRM chat after joining the inbox.
     */
    public void sendAMessage(String message) {
        clickToElement(joinButton);
        clickToElement(inboxJoinSubmitButton);
        sendText(chatInputField, message);
        clickToElement(sendButton);
    }

    /**
     * Marks the inbox as solved and waits for redirect to the solved page.
     */
    public void solveInbox() {
        clickToElement(solveButton);
        clickToElement(inboxSolveSubmitButton);
        waitForUrlToContainAndReturn("/solved/");
    }

    /**
     * Verifies that the attachment element is visible in the chat.
     */
    public void attachmentIsVisible() {
        assertElementIsVisible(attachment);
    }

    /**
     * Verifies that each inbox navigation button redirects to the correct page.
     */
    public void verifyInboxNavButtonsRedirect() {
        clickToElement(yourInboxButton);
        waitForUrlToContainAndReturn("inbox");
        clickToElement(allInboxButton);
        waitForUrlToContainAndReturn("all-inbox");
        clickToElement(unassignedButton);
        waitForUrlToContainAndReturn("unassigned");
        clickToElement(pendingButton);
        waitForUrlToContainAndReturn("pending");
        clickToElement(solvedButton);
        waitForUrlToContainAndReturn("solved");
    }

    /**
     * Uploads a file in the chat and sends it.
     */
    public void uploadFile() {
        fileUploadWithFileName(fileUploadButton, "chatpic.webp");
        clickToElement(sendButton);
        assertElementIsVisible(previewPoint);

    }
}
