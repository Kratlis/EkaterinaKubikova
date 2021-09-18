package com.epam.tc.hw9.assertions;

import com.epam.tc.hw9.service.RestBoardsService;
import com.example.types.ListDto;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.SoftAssertions;

public class RestListAssertions {

    private final ListDto list;

    public RestListAssertions(ListDto list) {
        this.list = list;
    }

    public RestListAssertions verifyListName(String expectedName) {
        SoftAssertions softAssertions = new SoftAssertions();

        softAssertions.assertThat(list.getName())
                      .as("List has name : " + expectedName)
                      .isEqualTo(expectedName);

        softAssertions.assertAll();
        return this;
    }

    public RestListAssertions verifyListId(String id) {
        SoftAssertions softAssertions = new SoftAssertions();

        Assertions.assertThat(list.getId())
                  .as("List has id : " + id)
                  .isEqualTo(id);

        softAssertions.assertAll();
        return this;
    }

    public RestListAssertions verifyBoardName(String expectedBoardName) {
        SoftAssertions softAssertions = new SoftAssertions();
        String boardName = RestBoardsService.getInstance().getBoard(list.getIdBoard()).getName();

        softAssertions.assertThat(boardName)
                  .as("List is on the board with name : " + expectedBoardName)
                  .isEqualTo(expectedBoardName);

        softAssertions.assertAll();
        return this;
    }
}
