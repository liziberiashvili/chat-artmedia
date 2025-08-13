package ge.chat.artmedia.api;

import ge.chat.artmedia.utils.JsonDataReader;

import ge.chat.artmedia.utils.PropertiesReader;
import ge.chat.artmedia.utils.apiHelper.AuthHelper;
import io.restassured.RestAssured;

import io.restassured.response.Response;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.*;

public class FaqApiTests {
    private String faqId;
    private String token;

    @BeforeMethod
    public void createFAQ() {
        RestAssured.baseURI = PropertiesReader.readConfig("base.URI");
        token = AuthHelper.getAuthTokenForRole("admin");
        String body = JsonDataReader.get("createFaq").toString();

        Response response = RestAssured
                .given()
                .header("Authorization", "Bearer " + token)
                .contentType(JSON)
                .body(body)
                .when()
                .post("faq")
                .then()
                .assertThat()
                .statusCode(201)
                .extract().response();
        faqId = response.jsonPath().getString("data.id");

    }

    @Test
    public void updateFAQ() {
        RestAssured.baseURI = PropertiesReader.readConfig("base.URI");
        String template = JsonDataReader.get("updateFaq").toString();
        String body = template.replace("{faqId}", faqId);
        Response response = RestAssured
                .given()
                .header("Authorization", "Bearer " + token)
                .contentType(JSON)
                .body(body)
                .when()
                .put("faq")
                .then()
                .assertThat()
                .statusCode(200)
                .body("success", equalTo(true))
                .extract().response();

    }

    @Test
    public void getFAQ() {
        RestAssured.baseURI = PropertiesReader.readConfig("base.URI");
        Response response = RestAssured
                .given()
                .header("Authorization", "Bearer " + token)
                .pathParam("id", faqId)
                .when()
                .get("faq/{id}")
                .then()
                .assertThat()
                .statusCode(200)
                .body("success", equalTo(true))
                .extract().response();

    }

    @AfterMethod
    public void deleteFAQ() {
        RestAssured.baseURI = PropertiesReader.readConfig("base.URI");
        Response response = RestAssured
                .given()
                .header("Authorization", "Bearer " + token)
                .pathParam("id", faqId)
                .when()
                .delete("faq/{id}")
                .then()
                .assertThat()
                .statusCode(200)
                .body("success", equalTo(true))
                .extract().response();
    }

}

