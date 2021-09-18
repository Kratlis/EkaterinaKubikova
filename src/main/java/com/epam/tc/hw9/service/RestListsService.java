package com.epam.tc.hw9.service;

import com.epam.tc.hw9.assertions.ResponseAssertions;
import com.epam.tc.hw9.utils.DefaultObjectCreator;
import com.epam.tc.hw9.utils.URI;
import com.example.types.ListDto;
import com.google.api.client.http.HttpStatusCodes;
import com.google.gson.JsonSyntaxException;
import io.restassured.response.Response;
import java.util.HashMap;
import org.hamcrest.Matchers;

public class RestListsService extends CommonService {

    private static RestListsService restListsService;

    private RestListsService() {
    }

    public static RestListsService getInstance() {
        if (restListsService == null) {
            restListsService = new RestListsService();
        }
        return restListsService;
    }

    public ListDto createList(ListDto list) {
        HashMap<String, String> parameters = new HashMap<>();
        parameters.put("name", list.getName());
        parameters.put("idBoard", list.getIdBoard());

        Response response = new CommonService()
            .postObject(parameters, URI.POST_LIST);

        new ResponseAssertions(response)
            .verifyStatusCode(200);

        return response.getBody().as(ListDto.class);
    }

    public ListDto getList(String id) {
        return new CommonService()
            .get(String.format(URI.GET_LIST_BY_ID, id))
            .getBody().as(ListDto.class);
    }

    public Response getNonexistentList(String id) {
        return new CommonService()
            .getWithError(String.format(URI.GET_LIST_BY_ID, id));
    }

    public Response moveListToBoard(String listId, String boardId) throws JsonSyntaxException {
        HashMap<String, Object> parameters = new HashMap<>();
        parameters.put("value", boardId);
        return new CommonService()
            .putObject(parameters, String.format(URI.MOVE_LIST_TO_BOARD, listId));
    }

    public String deleteList(String listId) throws JsonSyntaxException {
        String boardId = RestBoardsService.getInstance()
                                          .createBoard(DefaultObjectCreator.createBoard()).getId();

        Response response = moveListToBoard(listId, boardId);
        response.then()
                .statusCode(Matchers.equalTo(HttpStatusCodes.STATUS_CODE_OK));

        return new CommonService()
            .deleteObject(String.format(URI.DELETE_BOARD, boardId))
            .getBody().asString();
    }
}
