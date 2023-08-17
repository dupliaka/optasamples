package org.acme.warehouseplan.domain;

import org.optaplanner.core.api.domain.solution.PlanningEntityCollectionProperty;
import org.optaplanner.core.api.domain.solution.PlanningScore;
import org.optaplanner.core.api.domain.solution.PlanningSolution;
import org.optaplanner.core.api.domain.solution.ProblemFactCollectionProperty;
import org.optaplanner.core.api.domain.valuerange.ValueRangeProvider;
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;

import java.util.List;

@PlanningSolution
public class WarehousePlan {

    @ProblemFactCollectionProperty
    @ValueRangeProvider
    List<Row> rowsList;
    @PlanningEntityCollectionProperty
    List<Liquid> liquidList;
    @PlanningScore
    HardSoftScore score;

    public WarehousePlan() {
    }

    public WarehousePlan(List<Row> rowsList, List<Liquid> liquidList) {
        this.rowsList = rowsList;
        this.liquidList = liquidList;
    }

    public List<Row> getRowsList() {
        return rowsList;
    }

    public void setRowsList(List<Row> rowsList) {
        this.rowsList = rowsList;
    }

    public List<Liquid> getLiquidList() {
        return liquidList;
    }

    public void setLiquidList(List<Liquid> liquidList) {
        this.liquidList = liquidList;
    }
}
