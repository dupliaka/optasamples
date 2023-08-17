package org.acme.warehouseplan;

import org.acme.warehouseplan.domain.Liquid;
import org.acme.warehouseplan.domain.WarehousePlan;
import org.acme.warehouseplan.solver.WarehousePlanConstraintProvider;
import org.acme.warehouseplan.utils.WarehousePlanGenerator;
import org.optaplanner.core.api.solver.Solver;
import org.optaplanner.core.api.solver.SolverFactory;
import org.optaplanner.core.config.solver.SolverConfig;

import java.time.Duration;

public class WarehousePlanApp {
    public static void main(String[] args) {

        SolverFactory<WarehousePlan> solverFactory = SolverFactory.create(new SolverConfig()
                .withSolutionClass(WarehousePlan.class)
                .withEntityClasses(Liquid.class)
                .withConstraintProviderClass(WarehousePlanConstraintProvider.class)
                .withTerminationSpentLimit(Duration.ofSeconds(10)));
        Solver<WarehousePlan> solver = solverFactory.buildSolver();

        WarehousePlan problem = new WarehousePlanGenerator().createWarehouseProblem();

        // Solve the problem
        WarehousePlan solvedCloudBalance = solver.solve(problem);

        print(solvedCloudBalance);

    }

/*
    Prints results assignment.
    For example if it is given 2 Liquids L1 and L2
    with capacity 1,2  and color WHITE and 2 Rows R1 and R2 with capacity 1 and 3 and color WHITE

    Then the calculation result will be printed as follows:
    Liquid->Row
    L2(1/WHITE)->R1(1/WHITE)
    L1(2/WHITE)->R2(3/WHITE)
*/

    private static void print(WarehousePlan solvedCloudBalance) {
        StringBuilder stringBuilder = new StringBuilder();
        System.out.println("Liquid->Row");

        for (Liquid l : solvedCloudBalance.getLiquidList()) {

            stringBuilder.append(String.format("%s(%d/%s)->%s(%d/%s)",
                            l.getName(),
                            l.getRequiredCapacity(),
                            l.getColor(),
                            l.getRow().getName(),
                            l.getRow().getCapacity(),
                            l.getRow().getColorsAsString()))
                    .append("\n");
        }

        System.out.println(stringBuilder);
    }
}
