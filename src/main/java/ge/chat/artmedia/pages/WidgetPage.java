package ge.chat.artmedia.pages;

import ge.chat.artmedia.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;

import java.util.List;

public class WidgetPage extends BasePage {
    public WidgetPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }


    @FindBy(id = "artmedia-chat-frame-container")
    WebElement chatFrame;

    @FindBy(css = "iframe[title='Artmedia Chat Widget']")
    WebElement chatIframe;

    @FindBy(id = "name")
    WebElement nameField;

    @FindBy(id = "email")
    WebElement emailField;

    @FindBy(id = "submit")
    WebElement submitButton;

    @FindBy(id = "talk_to_operator")
    WebElement talkToOperatorButton;

    @FindBy(id = "message_input")
    WebElement messageInput;

    @FindBy(id = "send_message_action")
    WebElement sendButton;

    @FindBy(id = "2")
    WebElement rateEmojiButton;

    @FindBy(id = "rate_conversation")
    WebElement rateSubmitButton;

    @FindBy(xpath = "//h3[text()='You are blocked']")
    WebElement blockedModal;

    @FindBy(id = "back_to_main_action")
    WebElement backToMainButton;

    @FindBy(id = "customer_message")
    WebElement firstMessage;

    @FindBy(id = "typing_indicator")
    WebElement typingIndicator;

    @FindBy(id = "agent_message")
    WebElement agentMessage;

    @FindBy(id = "file_upload")
    WebElement fileUploadButton;

    @FindBy(xpath = "//img[@alt='attachment']")
    WebElement attachment;

    @FindBy(xpath = "//img[@alt='Preview']")
    WebElement previewPoint;


    /**
     * Sends a message via the chat widget, selecting a department if required.
     */
    public void sendAMessageViaWidget(String name, String email, String text) {

        clickToElement(chatFrame);
        switchToIFrame(chatIframe);
        sendText(nameField, name);
        sendText(emailField, email);
        clickToElement(submitButton);
        clickToElement(talkToOperatorButton);

        boolean isDisabled = !messageInput.isEnabled();
        if (isDisabled) {
            // Find all buttons inside answer_undefined
            List<WebElement> deptButtons = driver.findElements(
                    By.xpath("//*[@id='answer_undefined']//button")
            );

            // Check that there is at least a second button
            if (deptButtons.size() >= 2 && deptButtons.get(1).isDisplayed()) {
                wait.until(ExpectedConditions.elementToBeClickable(deptButtons.get(1))).click();
            }
        }
        clickToElement(messageInput);
        sendText(messageInput, text);
        clickToElement(sendButton);
    }

    /**
     * Types a message in the chat widget without sending it.
     */
    public void typingAMessage(String name, String email, String text) {

        clickToElement(chatFrame);
        switchToIFrame(chatIframe);
        sendText(nameField, name);
        sendText(emailField, email);
        clickToElement(submitButton);
        clickToElement(talkToOperatorButton);


        clickToElement(messageInput);
        sendText(messageInput, text);
    }


    /**
     * Confirms the end of the chat session.
     */
    public void userAcceptsChatEnding() {
        clickToElement(backToMainButton);
    }

    /**
     * Submits a rating after a chat session.
     */
    public void userSubmitsRating() {
        clickToElement(rateEmojiButton);
        clickToElement(rateSubmitButton);

    }

    /**
     * Verifies that a blocked user cannot start a chat.
     */
    public void verifyBlockedUserCannotStartChat(String name, String email) {
        sendText(nameField, name);
        sendText(emailField, email);
        clickToElement(submitButton);
        clickToElement(talkToOperatorButton);
        assertElementIsVisible(blockedModal);
    }

    /**
     * Verifies that an unblocked user can start a chat and send a message.
     */
    public void verifyUnblockedUserCanStartChat(String name, String email, String text) {
        sendText(nameField, name);
        sendText(emailField, email);
        clickToElement(submitButton);
        clickToElement(talkToOperatorButton);
        clickToElement(messageInput);
        sendText(messageInput, text);
        clickToElement(sendButton);
        assertElementIsVisible(firstMessage);
        String actualText = getText(firstMessage);
        assertEquals(actualText, text);
    }

    /**
     * Verifies that the typing indicator is visible in the chat widget.
     */
    public void typingIndicatorIsVisibleInWidget() {
        assertElementIsVisible(typingIndicator);
    }

    /**
     * Verifies that an agent's message is visible in the chat widget.
     */
    public void agentMessageIsVisible() {
        assertElementIsVisible(agentMessage);
    }

    /**
     * Uploads a file via the chat widget and sends it.
     */
    public void uploadFile() {
        fileUploadWithFileName(fileUploadButton, "chatpic.webp");
        assertElementIsVisible(previewPoint);
        clickToElement(sendButton);

    }

    /**
     * Verifies that the uploaded file attachment is visible in the chat.
     */
    public void attachmentIsVisible() {
        assertElementIsVisible(attachment);
    }
}

