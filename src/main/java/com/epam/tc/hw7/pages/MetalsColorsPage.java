package com.epam.tc.hw7.pages;

import com.epam.jdi.light.elements.complex.JList;
import com.epam.jdi.light.elements.composite.WebPage;
import com.epam.jdi.light.elements.pageobjects.annotations.Url;
import com.epam.jdi.light.elements.pageobjects.annotations.locators.Css;
import com.epam.jdi.light.elements.pageobjects.annotations.locators.UI;
import com.epam.tc.hw7.components.LogItem;
import com.epam.tc.hw7.components.MetalsColorsForm;
import com.epam.tc.hw7.components.ResultPanel;

@Url("/metals-colors.html")
public class MetalsColorsPage extends WebPage {
    @Css(".form")
    public MetalsColorsForm form;

    @UI(".logs li")
    public JList<LogItem> logs;

    @UI(".results")
    public ResultPanel resultPanel;
}
