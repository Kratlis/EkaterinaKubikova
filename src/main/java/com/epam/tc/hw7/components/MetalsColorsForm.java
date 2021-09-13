package com.epam.tc.hw7.components;

import com.epam.jdi.light.elements.complex.Checklist;
import com.epam.jdi.light.elements.complex.dropdown.Dropdown;
import com.epam.jdi.light.elements.composite.Form;
import com.epam.jdi.light.elements.pageobjects.annotations.locators.JDropdown;
import com.epam.jdi.light.elements.pageobjects.annotations.locators.UI;
import com.epam.jdi.light.ui.html.elements.common.Button;
import com.epam.jdi.light.ui.html.elements.complex.RadioButtons;
import com.epam.tc.hw7.custom.VegetablesMultiDropdown;
import com.epam.tc.hw7.entities.MetalsColorsFormData;

public class MetalsColorsForm extends Form<MetalsColorsFormData> {

    @UI("[name=custom_radio_odd]")
    RadioButtons summaryOdd;

    @UI("[name=custom_radio_even]")
    RadioButtons summaryEven;

    @UI("#elements-checklist input")
    Checklist elementsChecklist;

    @JDropdown(root = "div[ui=dropdown]",
               value = ".filter-option",
               list = "li",
               expand = ".caret")
    Dropdown colors;

    @JDropdown(root = "div[ui=combobox]",
               value = ".filter-option",
               list = "li",
               expand = ".caret")
    Dropdown metals;

    VegetablesMultiDropdown vegetables;

    @UI("[id=submit-button]")
    public Button submit;

    @Override
    public void fill(MetalsColorsFormData entity) {
        summaryOdd.select(entity.summary[0]);
        summaryEven.select(entity.summary[1]);
        elementsChecklist.select(entity.elements);
        colors.select(entity.color);
        metals.select(entity.metals);
        vegetables.check(entity.vegetables);
    }

    @Override
    public void submit() {
        submit.click();
    }
}
