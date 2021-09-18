package com.epam.tc.hw9.service;

import static io.restassured.RestAssured.given;

import com.epam.tc.hw9.utils.PropertyFileReader;
import com.epam.tc.hw9.utils.URI;
import com.google.api.client.http.HttpStatusCodes;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Properties;
import org.hamcrest.Matchers;

public class CommonService {
    private static final String PROPERTY_FILE = "src/test/resources/hw9_api/test.properties";
    private final RequestSpecification requestSpecification;
    Properties properties;

    public CommonService() {
        properties = PropertyFileReader.init(PROPERTY_FILE);

        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        requestSpecification = new RequestSpecBuilder()
            .setBaseUri(URI.BASE_URL)
            .addQueryParam("key", properties.getProperty("key"))
            .addQueryParam("token", properties.getProperty("token"))
            .build();
    }

    public Response get(String uri) {
        Response response = given(requestSpecification).get(uri);
        response.then()
                .statusCode(Matchers.equalTo(HttpStatusCodes.STATUS_CODE_OK));
        return response;
    }

    public Response deleteObject(String uri) {
        return given(requestSpecification)
            .delete(uri);
    }

    public Response postObject(HashMap<String, String> queryParameters, String uri) {
        for (Entry<String, String> parameter : queryParameters.entrySet()) {
            requestSpecification.queryParam(parameter.getKey(), parameter.getValue());
        }
        return given(requestSpecification
            .header("Content-Type", "application/json"))
            .post(uri);
    }

    public Response putObject(HashMap<String, Object> queryParameters, String uri) {
        for (Entry<String, Object> parameter : queryParameters.entrySet()) {
            requestSpecification.queryParam(parameter.getKey(), parameter.getValue());
        }
        return given(requestSpecification)
            .put(uri);
    }
}
