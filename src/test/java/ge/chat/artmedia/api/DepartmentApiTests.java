package ge.chat.artmedia.api;

import ge.chat.artmedia.setup.SuiteSetup;
import ge.chat.artmedia.utils.JsonDataReader;
import ge.chat.artmedia.utils.PropertiesReader;
import ge.chat.artmedia.utils.apiHelper.AuthHelper;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.equalTo;

public class DepartmentApiTests {
    private static String departmentId;
    private static String token;

    @BeforeMethod
    public void setupAgent() {
        SuiteSetup suiteSetup = new SuiteSetup();
        suiteSetup.ensureAgentExists();
    }

    @Test
    public void addDepartment(){
        RestAssured.baseURI = PropertiesReader.readConfig("base.URI");
        token = AuthHelper.getAuthTokenForRole("admin");
        String userId = SuiteSetup.userId;
        String template = JsonDataReader.get("createDepartment").toString();
        String body = template.replace("{userId}", userId);

        Response response = RestAssured
                .given()
                .header("Authorization", "Bearer " + token)
                .contentType(JSON)
                .body(body)
                .when()
                .post("department")
                .then()
                .assertThat()
                .log().body()
                .statusCode(201)
                .body("success", equalTo(true))
                .extract().response();
        departmentId = response.jsonPath().getString("data.id");
    }
 @AfterSuite
 public void deleteDepartment(){
     RestAssured.baseURI = PropertiesReader.readConfig("base.URI");
     if (departmentId != null) {
         RestAssured
                 .given()
                 .pathParam("id", departmentId)
                 .header("Authorization", "Bearer " + token)
                 .delete("department/{id}")
                 .then()
                 .statusCode(200);
     }
 }
}
