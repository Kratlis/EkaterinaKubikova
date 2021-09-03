package com.epam.tc.entities;

import com.epam.jdi.tools.DataClass;
import java.util.Arrays;

public class MetalsColorsFormData extends DataClass<MetalsColorsFormData> {
    public String[] summary;
    public String[] elements;
    public String color;
    public String metals;
    public String[] vegetables;

    @Override
    public String toString() {
        return String.format("MetalsColorsFormData{summary=%s, elements=%s, color='%s', metals='%s', vegetables=%s}",
            Arrays.toString(summary), Arrays.toString(elements), color, metals, Arrays.toString(vegetables));
    }
}
