package Optimizers;

import Nodes.Program;
import Nodes.statement.Declarations.RoutineDeclaration;
import Nodes.statement.Return;

import java.util.ArrayList;

public class RoutineDeadCodeOptimizer implements Optimizer {
    @Override
    public void optimize(Program program) {
        // Traversing each routine declaration
        for (RoutineDeclaration routineDeclaration : program.getRoutineDeclarations()) {
            // ArrayList of declarations and statements BEFORE return.
            // Everything below is omitted.
            ArrayList<Object> orderedDnSOptimized = new ArrayList<>();
            for (Object object : routineDeclaration.getBody().getOrderedDnS()) {
                orderedDnSOptimized.add(object);
                if (object instanceof Return) {
                    break;
                }
            }
            routineDeclaration.getBody().setDns(orderedDnSOptimized);
        }
    }
}
