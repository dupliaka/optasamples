package org.acme.warehouseplan.domain;

import org.acme.warehouseplan.domain.enums.Color;
import org.optaplanner.core.api.domain.entity.PlanningEntity;
import org.optaplanner.core.api.domain.variable.PlanningVariable;

@PlanningEntity
public class Liquid {

    Color color;
    int requiredCapacity;
    String name;
    @PlanningVariable
    Row row;

    public Liquid(int requiredCapacity, String name, Color color) {
        this.requiredCapacity = requiredCapacity;
        this.name = name;
        this.color = color;
    }

    public Liquid() {
    }

    public int getRequiredCapacity() {
        return requiredCapacity;
    }

    public Row getRow() {
        return row;
    }

    public String getName() {
        return name;
    }

    public Color getColor() {
        return color;
    }
}
