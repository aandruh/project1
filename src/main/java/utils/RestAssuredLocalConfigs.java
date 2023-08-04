package utils;

import io.restassured.RestAssured;
import io.restassured.config.RestAssuredConfig;

import static io.restassured.config.HttpClientConfig.httpClientConfig;
import static io.restassured.config.SSLConfig.sslConfig;
import static org.apache.http.client.params.ClientPNames.CONN_MANAGER_TIMEOUT;
import static org.apache.http.params.CoreConnectionPNames.*;

public class RestAssuredLocalConfigs {
    public static RestAssuredConfig REST_ASSURED_TIMEOUT_CONFIG = RestAssured.config().httpClient(httpClientConfig()
            .setParam(CONN_MANAGER_TIMEOUT, 1000000L)
            .setParam(CONNECTION_TIMEOUT, 1000000)
            .setParam(SO_TIMEOUT, 1000000)
            .setParam(STALE_CONNECTION_CHECK, true));
    public static RestAssuredConfig REST_ASSURED_sslRelaxedHTTPSValidation_CONFIG = RestAssured.config().sslConfig(sslConfig().relaxedHTTPSValidation("TLS"));
}
