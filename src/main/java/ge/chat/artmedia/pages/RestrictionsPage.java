package ge.chat.artmedia.pages;

import ge.chat.artmedia.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RestrictionsPage extends BasePage {
    public RestrictionsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "contacts")
    WebElement usersNavButton;

    @FindBy(id = "restrictions")
    WebElement restrictionsButton;

    @FindBy(name = "search")
    WebElement searchInput;

    @FindBy(id = "unblock")
    WebElement unblockButton;

    @FindBy(id = "delete_restriction_submit")
    WebElement yesOptionButton;

    @FindBy(id = "dashboard")
    WebElement dashboardNavButton;


    /**
     * Unblocks a user by email through the restrictions panel and returns to the dashboard.
     */
    public void unblockUser(String email){
        clickToElement(usersNavButton);
        clickToElement(restrictionsButton);
        sendText(searchInput, email);
        clickToElement(unblockButton);
        clickToElement(yesOptionButton);
        clickToElement(dashboardNavButton);

    }
}
