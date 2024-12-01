package Nodes.statement.Declarations;

import Nodes.Body;
import Nodes.Enums.Type;
import Nodes.Parameter;
import Nodes.Program;
import Nodes.jasmine.CodeGenerator;
import Nodes.statement.Statement;
import main.IndentManager;
import main.MyLangParser;
import org.antlr.v4.runtime.tree.ParseTree;

import java.util.ArrayList;
import java.util.List;

public class RoutineDeclaration extends Statement {
    private String name;
    private List<Parameter> parameters;
    private Type returnType;
    private Body body;

    public RoutineDeclaration() {
    }

    public RoutineDeclaration(String name, List<Parameter> parameters, Type returnType, Body body) {
        this.name = name;
        this.parameters = parameters;
        this.returnType = returnType;
        this.body = body;
    }

    public boolean isReturnTypeVoid() {
        return this.returnType == null;
    }

    public static RoutineDeclaration parse(ParseTree tree, MyLangParser parser) {

        RoutineDeclaration routine = new RoutineDeclaration();

        routine.name = tree.getChild(1).getText();

        // Need to handle modifiable primary types. Does not affect parse method logic.
        Program.enterScope(routine.name);

        for (int i = 0; i < tree.getChildCount(); i++) {
            if (tree.getChild(i) instanceof MyLangParser.ParametersContext) {
                routine.parameters = parseParameters(tree.getChild(i));
            } else if (tree.getChild(i) instanceof MyLangParser.TypeContext) {
                String rt = tree.getChild(i).getText();
                routine.returnType = Type.fromString(rt);
                Program.addRoutineReturnType(routine.name, routine.returnType);
            } else if (tree.getChild(i) instanceof MyLangParser.BodyContext) {
                routine.body = Body.parse(tree.getChild(i), parser);
            }
        }

        Program.addRoutineReturnType(routine.name, routine.returnType);

        if (routine.parameters == null) {
            routine.parameters = new ArrayList<>();
        }

        // Need to handle modifiable primary types. Does not affect parse method logic.
        Program.scopeManager.exitScope();

        return routine;
    }

    private static List<Parameter> parseParameters(ParseTree parametersTree) {
        List<Parameter> params = new ArrayList<>();
        for (int i = 0; i < parametersTree.getChildCount(); i += 2) {
            try {
                String paramName = parametersTree.getChild(i).getChild(0).getText();
                String paramType = parametersTree.getChild(i).getChild(2).getText();

                Type type = Type.fromString(paramType);
                Program.registerVariable(paramName, type);
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
        // Generate method signature
        StringBuilder methodSignature = new StringBuilder(".method public static ");
        methodSignature.append(name).append("(");

        // Enter new scope
        generator.enterScope(name);

        // register routine
        generator.registerRoutine(name, returnType, (ArrayList<Parameter>) parameters);

        // Add parameter types to signature
        if (parameters != null) {
            for (Parameter param : parameters) {
                switch (param.getType()) {
                    case INTEGER, BOOLEAN -> methodSignature.append("I");
                    case REAL -> methodSignature.append("F");
                    default -> throw new IllegalStateException("Unsupported parameter type: " + param.getType());
                }
            }
        }
        methodSignature.append(")");

        // Add return type to signature
        if (returnType == null) {
            methodSignature.append("V");  // void
        } else {
            switch (returnType) {
                case INTEGER, BOOLEAN -> methodSignature.append("I");
                case REAL -> methodSignature.append("F");
                default -> throw new IllegalStateException("Unsupported return type: " + returnType);
            }
        }

        generator.writeToProgram(methodSignature.toString());

        // Method prologue
        generator.writeToProgram(".limit stack 100");
        generator.writeToProgram(".limit locals 100");

        // TODO: handle case, when a routineCallStatement that returns some value other than void is called,
        // TODO: but its value not used.
        // EXAMPLE:
        // func(5)         -> has no effect (must be discarded)
        // var a = func(5) -> has effect

        // Generate body code
        body.generateCode(generator);


        // If no explicit return at the end, add default return
//        if (returnType == null) {
//            generator.writeToProgram("return");
//        } else {
//            switch (returnType) {
//                case INTEGER, BOOLEAN -> generator.writeToProgram("iconst_0\nireturn");
//                case REAL -> generator.writeToProgram("fconst_0\nfreturn");
//                default -> throw new IllegalStateException("Unsupported return type: " + returnType);
//            }
//        }

        if (isReturnTypeVoid()) {
            generator.writeToProgram("return");
        }

        // Method epilogue
        generator.writeToProgram(".end method\n");

        // Exit scope
        generator.exitScope();
    }

}
