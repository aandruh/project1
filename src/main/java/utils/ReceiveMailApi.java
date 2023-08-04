package utils;


import io.restassured.RestAssured;
import io.restassured.response.Response;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static io.restassured.http.ContentType.JSON;
import static utils.RestAssuredLocalConfigs.REST_ASSURED_TIMEOUT_CONFIG;
import static utils.RestAssuredLocalConfigs.REST_ASSURED_sslRelaxedHTTPSValidation_CONFIG;

public class ReceiveMailApi {
    public static String getConfirmOrderLink(String orderNumber) {
        Response messagesResponse;
        Response extractedConfirmLink;
        String messageId = null;
        String output = null;

        messagesResponse = RestAssured.with().config(REST_ASSURED_TIMEOUT_CONFIG).config(REST_ASSURED_sslRelaxedHTTPSValidation_CONFIG)
//            .given().log().all()
                .given().header("Content-Type", "application/json")
                .given().header("Api-Token", "c0942e65f8715734969639e38e8a8bd3")
                .contentType(JSON.withCharset("UTF-8"))
                .when()
                .get("https://mailtrap.io/api/accounts/1116195/inboxes/1565036/messages")//;

                .then().log().all()
                .contentType(JSON)
                .statusCode(200)
                .extract().response();
        Message[] messages = messagesResponse.body().as(Message[].class);


        for (int i = 0; i < messages.length; i++) {
            if (messages[i].subject.contains("Picking list for order " + orderNumber + " from")) {
                messageId = messages[i].id;
                break;
            }

        }
        extractedConfirmLink = RestAssured.with().config(REST_ASSURED_TIMEOUT_CONFIG).config(REST_ASSURED_sslRelaxedHTTPSValidation_CONFIG)
//            .given().log().all()
                .given().header("Content-Type", "application/json")
                .given().header("Api-Token", "c0942e65f8715734969639e38e8a8bd3")
                .contentType(JSON.withCharset("UTF-8"))
                .when()
                .get("https://mailtrap.io/api/accounts/1116195/inboxes/1565036/messages/" + messageId + "/body.txt");
        System.out.println(extractedConfirmLink.body().asString());
        System.out.println(extractedConfirmLink.statusCode());
        System.out.println(extractedConfirmLink.contentType());
        String input = extractedConfirmLink.body().asString();


        try {
            if (input != null) {
                Pattern pattern = Pattern.compile("\\[Confirm order\\]\\((.+)\\)");
                Matcher matcher = pattern.matcher(input);
                if (matcher.find()) {
                    output = matcher.group(1);
                    System.out.println("Extracted link: " + output);

                } else {
                    System.out.println("No Match found in the given input string");
                }
            } else {
                System.out.println("Input is empty");
            }
        } catch (Exception e) {
            System.out.println("Error while parsing the Email Body: " + e);
        }

        return output;
    }
}



