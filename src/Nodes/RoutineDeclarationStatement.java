package Nodes;

import Nodes.jasmine.CodeGenerator;
import Nodes.statement.Statement;
import main.IndentManager;
import main.MyLangParser;
import org.antlr.v4.runtime.tree.ParseTree;

import java.util.ArrayList;
import java.util.List;

public class RoutineDeclarationStatement extends Statement {
    private String name;
    private List<Parameter> parameters;
    private Type returnType;
    private Body body;

    public RoutineDeclarationStatement() {
    }

    public RoutineDeclarationStatement(String name, List<Parameter> parameters, Type returnType, Body body) {
        this.name = name;
        this.parameters = parameters;
        this.returnType = returnType;
        this.body = body;
    }

    public static RoutineDeclarationStatement parse(ParseTree tree, MyLangParser parser) {
        RoutineDeclarationStatement routine = new RoutineDeclarationStatement();

        routine.name = tree.getChild(1).getText();
        if (tree.getChildCount() > 5) {
            routine.parameters = parseParameters(tree.getChild(3));
        }
        if (tree.getChildCount() > 4) {
            String rt = tree.getChild(6).getText();
            if (!rt.equals("is")) {
                routine.returnType = switch (rt) {
                    case "integer" -> Type.INTEGER;
                    case "real" -> Type.REAL;
                    case "boolean" -> Type.BOOLEAN;
                    default -> Type.IDENTIFIER;
                };
            }
        }
        int bodyIndex;
        if (routine.returnType == null) {
            bodyIndex = 8;
        } else {
            bodyIndex = 9;
        }
        routine.body = Body.parse(tree.getChild(bodyIndex), parser);
        return routine;
    }

    private static List<Parameter> parseParameters(ParseTree parametersTree) {
        List<Parameter> params = new ArrayList<>();
        for (int i = 0; i < parametersTree.getChildCount(); i += 2) {
            try {
                String paramName = parametersTree.getChild(i).getChild(0).getText();
                String paramType = parametersTree.getChild(i).getChild(2).getText();
                Type type = switch (paramType) {
                    case "integer" -> Type.INTEGER;
                    case "real" -> Type.REAL;
                    case "boolean" -> Type.BOOLEAN;
                    default -> Type.IDENTIFIER;
                };
                params.add(new Parameter(paramName, type));
            } catch (Exception e) {
                break;
            }
        }
        return params;
    }

    public String getName() {
        return name;
    }

    public List<Parameter> getParameters() {
        return parameters;
    }

    public Type getReturnType() {
        return returnType;
    }

    public Body getBody() {
        return body;
    }

    @Override
    public String toString(String indent) {
        IndentManager.print("Routine declaration:");
        IndentManager.goDown();
        IndentManager.print("name: " + name);
        IndentManager.print("parameters:");
        IndentManager.goDown();
        for (final Parameter param : parameters) {
            IndentManager.print(param.toString(""));
        }
        IndentManager.goUp();
        IndentManager.print("returnType: " + (returnType == null ? "NOT DEFINED" : returnType.toString().toLowerCase()));
        IndentManager.print("body:");
        IndentManager.goDown();
        IndentManager.print(body.toString(""));
        IndentManager.goUp();

        return "";
    }

    @Override
    public void generateCode(CodeGenerator generator) {

    }
}
