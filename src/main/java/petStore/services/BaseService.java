package petStore.services;


import io.restassured.RestAssured;
import io.restassured.config.RestAssuredConfig;
import io.restassured.config.SSLConfig;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.config.RestAssuredConfig.config;
import static io.restassured.http.ContentType.JSON;

public abstract class BaseService {

    private final RestAssuredConfig sslConfig;
    protected String url;
    protected Map<String, String> headers;
    protected ContentType contentType = JSON;

    public BaseService() {
        sslConfig = config().sslConfig(SSLConfig.sslConfig()
                .with()
                .relaxedHTTPSValidation());
        RestAssured.baseURI = "https://petstore.swagger.io/v2";
    }

    public Response post(Map<String, ?> headers, Object body, String path) {
        return given()
                .config(sslConfig)
                .headers(headers)
                .contentType(contentType)
                .body(body).log().all()
                .post(path).prettyPeek();
    }

    public Response delete(String path) {
        return given()
                .config(sslConfig)
                .log().all()
                .delete(path)
                .prettyPeek();
    }

    public Response get(String path) {
        return given()
                .config(sslConfig)
                .log().all()
                .get(path).prettyPeek();
    }


    public Response put(String path, Object body) {
        return given()
                .contentType(JSON)
                .body(body)
                .when()
                .log().all()
                .put(path).prettyPeek();
    }
}