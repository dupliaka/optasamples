package org.acme.warehouseplan.solver;

import org.acme.warehouseplan.domain.Liquid;
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;
import org.optaplanner.core.api.score.stream.Constraint;
import org.optaplanner.core.api.score.stream.ConstraintFactory;
import org.optaplanner.core.api.score.stream.ConstraintProvider;

import static org.optaplanner.core.api.score.stream.ConstraintCollectors.sum;

public class WarehousePlanConstraintProvider implements ConstraintProvider {

    @Override
    public Constraint[] defineConstraints(ConstraintFactory constraintFactory) {
        return new Constraint[]{
                requiredCapacityTotal(constraintFactory),
                colorMatch(constraintFactory)
        };
    }

    private Constraint colorMatch(ConstraintFactory constraintFactory) {
        return constraintFactory.forEach(Liquid.class)
                .filter(liquid -> !liquid.getRow().getColors().contains(liquid.getColor()))
                .penalize(HardSoftScore.ONE_HARD)
                .asConstraint("colorMatch");
    }

    private Constraint requiredCapacityTotal(ConstraintFactory constraintFactory) {
        return constraintFactory.forEach(Liquid.class)
                .groupBy(Liquid::getRow, sum(Liquid::getRequiredCapacity))
                .filter((row, requiredLiquidCapacity) -> requiredLiquidCapacity > row.getCapacity())
                .penalize(HardSoftScore.ONE_HARD,
                        (row, requiredLiquidCapacity) -> requiredLiquidCapacity - row.getCapacity())
                .asConstraint("requiredLiquidCapacity");
    }
}
