package ge.chat.artmedia;
import ge.chat.artmedia.utils.Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.Duration;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void waitForElementToBeVisible(WebElement locator) {
        wait.until(ExpectedConditions.visibilityOf(locator));
        Utils.logInfo("Waiting for element: " + locator);
    }

    public void waitForElementToBeClickable(WebElement locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator));
        Utils.logInfo("Waiting for element: " + locator + "to be clickable");
    }

    public void sendText(WebElement locator, String text) {
        waitForElementToBeVisible(locator);
        locator.sendKeys(text);
        Utils.logInfo("Sending text: [ " + text + " ] to an element: " + locator);
    }

    public void clearText(WebElement locator) {
        waitForElementToBeVisible(locator);
        locator.clear();
        Utils.logInfo(("Clearing text from an element: " + locator));
    }


    public void clickToElement(WebElement locator) {
        waitForElementToBeClickable(locator);
        locator.click();
        Utils.logInfo("Clicking on element: " + locator);
    }

    public void assertEquals(String actualValue, String expectedValue) {
        Assert.assertEquals(actualValue, expectedValue);
    }

    public void assertEquals(String actualValue, String expectedValue, String message) {
        Assert.assertEquals(actualValue, expectedValue, message);
    }

    public void switchToIFrame(WebElement locator){
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(locator));
        Utils.logInfo("Switching to an Iframe: " + locator);
    }

    public void assertElementIsVisible(WebElement locator){
        waitForElementToBeVisible(locator);
        Assert.assertTrue(locator.isDisplayed());
    }

    public void fileUploadWithRelativePath(WebElement locator, String relativePath){
        File file = new File(relativePath);
        if (!file.exists()) {
            throw new RuntimeException("File not found at path: " + relativePath);
        }
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].style.display='block'; arguments[0].style.visibility='visible';",
                locator
        );

        locator.sendKeys(file.getAbsolutePath());
        Utils.logInfo("Uploading file from: "  + relativePath);
    }

    public void fileUploadWithFileName(WebElement locator, String fileName) {
        URL resource = getClass().getClassLoader().getResource(fileName);
        if (resource == null) {
            throw new RuntimeException("File not found in resources: " + fileName);
        }

        File file;
        try {
            file = new File(resource.toURI());
        } catch (URISyntaxException e) {
            throw new RuntimeException("Invalid file URI for: " + fileName, e);
        }

        if (!file.exists()) {
            throw new RuntimeException("File does not exist: " + file.getAbsolutePath());
        }
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].style.display='block'; arguments[0].style.visibility='visible';",
                locator
        );
        locator.sendKeys(file.getAbsolutePath());
        Utils.logInfo("Uploading file: "  + fileName);
    }



    public String getText(WebElement locator){
        waitForElementToBeVisible(locator);
        return locator.getText();
    }

    public String waitForUrlToContainAndReturn(String fraction){
        Assert.assertTrue(
            wait.until(ExpectedConditions.urlContains(fraction)));
        Utils.logInfo("Expected current URL to contain: " + fraction + " but was: " + driver.getCurrentUrl());
        return driver.getCurrentUrl();
    }


}
