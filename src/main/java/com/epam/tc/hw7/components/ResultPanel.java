package com.epam.tc.hw7.components;

import com.epam.jdi.light.elements.common.UIElement;
import com.epam.jdi.light.elements.pageobjects.annotations.locators.UI;

public class ResultPanel {

    @UI(".summ-res")
    public UIElement summary;

    @UI(".elem-res")
    public UIElement elements;

    @UI(".col-res")
    public UIElement color;

    @UI(".met-res")
    public UIElement metal;

    @UI(".sal-res")
    public UIElement vegetables;
}
