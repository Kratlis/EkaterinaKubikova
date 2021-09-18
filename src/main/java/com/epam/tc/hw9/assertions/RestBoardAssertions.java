package com.epam.tc.hw9.assertions;

import com.epam.tc.hw9.service.RestBoardsService;
import com.example.types.BoardDto;
import com.example.types.LabelDto;
import java.util.List;
import org.assertj.core.api.SoftAssertions;

public class RestBoardAssertions {

    private final BoardDto board;

    public RestBoardAssertions(BoardDto board) {
        this.board = board;
    }

    public RestBoardAssertions verifyBoardName(String expectedName) {
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(board.getName())
                      .as("Board has name : " + expectedName)
                      .isEqualTo(expectedName);
        softAssertions.assertAll();
        return this;
    }

    public RestBoardAssertions verifyBoardId(String id) {
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(board.getId())
                      .as("Board has id : " + id)
                      .isEqualTo(id);
        softAssertions.assertAll();
        return this;
    }

    public RestBoardAssertions verifyLabels(LabelDto... labels) {
        SoftAssertions softAssertions = new SoftAssertions();
        List<LabelDto> labelsOnBoard = RestBoardsService.getInstance()
                                                        .getLabels(board.getId());
        for (int i = 0; i < labelsOnBoard.size(); i++) {
            softAssertions.assertThat(labelsOnBoard.get(i).getName())
                          .isEqualTo(labels[i].getName());
            softAssertions.assertThat(labelsOnBoard.get(i).getColor())
                          .isEqualTo(labels[i].getColor());
            softAssertions.assertThat(labelsOnBoard.get(i).getIdBoard())
                          .isEqualTo(labels[i].getIdBoard());
        }
        softAssertions.assertAll();
        return this;
    }
}
