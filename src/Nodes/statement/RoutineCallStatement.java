package Nodes.statement;

import Nodes.Parameter;
import Nodes.RoutineCallParameter;
import Nodes.Type;
import Nodes.expression.Expression;
import Nodes.jasmine.CodeGenerator;
import main.IndentManager;
import main.MyLangParser;
import org.antlr.v4.runtime.tree.ParseTree;

import java.util.ArrayList;
import java.util.List;

public class RoutineCallStatement extends Statement {
    private final String identifier;
    private final List<RoutineCallParameter> parameters;

    @Override
    public String toString(String indent) {
        IndentManager.print("Routine Call Statement:");
        IndentManager.goDown();
        IndentManager.print("identifier: " + identifier);
        IndentManager.print("parameters:");
        IndentManager.goDown();
        for (final RoutineCallParameter param : parameters) {
            IndentManager.print(param.toString(""));
        }
        IndentManager.goUp();
        IndentManager.goUp();
        return "";
    }

    public static RoutineCallStatement parse(ParseTree tree, MyLangParser parser) {
        String identifier = tree.getChild(0).getText();
        List<RoutineCallParameter> parameters = new ArrayList<>();
        for (int i = 2; i < tree.getChildCount() - 1; i += 2) {
            parameters.add(RoutineCallParameter.parse(tree.getChild(i), parser));
        }
        return new RoutineCallStatement(identifier, parameters);
    }

    public String getIdentifier() {
        return identifier;
    }

    public List<RoutineCallParameter> getParameters() {
        return parameters;
    }

    public RoutineCallStatement(String identifier, List<RoutineCallParameter> parameters) {
        this.identifier = identifier;
        this.parameters = parameters;
    }

    @Override
    public void generateCode(CodeGenerator generator) {

    }
}
