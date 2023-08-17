package org.acme.warehouseplan.domain.enums;

public enum Color {
    YEllOW, RED, BLUE;

    @Override
    public String toString() {
        return this.name();
    }
}
