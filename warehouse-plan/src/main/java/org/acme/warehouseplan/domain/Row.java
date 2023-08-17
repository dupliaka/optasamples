package org.acme.warehouseplan.domain;

import org.acme.warehouseplan.domain.enums.Color;

import java.util.List;
import java.util.stream.Collectors;

public class Row {
    int capacity;
    String name;
    List<Color> colors;

    public Row(int capacity, String name, List<Color> colors) {
        this.capacity = capacity;
        this.name = name;
        this.colors = colors;
    }

    public int getCapacity() {
        return capacity;
    }

    public String getName() {
        return name;
    }

    public String getColorsAsString() {
        return colors.stream().map(Color::toString)
                .collect(Collectors.joining(", "));
    }

    public List<Color> getColors() {
        return colors;
    }
}
