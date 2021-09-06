package com.epam.tc.hw7.components;

import com.epam.jdi.light.elements.common.UIElement;

public class LogItem extends UIElement {
    String submitButtonLog = "submit-button:button clicked";

    public boolean containsSubmitButtonClickedLog() {
        return text().contains(submitButtonLog);
    }
}
