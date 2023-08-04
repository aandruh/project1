package utils;


import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static io.restassured.http.ContentType.JSON;
import static utils.RestAssuredLocalConfigs.REST_ASSURED_TIMEOUT_CONFIG;
import static utils.RestAssuredLocalConfigs.REST_ASSURED_sslRelaxedHTTPSValidation_CONFIG;

public class Query {
    public static String getQuantityAvailable(String token, String productId ) {

        Response messagesResponse;

        messagesResponse = RestAssured.with().config(REST_ASSURED_TIMEOUT_CONFIG).config(REST_ASSURED_sslRelaxedHTTPSValidation_CONFIG)
                .given().log().all()
                .given().header("Content-Type", "application/json")
                .given().header("authorization", "Bearer " + token)
                .contentType(JSON.withCharset("UTF-8"))
                .body("{\n" +
                        "  \"query\": \"{alcoholProduct(id:\\"+"\"" + productId + "\\" + "\"){stock{quantityAvailable}}}\",\n" +
                        "  \"variables\": {},\n" +
                        "  \"operationName\": null\n" +
                        "}")


                .when()
                .post("https://app.staging-bemakers.com/api/graphql")//;

                .then().log().all()
                .contentType(JSON)
                .statusCode(200)
                .extract().response();
        String jsAsString = messagesResponse.body().asString();
        JsonPath jsonPath = new JsonPath(jsAsString);
        String quantityAvailableValue = jsonPath.getString("data.alcoholProduct.stock.quantityAvailable");
        System.out.println(quantityAvailableValue);
        return quantityAvailableValue;
    }

    public static String getAuthToken(String email, String password) {

        Response messagesResponse;

        messagesResponse = RestAssured.with().config(REST_ASSURED_TIMEOUT_CONFIG).config(REST_ASSURED_sslRelaxedHTTPSValidation_CONFIG)
                .given().log().all()
                .given().header("Content-Type", "application/json")
                .contentType(JSON.withCharset("UTF-8"))
                .body("{\n" +
                        "  \"email\": \"" + email + "\",\n" +
                        "  \"password\": \"" + password + "\"\n" +
                        "}")
                .when()
                .post("https://app.staging-bemakers.com/api/auth_tokens")//;

                .then().log().all()
                .contentType(JSON)
                .statusCode(201)
                .extract().response();
        String jsAsString = messagesResponse.body().asString();
        JsonPath jsonPath = new JsonPath(jsAsString);
        String token = jsonPath.getString("token");
        System.out.println(token);
        return token;
    }

}

