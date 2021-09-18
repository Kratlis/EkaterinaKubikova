package com.epam.tc.hw9.utils;

import com.example.types.BoardDto;
import com.example.types.CardDto;
import com.example.types.ListDto;

public class DefaultObjectCreator {

    public static BoardDto createBoard() {
        return new BoardDto().withName("DEFAULT_BOARD");
    }

    public static ListDto createList() {
        return new ListDto().withName("DEFAULT_LIST");
    }

    public static CardDto createCard() {
        return new CardDto().withName("DEFAULT_CARD");
    }
}
