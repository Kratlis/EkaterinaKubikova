package com.epam.tc.hw9;

import com.example.types.LabelDto;
import org.testng.annotations.DataProvider;

public class TrelloDataProvider {

    @DataProvider
    public static Object[][] boardNames() {
        return new Object[][] {
            {"BOARD_1", "NEW_BOARD"},
            {"BOARD_2", "98"},
            {"BOARD_3", "0"},
            {"BOARD_4", " b o a r d "},
            {"BOARD_5", " "},
        };
    }

    @DataProvider
    public static Object[][] listNames() {
        return new Object[][] {
            {"LIST_1", "NEW_BOARD", "NEW_LIST"},
            {"LIST_2", "NEW_BOARD", "1", "2", "3"},
            {"LIST_3", "NEW_BOARD", "$%\"#@|$"}
        };
    }

    @DataProvider
    public static Object[][] labels() {
        return new Object[][] {
            {"LABELS_1", "NEW_BOARD_WITH_LABELS",
                new LabelDto().withName("security").withColor("yellow"),
                new LabelDto().withName("task").withColor("blue"),
                new LabelDto().withName("ASAP").withColor("red")
            },
            {"LABELS_2", "NEW_BOARD_WITH_LABELS",
                new LabelDto().withName("shopping").withColor("orange"),
                new LabelDto().withName("grocery").withColor("green")
            }
        };
    }
}
