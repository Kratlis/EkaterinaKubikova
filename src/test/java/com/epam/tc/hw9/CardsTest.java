package com.epam.tc.hw9;

import com.epam.tc.hw9.assertions.RestCardAssertions;
import com.epam.tc.hw9.service.RestBoardsService;
import com.epam.tc.hw9.service.RestCardService;
import com.epam.tc.hw9.service.RestListsService;
import com.epam.tc.hw9.utils.DefaultObjectCreator;
import com.example.types.BoardDto;
import com.example.types.CardDto;
import com.example.types.ListDto;
import java.util.Arrays;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CardsTest {

    private ListDto list;

    private RestCardService restCardService;

    @BeforeMethod
    public void createBoardAndList() {
        restCardService = new RestCardService();
        BoardDto board = new RestBoardsService()
            .createBoard(DefaultObjectCreator.createBoard());
        list = new RestListsService()
            .createList(DefaultObjectCreator.createList().withIdBoard(board.getId()));
    }

    @AfterMethod
    public void clean() {
        RestBoardsService restBoardsService = new RestBoardsService();
        Arrays.stream(restBoardsService.getBoards())
              .map(BoardDto::getId)
              .forEach(restBoardsService::deleteBoard);
    }

    @Test(description = "Create card.")
    public void createCard() {
        CardDto cardForCreation = DefaultObjectCreator.createCard();
        String cardId = restCardService.createCard(cardForCreation.withIdList(list.getId()))
                                       .getId();

        CardDto card = restCardService.getCard(cardId);

        new RestCardAssertions(card)
            .verifyCardName(cardForCreation.getName())
            .verifyCardId(cardId);
    }

    @Test
    public void deleteCard() {
        SoftAssertions softAssertions = new SoftAssertions();

        String cardId = restCardService
            .createCard(DefaultObjectCreator.createCard().withIdList(list.getId()))
            .getId();

        restCardService.deleteCard(cardId);

        String response = restCardService.deleteCard(cardId);
        softAssertions.assertThat(response)
                      .isEqualTo("The requested resource was not found.");
        softAssertions.assertAll();
    }
}
