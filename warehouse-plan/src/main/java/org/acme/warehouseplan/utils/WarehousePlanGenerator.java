package org.acme.warehouseplan.utils;

import org.acme.warehouseplan.domain.Liquid;
import org.acme.warehouseplan.domain.Row;
import org.acme.warehouseplan.domain.WarehousePlan;
import org.acme.warehouseplan.domain.enums.Color;

import java.util.List;

public class WarehousePlanGenerator {

    public WarehousePlan createWarehouseProblem() {

        List<Row> rowsList = List.of(new Row(6,"Row_01", List.of(Color.YEllOW, Color.RED))
                , new Row(12, "Row_02", List.of(Color.RED, Color.BLUE))
        ,new Row(18,"Row_03",List.of(Color.YEllOW, Color.BLUE)));

        List<Liquid> liquidList = List.of(new Liquid(1,"L1", Color.YEllOW),
                new Liquid(1, "L2", Color.YEllOW),
                new Liquid(1, "L3", Color.YEllOW),
                new Liquid(1, "L4", Color.YEllOW),

                new Liquid(1, "L5", Color.RED),
                new Liquid(1, "L6", Color.RED),
                new Liquid(1, "L7", Color.RED),
                new Liquid(1, "L8", Color.RED),
                new Liquid(1, "L9", Color.RED),
                new Liquid(1, "L10", Color.RED),

                new Liquid(2, "L11", Color.BLUE),
                new Liquid(2, "L12", Color.BLUE),

                new Liquid(2, "L13", Color.YEllOW),
                new Liquid(2, "L14", Color.YEllOW),
                new Liquid(2, "L15", Color.YEllOW),
                new Liquid(2, "L16", Color.YEllOW),

                new Liquid(3, "L17", Color.BLUE),
                new Liquid(3, "L18", Color.BLUE));

        return new WarehousePlan(rowsList, liquidList);
    }
}
