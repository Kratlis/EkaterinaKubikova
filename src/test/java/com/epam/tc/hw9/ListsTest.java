package com.epam.tc.hw9;

import com.epam.tc.hw9.assertions.RestListAssertions;
import com.epam.tc.hw9.service.RestBoardsService;
import com.epam.tc.hw9.service.RestListsService;
import com.epam.tc.hw9.utils.DefaultObjectCreator;
import com.example.types.BoardDto;
import com.example.types.ListDto;
import com.google.api.client.http.HttpStatusCodes;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.util.Arrays;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ListsTest {

    private BoardDto board;

    private RestListsService listsService;
    private RestBoardsService boardsService;

    @BeforeMethod
    public void init() {
        boardsService = RestBoardsService.getInstance();
        listsService = RestListsService.getInstance();
        board = boardsService
            .createBoard(DefaultObjectCreator.createBoard());
    }

    @Test(description = "Create default list.")
    public void createList() {
        ListDto listForCreation = DefaultObjectCreator.createList();

        String createdListId = listsService.createList(listForCreation.withIdBoard(board.getId()))
                                           .getId();

        ListDto createdList = listsService.getList(createdListId);

        new RestListAssertions(createdList)
            .verifyListName(listForCreation.getName())
            .verifyListId(createdListId);
    }

    @Test(description = "Create lists on the specified boards.",
          dataProviderClass = TrelloDataProvider.class,
          dataProvider = "listNames")
    public void createListsOnBoard(String testId, String boardName, String... lists) {
        for (String listName : lists) {
            board = boardsService.createBoard(DefaultObjectCreator.createBoard().withName(boardName));

            ListDto listForCreation = DefaultObjectCreator.createList()
                                                          .withName(listName)
                                                          .withIdBoard(board.getId());

            String createdListId = listsService.createList(listForCreation)
                                               .getId();

            ListDto createdList = listsService.getList(createdListId);

            new RestListAssertions(createdList)
                .verifyListName(listForCreation.getName())
                .verifyListId(createdListId)
                .verifyBoardName(boardName);
        }
    }

    @Test
    public void deleteBoardWithList() {

        String listId = listsService.createList(DefaultObjectCreator.createList().withIdBoard(board.getId()))
                                    .getId();

        String response = boardsService.deleteBoard(board.getId());
        checkDeleted(response);

        Assertions.assertThat(listsService.getNonexistentList(listId).statusCode())
                  .isEqualTo(HttpStatusCodes.STATUS_CODE_NOT_FOUND);
    }

    @Test
    public void doubleDeleteList() {
        SoftAssertions softAssertions = new SoftAssertions();

        String listId = listsService.createList(DefaultObjectCreator.createList().withIdBoard(board.getId()))
                                    .getId();

        String response = listsService.deleteList(listId);
        checkDeleted(response);
        String boardId = RestBoardsService.getInstance()
                                          .createBoard(DefaultObjectCreator.createBoard()).getId();
        softAssertions.assertThat(listsService.moveListToBoard(listId, boardId).statusCode())
            .isEqualTo(HttpStatusCodes.STATUS_CODE_NOT_FOUND);
        softAssertions.assertAll();
    }

    private void checkDeleted(String response) {
        JsonElement value = new Gson().fromJson(response, JsonObject.class).get("_value");
        Assertions.assertThat(value.isJsonNull())
                  .isTrue();
    }

    @AfterMethod
    public void clean() {
        Arrays.stream(boardsService.getBoards())
              .map(BoardDto::getId)
              .forEach(boardsService::deleteBoard);
    }
}
