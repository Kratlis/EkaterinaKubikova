package com.epam.tc.hw9.utils;

public class URI {
    public static final String BASE_URL = "https://api.trello.com/";

    public static final String GET_BOARDS = "/1/members/me/boards/";
    public static final String GET_BOARD_BY_ID = "/1/boards/%s";
    public static final String POST_BOARD = "/1/boards/";
    public static final String DELETE_BOARD = "/1/boards/%s";

    public static final String GET_LIST_BY_ID = "/1/lists/%s";
    public static final String POST_LIST = "/1/lists/";
    public static final String ARCHIVE_LIST = "/1/lists/%s/closed";
    public static final String MOVE_LIST_TO_BOARD = "/1/lists/%s/idBoard";

    public static final String GET_CARD_BY_ID = "/1/cards/%s";
    public static final String POST_CARD = "/1/cards";
    public static final String DELETE_CARD = "/1/cards/%s";

    public static final String CREATE_LABEL = "/1/boards/%s/labels";
    public static final String GET_LABELS_ON_BOARD = "/1/boards/%s/labels";
}
