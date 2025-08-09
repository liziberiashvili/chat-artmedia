package ge.chat.artmedia.pages;

import ge.chat.artmedia.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

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

    public void sendAMessageViaWidget(String name, String email, String text) {

        clickToElement(chatFrame);
        switchToIFrame(chatIframe);
        sendText(nameField, name);
        sendText(emailField, email);
        clickToElement(submitButton);
        clickToElement(talkToOperatorButton);
        clickToElement(messageInput);
        sendText(messageInput, text);
        clickToElement(sendButton);
    }

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

    public void userAcceptsChatEnding() {
        clickToElement(backToMainButton);
    }

    public void userSubmitsRating() {
        clickToElement(rateEmojiButton);
        clickToElement(rateSubmitButton);

    }

    public void verifyBlockedUserCannotStartChat(String name, String email) {
        sendText(nameField, name);
        sendText(emailField, email);
        clickToElement(submitButton);
        clickToElement(talkToOperatorButton);
        assertElementIsVisible(blockedModal);
    }

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
    public void typingIndicatorIsVisibleInWidget(){
        assertElementIsVisible(typingIndicator);
    }

    public void agentMessageIsVisible(){
        assertElementIsVisible(agentMessage);
    }

    public void uploadFile(){
        fileUploadWithFileName(fileUploadButton, "chatpic.jpg");
        clickToElement(sendButton);

    }
}

