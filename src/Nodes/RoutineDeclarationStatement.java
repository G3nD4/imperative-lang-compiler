package Nodes;

import main.MyLangParser;
import org.antlr.v4.runtime.tree.ParseTree;

import java.util.ArrayList;
import java.util.List;

public class RoutineDeclarationStatement extends Statement {
    private String name;
    private List<Parameter> parameters;
    private String returnType;
    private Block body;

    public RoutineDeclarationStatement() {}

    public RoutineDeclarationStatement(String name, List<Parameter> parameters, String returnType, Block body) {
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
                routine.returnType = rt;
            }
        }
        int bodyIndex;
        if (routine.returnType == null) {
            bodyIndex = 8;
        } else {
            bodyIndex = 9;
        }
        routine.body = Block.parse(tree.getChild(bodyIndex), parser);
        return routine;
    }

    private static List<Parameter> parseParameters(ParseTree parametersTree) {
        List<Parameter> params = new ArrayList<>();
        for (int i = 0; i < parametersTree.getChildCount(); i += 2) {
            try {
                String paramName = parametersTree.getChild(i).getChild(0).getText();
                String paramType = parametersTree.getChild(i).getChild(2).getText();
                params.add(new Parameter(paramName, paramType));
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

    public String getReturnType() {
        return returnType;
    }

    public Block getBody() {
        return body;
    }

    @Override
    public String toString() {
        return "Function{" +
                "name='" + name + '\'' +
                ", parameters=" + parameters +
                ", returnType='" + returnType + '\'' +
                ", body=" + body +
                '}';
    }
}
