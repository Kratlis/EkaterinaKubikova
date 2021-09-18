package com.epam.tc.hw9;

import com.epam.tc.hw9.assertions.RestBoardAssertions;
import com.epam.tc.hw9.service.RestBoardsService;
import com.epam.tc.hw9.utils.DefaultObjectCreator;
import com.example.types.BoardDto;
import com.example.types.LabelDto;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.util.Arrays;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class BoardsTest {

    RestBoardsService restBoardsService;

    @BeforeMethod(description = "RestBoardsService initialization.")
    public void setup() {
        restBoardsService = RestBoardsService.getInstance();
    }

    @AfterMethod(description = "Deleting all boards.")
    public void clean() {
        Arrays.stream(restBoardsService.getBoards())
              .map(BoardDto::getId)
              .forEach(restBoardsService::deleteBoard);
    }

    @Test(description = "Create board.",
          dataProviderClass = TrelloDataProvider.class,
          dataProvider = "boardNames")
    public void createBoard(String testId, String boardName) {
        String boardId = restBoardsService.createBoard(DefaultObjectCreator.createBoard().withName(boardName))
                                          .getId();

        BoardDto board = restBoardsService.getBoard(boardId);

        new RestBoardAssertions(board)
            .verifyBoardName(boardName)
            .verifyBoardId(boardId);
    }

    @Test(description = "Delete board.",
          dataProviderClass = TrelloDataProvider.class,
          dataProvider = "boardNames")
    public void deleteBoard(String testId, String boardName) {
        SoftAssertions softAssertions = new SoftAssertions();
        String boardId = restBoardsService.createBoard(DefaultObjectCreator.createBoard().withName(boardName))
                                          .getId();

        String response = restBoardsService.deleteBoard(boardId);
        checkDeleted(response, softAssertions);

        response = restBoardsService.deleteBoard(boardId);
        softAssertions.assertThat(response)
                      .isEqualTo("The requested resource was not found.");
        softAssertions.assertAll();
    }

    @Test(description = "Create labels on the specified board.",
          dataProviderClass = TrelloDataProvider.class,
          dataProvider = "labels")
    public void createLabelsOnBoard(String testId, String boardName, LabelDto... labels) {
        BoardDto createdBoard = restBoardsService.createBoard(DefaultObjectCreator.createBoard().withName(boardName));

        Arrays.stream(labels).forEach(labelDto -> labelDto.withIdBoard(createdBoard.getId()));

        restBoardsService.createLabels(createdBoard.getId(), labels);

        new RestBoardAssertions(createdBoard)
            .verifyLabels(labels);
    }

    private void checkDeleted(String response, SoftAssertions softAssertions) {
        JsonElement value = new Gson().fromJson(response, JsonObject.class).get("_value");
        softAssertions.assertThat(value.isJsonNull())
                      .isTrue();
    }
}
