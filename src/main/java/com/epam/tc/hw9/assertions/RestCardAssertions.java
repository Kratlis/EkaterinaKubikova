package com.epam.tc.hw9.assertions;

import com.example.types.CardDto;
import org.assertj.core.api.SoftAssertions;

public class RestCardAssertions {

    private final CardDto card;

    public RestCardAssertions(CardDto card) {
        this.card = card;
    }

    public RestCardAssertions verifyCardName(String expectedName) {
        SoftAssertions softAssertions = new SoftAssertions();

        softAssertions.assertThat(card.getName())
                      .as("Card has name : " + expectedName)
                      .isEqualTo(expectedName);

        softAssertions.assertAll();
        return this;
    }

    public RestCardAssertions verifyCardId(String id) {
        SoftAssertions softAssertions = new SoftAssertions();

        softAssertions.assertThat(card.getId())
                      .as("Card has id : " + id)
                      .isEqualTo(id);

        softAssertions.assertAll();
        return this;
    }
}
