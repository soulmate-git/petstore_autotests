package petStore.utils;


import io.restassured.RestAssured;
import io.restassured.config.RestAssuredConfig;
import io.restassured.config.SSLConfig;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.config.RestAssuredConfig.config;
import static io.restassured.http.ContentType.JSON;

public class HttpUtil {

    private final RestAssuredConfig sslConfig;
    protected String url;
    protected Map<String, String> headers;
    protected ContentType contentType = JSON;

    public HttpUtil() {
        sslConfig = config().sslConfig(SSLConfig.sslConfig()
                .with()
                .relaxedHTTPSValidation());
        RestAssured.baseURI = "https://petstore.swagger.io/v2";
    }

    public Response get(String path) {
        return given()
                .config(sslConfig)
                .log().all()
                .get(path).prettyPeek();
    }

    public Response get(Map<String, ?> params, String path) {
        return given()
                .relaxedHTTPSValidation()
                .queryParams(params)
                .when()
                .get(path).prettyPeek();
    }

    public Response getWithHeaders(Map<String, ?> params, String path) {
        return given()
                .relaxedHTTPSValidation()
                .headers(headers)
                .queryParams(params)
                .when()
                .get(path).prettyPeek();
    }

    public Response getWithHeaders(Map<String, ?> headers, Map<String, ?> params, String path) {
        return given()
                .relaxedHTTPSValidation()
                .headers(headers)
                .queryParams(params)
                .when()
                .get(path).prettyPeek();
    }

    public Response post(Object body, String path) {
        return given()
                .config(sslConfig)
                .contentType(contentType)
                .headers(headers)
                .body(body).log().all()
                .post(path).prettyPeek();
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

    public Response delete(String path, Map<String, ?> params) {
        return given()
                .queryParams(params)
                .when()
                .log().all()
                .delete(path);
    }

    public Response delete(Map<String, ?> headers, Map<String, ?> params, String path) {
        return given()
                .queryParams(params)
                .headers(headers)
                .when()
                .log().all()
                .delete(path);
    }

    public Response delete(String path, Object body) {
        return given()
                .body(body)
                .when()
                .log().all()
                .delete(path);
    }


    public Response put(String path, Object body) {
        return given()
                .body(body)
                .when()
                .log().all()
                .put(path);
    }

    public Response put(Map<String, ?> headers, Object body, String path) {
        return given()
                .headers(headers)
                .body(body)
                .when()
                .log().all()
                .put(path);
    }
}