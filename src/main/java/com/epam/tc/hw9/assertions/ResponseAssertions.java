package com.epam.tc.hw9.assertions;

import io.restassured.response.Response;
import org.assertj.core.api.Assertions;

public class ResponseAssertions {

    private final Response response;

    public ResponseAssertions(Response response) {
        this.response = response;
    }

    public ResponseAssertions verifyStatusCode(int expectedStatusCode) {
        Assertions.assertThat(response.statusCode())
                  .as("Verify response status code.")
                  .isEqualTo(expectedStatusCode);
        return this;
    }
}
