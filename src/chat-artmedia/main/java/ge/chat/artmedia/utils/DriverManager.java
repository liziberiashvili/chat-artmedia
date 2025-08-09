package ge.chat.artmedia.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverManager {
    private static WebDriver chatDriver;
    private static WebDriver crmDriver;

    public static WebDriver getChatDriver() {
        if (chatDriver == null) {
            WebDriverManager.chromedriver().setup();
            chatDriver = new ChromeDriver();
        }
        return chatDriver;
    }

    public static WebDriver getCrmDriver() {
        if (crmDriver == null) {
            WebDriverManager.chromedriver().setup();
            crmDriver = new ChromeDriver();
        }
        return crmDriver;
    }

    public static void quitCrmDriver() {

        if (crmDriver != null) {
            crmDriver.quit();
            crmDriver = null;
        }
    }

    public static void quitChatDriver(){
        if (chatDriver != null) {
            chatDriver.quit();
            chatDriver = null;
        }
    }
}
