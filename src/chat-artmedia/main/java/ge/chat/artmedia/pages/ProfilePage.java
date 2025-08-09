package ge.chat.artmedia.pages;

import ge.chat.artmedia.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProfilePage extends BasePage {
    public ProfilePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "settings")
    WebElement settingNavButton;

    @FindBy(id = "Profile")
    WebElement profileNavButton;

    @FindBy(id = "language_select")
    WebElement languageSelector;

    @FindBy(xpath = "//button[@type='submit']")
    WebElement saveButton;


    public WebElement getLanguageButton(String langCode) {
        return driver.findElement(By.xpath("//button[@data-value='" + langCode + "']"));
    }

    public String getCurrentLanguageCode(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement currentLanguageFlag = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//img[@id='label_Icon']")));
        String src = currentLanguageFlag.getDomAttribute("src");
        if (src.contains("ge.svg")){
            return "ka";
        } else if(src.contains("us.svg")){
            return "en";
        } else {
            return "unknown language";
        }
    }

    public WebElement getPageHeaderByText(String headerText) {
        String xpath = "//div[contains(text(),'" + headerText + "')]";
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
    }

    public void updatePlatformLanguage() {

        clickToElement(settingNavButton);
        clickToElement(profileNavButton);
        clickToElement(languageSelector);

        String targetLanguage;
        String expectedHeader;
        String currentLanguage = getCurrentLanguageCode();

        if (currentLanguage.equals("en")) {
            targetLanguage = "ka";
            expectedHeader = "ანგარიში";
        } else if (currentLanguage.equals("ka")) {
            targetLanguage = "en";
            expectedHeader = "Account";
        } else {
            throw new RuntimeException("Unknown language: " + currentLanguage);
        }

        clickToElement(getLanguageButton(targetLanguage));
        clickToElement(saveButton);
        assertElementIsVisible(getPageHeaderByText(expectedHeader));

    }


}



