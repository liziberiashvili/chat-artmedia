package ge.chat.artmedia.pages;

import ge.chat.artmedia.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ge.chat.artmedia.utils.PropertiesReader;

import java.util.Properties;

public class LoginPage extends BasePage {
    public LoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "name")
    WebElement emailField;

    @FindBy(id = "password")
    WebElement passwordField;

    @FindBy(id = "submit")
    WebElement loginButton;

    @FindBy(id = "login-error-toast")
    WebElement errorToast;

    public void login(String email, String password) {

        sendText(emailField, email);
        sendText(passwordField, password);
        clickToElement(loginButton);
    }

    public void errorToastIsVisible(){
        assertElementIsVisible(errorToast);
    }


}

