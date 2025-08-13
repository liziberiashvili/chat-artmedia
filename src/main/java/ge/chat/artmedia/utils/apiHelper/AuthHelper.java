package ge.chat.artmedia.utils.apiHelper;

import ge.chat.artmedia.utils.PropertiesReader;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class AuthHelper {
    public static String getAuthTokenForRole(String roleKey){
        String emailKey = "crm." + roleKey + ".email";
        String passwordKey = "crm." + roleKey + ".password";
        String email = PropertiesReader.readTestData(emailKey);
        String password = PropertiesReader.readTestData(passwordKey);
        Response response = RestAssured.given()
                .baseUri("http://109.172.158.104:3011/backend/")
                .contentType(ContentType.JSON)
                .body("{ \"email\": \"" + email + "\", \"password\": \"" + password + "\" }")
                .post("/auth/login");

        if (response.statusCode() != 201) {
            throw new RuntimeException("Login failed for role: " + roleKey + " | Status: " + response.statusCode());
        }
        return response.jsonPath().getString("data.accessToken");
    }

    public static void injectToken(WebDriver driver, String token) {
        driver.get("http://109.172.158.104:3011/login");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.localStorage.setItem('access_token', arguments[0]);", token);
        driver.navigate().refresh();
    }

    public static void authenticateUserViaApi(WebDriver driver, String roleKey){
        String token = getAuthTokenForRole(roleKey);
        injectToken(driver, token);
    }
}
