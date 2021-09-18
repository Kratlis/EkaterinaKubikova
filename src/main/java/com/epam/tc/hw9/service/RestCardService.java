package com.epam.tc.hw9.service;

import com.epam.tc.hw9.assertions.ResponseAssertions;
import com.epam.tc.hw9.utils.URI;
import com.example.types.CardDto;
import io.restassured.response.Response;
import java.util.HashMap;

public class RestCardService {

    public CardDto createCard(CardDto card) {
        HashMap<String, String> parameters = new HashMap<>();
        parameters.put("idList", card.getIdList());
        parameters.put("name", card.getName());

        Response response = new CommonService()
            .postObject(parameters, URI.POST_CARD);

        new ResponseAssertions(response)
            .verifyStatusCode(200);

        return response.getBody().as(CardDto.class);
    }

    public CardDto getCard(String id) {
        return new CommonService()
            .get(String.format(URI.GET_CARD_BY_ID, id))
            .getBody().as(CardDto.class);
    }

    public String deleteCard(String id) {
        return new CommonService()
            .deleteObject(String.format(URI.DELETE_CARD, id))
            .getBody().asString();
    }
}
