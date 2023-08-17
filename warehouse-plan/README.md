# Warehouse liquids planning problem

Modeling challenge: Given various colored liquids to be allocated into rows of boxes, each row having distinct height and accepting only specific colors.
Unlike strict elongated boxing, liquids can be packed in multiple smaller boxes to better suit the fluid allocation problem.

![Warehouse problem]()

We have a:
 - set of Liquids
 - set of Rows
 - each liquid should be assigned to a row
 - capacity of all liquids assigned to the row should not be greater then available row capacity
 - row defines color of liquids that could be stored 

We need to reassign rows for liquids, leading to row changes.
Our solution involves iteratively assigning rows to liquids, recalculating scores after each move.
To facilitate this, we mark the row in the Liquid class with @PlanningVariable, making it a PlanningEntity.
To integrate results, we create a class with a list of planned (assigned) liquids marked with @PlanningEntityCollectionProperty, alongside a list of unchanged rows marked with @ProblemFactCollectionProperty and @ValueRangeProvider.
```java
    private Constraint requiredCapacityTotal(ConstraintFactory constraintFactory) {
        return constraintFactory.forEach(Liquid.class) // go throw all planning entities
               .groupBy(Liquid::getRow, sum(Liquid::getRequiredCapacity)) // fold it by rows and sum assigned liquids capacities
               .filter((row, requiredLiquidCapacity) -> requiredLiquidCapacity > row.getCapacity()) // check if it is over limit
               .penalize(HardSoftScore.ONE_HARD,
               (row, requiredLiquidCapacity) -> requiredLiquidCapacity - row.getCapacity()) // the more its overlimits - the more score is getting penalized
               .asConstraint("requiredLiquidCapacity");
        }
```

To build the project run:


`mvn clean install`

To run the solver on test data:

`java -jar warehouse-plan/target/warehouse-plan.jar`

```
Liquid->Row
L1(1/YEllOW)->Row_01(6/YEllOW, RED)
L2(1/YEllOW)->Row_01(6/YEllOW, RED)
L3(1/YEllOW)->Row_01(6/YEllOW, RED)
L4(1/YEllOW)->Row_01(6/YEllOW, RED)
L5(1/RED)->Row_01(6/YEllOW, RED)
L6(1/RED)->Row_01(6/YEllOW, RED)
L7(1/RED)->Row_02(12/RED, BLUE)
L8(1/RED)->Row_02(12/RED, BLUE)
L9(1/RED)->Row_02(12/RED, BLUE)
L10(1/RED)->Row_02(12/RED, BLUE)
L11(2/BLUE)->Row_02(12/RED, BLUE)
L12(2/BLUE)->Row_02(12/RED, BLUE)
L13(2/YEllOW)->Row_03(18/YEllOW, BLUE)
L14(2/YEllOW)->Row_03(18/YEllOW, BLUE)
L15(2/YEllOW)->Row_03(18/YEllOW, BLUE)
L16(2/YEllOW)->Row_03(18/YEllOW, BLUE)
L17(3/BLUE)->Row_02(12/RED, BLUE)
L18(3/BLUE)->Row_03(18/YEllOW, BLUE)
```