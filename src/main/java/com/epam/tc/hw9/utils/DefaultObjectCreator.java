package com.epam.tc.hw9.utils;

import com.example.types.BoardDto;
import com.example.types.CardDto;
import com.example.types.ListDto;

public class DefaultObjectCreator {

    public static BoardDto createBoard() {
        return new BoardDto().withName(DefaultNames.DEFAULT_BOARD.name());
    }

    public static ListDto createList() {
        return new ListDto().withName(DefaultNames.DEFAULT_LIST.name());
    }

    public static CardDto createCard() {
        return new CardDto().withName(DefaultNames.DEFAULT_CARD.name());
    }
}
