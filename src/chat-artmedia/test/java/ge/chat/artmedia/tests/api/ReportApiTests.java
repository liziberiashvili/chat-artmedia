package ge.chat.artmedia.tests.api;

import ge.chat.artmedia.utils.PropertiesReader;
import ge.chat.artmedia.utils.Utils;
import ge.chat.artmedia.utils.apiHelper.AuthHelper;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.*;

public class ReportApiTests {
    private String token;

    @Test
    public void getGeneralReport() {
        RestAssured.baseURI = PropertiesReader.readConfig("base.URI");
        token = AuthHelper.getAuthTokenForRole("admin");
        Response response = RestAssured
                .given()
                .header("Authorization", "Bearer " + token)
                .when()
                .get("/report/general")
                .then()
                .assertThat()
                .statusCode(200)
                .log().body()
                .body("totalSolved", notNullValue())
                .body("totalSolved", greaterThanOrEqualTo(0))
                .body("inboxByDate", notNullValue())
                .body("inboxByDate.month", notNullValue())  // check current month exists
                .body("ratingStat.averageRating", allOf(notNullValue(), greaterThanOrEqualTo(0f), lessThanOrEqualTo(5f)))
                .extract().response();
        Utils.logInfo("Status code: " + response.statusCode());
        Utils.logInfo("Response body: " + response.asString());
    }
    @Test
    public void getInboxReports(){
        RestAssured.baseURI = PropertiesReader.readConfig("base.URI");
        token = AuthHelper.getAuthTokenForRole("admin");
        Response response = RestAssured
                .given()
                .queryParam("language", "en")
                .header("Authorization", "Bearer " + token)
                .when()
                .get("report/inboxes")
                .then()
                .assertThat()
                .statusCode(200)
                .log().body()
                .body("total", notNullValue())
                .body("total", greaterThanOrEqualTo(0))
                .body("data", notNullValue())
                .extract().response();
        Utils.logInfo("Status code: " + response.statusCode());
        Utils.logInfo("Response body: " + response.asString());
    }
}
