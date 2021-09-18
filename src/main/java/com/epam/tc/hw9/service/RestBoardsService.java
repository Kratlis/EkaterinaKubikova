package com.epam.tc.hw9.service;

import com.epam.tc.hw9.assertions.ResponseAssertions;
import com.epam.tc.hw9.utils.URI;
import com.example.types.BoardDto;
import com.example.types.LabelDto;
import io.restassured.response.Response;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import lombok.SneakyThrows;

public class RestBoardsService extends CommonService {

    @SneakyThrows
    public BoardDto createBoard(BoardDto board) {
        HashMap<String, String> boardParameters = new HashMap<>();
        boardParameters.put("name", board.getName());

        Response response = new CommonService()
            .postObject(boardParameters, URI.POST_BOARD);

        new ResponseAssertions(response)
            .verifyStatusCode(200);

        return response.getBody().as(BoardDto.class);
    }

    @SneakyThrows
    public BoardDto[] getBoards() {
        return new CommonService()
            .get(URI.GET_BOARDS)
            .getBody().as(BoardDto[].class);
    }

    public BoardDto getBoard(String id) {
        return new CommonService()
            .get(String.format(URI.GET_BOARD_BY_ID, id))
            .getBody().as(BoardDto.class);
    }

    public String deleteBoard(String id) {
        return new CommonService()
            .deleteObject(String.format(URI.DELETE_BOARD, id))
            .getBody().asString();
    }

    public void createLabels(String boardId, LabelDto... labels) {
        HashMap<String, String> labelParameters = new HashMap<>();
        for (LabelDto label : labels) {
            labelParameters.put("name", label.getName());
            labelParameters.put("color", label.getColor());

            new CommonService()
                .postObject(labelParameters, String.format(URI.CREATE_LABEL, boardId));

            labelParameters.clear();
        }
    }

    public List<LabelDto> getLabels(String boardId) {
        List<LabelDto> labels = List.of(new CommonService()
            .get(String.format(URI.GET_LABELS_ON_BOARD, boardId))
            .getBody().as(LabelDto[].class));
        labels = labels.stream()
                       .filter(label -> !label.getName().equals("")).collect(Collectors.toList());
        return labels;
    }
}
