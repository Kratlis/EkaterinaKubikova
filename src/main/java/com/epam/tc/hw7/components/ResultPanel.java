package com.epam.tc.hw7.components;

import com.epam.jdi.light.elements.pageobjects.annotations.locators.UI;

public class ResultPanel {

    @UI(".summ-res")
    public ResultRow summary;

    @UI(".elem-res")
    public ResultRow elements;

    @UI(".col-res")
    public ResultRow color;

    @UI(".met-res")
    public ResultRow metal;

    @UI(".sal-res")
    public ResultRow vegetables;
}
