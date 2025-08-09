package ge.chat.artmedia.pages;

import ge.chat.artmedia.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FaqPage extends BasePage {
    public FaqPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "widget")
    WebElement widgetNavButton;

    @FindBy(id = "faq")
    WebElement FAQNavButton;

    @FindBy(id = "add_faq")
    WebElement addFAQButton;

    @FindBy(id = "question")
    WebElement questionField;

    @FindBy(id = "answer")
    WebElement answerField;

    @FindBy(id = "submit_form")
    WebElement createButton;

    public void addFAQ(String question, String answer){
        clickToElement(widgetNavButton);
        clickToElement(FAQNavButton);
        clickToElement(addFAQButton);
        sendText(questionField, question);
        sendText(answerField, answer);
        clickToElement(createButton);
    }

}
