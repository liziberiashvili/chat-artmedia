package ge.chat.artmedia.pages;

import ge.chat.artmedia.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PasswordChangePage extends BasePage {
    public PasswordChangePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }
    @FindBy(id = "settings")
    WebElement settingsNavButton;

    @FindBy(id = "Password change")
    WebElement passwordChangeNavButton;

    @FindBy(name = "oldPassword")
    WebElement oldPasswordField;

    @FindBy(name = "newPassword")
    WebElement newPasswordField;

    @FindBy(xpath = "//button[@type='submit']")
    WebElement submitButton;

    @FindBy(xpath = "//div[contains(text(),'Updated successfully!')]")
    WebElement successMessage;

    @FindBy(id = "logout_action")
    WebElement logoutButton;

    @FindBy(id = "logout_submit")
    WebElement logoutSubmitButton;

    @FindBy(xpath = "//div[contains(text(),'old password is invalid')]")
    WebElement errorMessage;

    @FindBy(id = "error_oldPassword")
    WebElement firstFieldValidationMsg;

    @FindBy(id = "error_newPassword")
    WebElement secondFieldValidationMsg;

    @FindBy(id = "error_newPassword")
    WebElement invalidFormatMessage;



    public void changePasswordWithValidCredentials(String oldPassword, String newPassword){
        clickToElement(settingsNavButton);
        clickToElement(passwordChangeNavButton);
        sendText(oldPasswordField, oldPassword);
        sendText(newPasswordField, newPassword);
        clickToElement(submitButton);
        assertElementIsVisible(successMessage);
        clickToElement(logoutButton);
        clickToElement(logoutSubmitButton);
    }

    public void changePasswordWithInvalidOldPassword(String oldPassword, String newPassword){
        clickToElement(settingsNavButton);
        clickToElement(passwordChangeNavButton);
        sendText(oldPasswordField, oldPassword);
        sendText(newPasswordField, newPassword);
        clickToElement(submitButton);
        assertElementIsVisible(errorMessage);
    }

    public void changePasswordWithEmptyFields(){
        clickToElement(settingsNavButton);
        clickToElement(passwordChangeNavButton);
        clickToElement(submitButton);
        assertElementIsVisible(firstFieldValidationMsg);
        assertElementIsVisible(secondFieldValidationMsg);
    }

    public void changePasswordWithInvalidNewPassword(String oldPassword, String newPassword) {
        clickToElement(settingsNavButton);
        clickToElement(passwordChangeNavButton);
        sendText(oldPasswordField, oldPassword);
        sendText(newPasswordField, newPassword);
        clickToElement(submitButton);
        assertElementIsVisible(invalidFormatMessage);
    }
}
