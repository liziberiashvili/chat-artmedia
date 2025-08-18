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


    /**
     * Changes the password using valid old and new credentials, verifies success message, and logs the user out.
     */
    public void changePasswordWithValidCredentials(String oldPassword, String newPassword) {
        clickToElement(settingsNavButton);
        clickToElement(passwordChangeNavButton);
        sendText(oldPasswordField, oldPassword);
        sendText(newPasswordField, newPassword);
        clickToElement(submitButton);
        assertElementIsVisible(successMessage);
        clickToElement(logoutButton);
        clickToElement(logoutSubmitButton);
    }

    /**
     * Attempts to change the password with an incorrect old password and verifies the error message is displayed.
     */
    public void changePasswordWithInvalidOldPassword(String oldPassword, String newPassword) {
        clickToElement(settingsNavButton);
        clickToElement(passwordChangeNavButton);
        sendText(oldPasswordField, oldPassword);
        sendText(newPasswordField, newPassword);
        clickToElement(submitButton);
        assertElementIsVisible(errorMessage);
    }

    /**
     * Attempts to change the password with empty fields and verifies field validation messages are displayed.
     */
    public void changePasswordWithEmptyFields() {
        clickToElement(settingsNavButton);
        clickToElement(passwordChangeNavButton);
        clickToElement(submitButton);
        assertElementIsVisible(firstFieldValidationMsg);
        assertElementIsVisible(secondFieldValidationMsg);
    }

    /**
     * Attempts to change the password with an invalid new password format and verifies the error message is displayed.
     */
    public void changePasswordWithInvalidNewPassword(String oldPassword, String newPassword) {
        clickToElement(settingsNavButton);
        clickToElement(passwordChangeNavButton);
        sendText(oldPasswordField, oldPassword);
        sendText(newPasswordField, newPassword);
        clickToElement(submitButton);
        assertElementIsVisible(invalidFormatMessage);
    }
}
