package ge.chat.artmedia.utils.apiHelper;

import ge.chat.artmedia.utils.PropertiesReader;
import ge.chat.artmedia.utils.TestDataFactory;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.util.List;
import java.util.Map;

import static io.restassured.http.ContentType.JSON;

public class InviteHelper {
    private String verificationLink;
    private String generatedEmail;
    private String userId;

    public void inviteAgent(){
        RestAssured.baseURI = PropertiesReader.readConfig("base.URI");

        String token = AuthHelper.getAuthTokenForRole("admin");

        generatedEmail = TestDataFactory.randomEmail();
        String requestBody = String.format("""
                {
                  "email": "%s",
                  "role": "AGENT"
                }
                """, generatedEmail);

        Response response = RestAssured
                .given()
                .header("Authorization", "Bearer " + token)
                .contentType(JSON)
                .body(requestBody)
                .when()
                .post("users/invite")
                .then()
                .statusCode(201)
                .extract().response();

        verificationLink = response.jsonPath().getString("data.link");
        userId = getUserByEmail(generatedEmail, token);
    }

    public String getVerificationLink(){
        return verificationLink;
    }

    public String getGeneratedEmail() {
        return generatedEmail;
    }
    public String getUserId(){
        return userId;
    }

    public static String getUserByEmail(String email, String token){
        RestAssured.baseURI = PropertiesReader.readConfig("base.URI");;

        Response response = RestAssured
                .given()
                .header("Authorization", "Bearer " + token)
                .get("users")
                .then()
                .statusCode(200)
                .extract().response();

        List<Map<String, Object>> users = response.jsonPath().getList("data");
        for (Map<String, Object> user : users) {
            if (email.equals(user.get("email"))) {
                return (String) user.get("id");
            }
        }
        return null;
    }

    public static void deleteUserById(String userId, String token){
        RestAssured.baseURI = PropertiesReader.readConfig("base.URI");
        if (userId != null) {
            RestAssured
                    .given()
                    .header("Authorization", "Bearer " + token)
                    .delete("users/" + userId)
                    .then()
                    .statusCode(200);
        }
    }
}
