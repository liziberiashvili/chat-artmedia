package ge.chat.artmedia.api;

import ge.chat.artmedia.utils.PropertiesReader;
import ge.chat.artmedia.utils.Utils;
import ge.chat.artmedia.utils.apiHelper.AuthHelper;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.greaterThanOrEqualTo;

public class DashboardApiTests {
    @Test
    public void getDashboard(){
        RestAssured.baseURI = PropertiesReader.readConfig("base.URI");
        String token = AuthHelper.getAuthTokenForRole("admin");
        Response response = RestAssured
                .given()
                .header("Authorization", "Bearer " + token)
                .when()
                .get("dashboard")
                .then()
                .assertThat()
                .statusCode(200)
                .log().body()
                .body("onlineUsers", greaterThanOrEqualTo(0))
                .body("offlineUsers", greaterThanOrEqualTo(0))
                .body("unassignedChats", greaterThanOrEqualTo(0))
                .body("pendingChats", greaterThanOrEqualTo(0))
                .body("ongoingChats", greaterThanOrEqualTo(0))
                .body("unansweredFor5Minutes", greaterThanOrEqualTo(0))
                .extract().response();
        Utils.logInfo("Status code: " + response.statusCode());
        Utils.logInfo("Response body: " + response.asString());
    }
}
