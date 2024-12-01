package Nodes.statement;

import Nodes.Enums.Sign;
import Nodes.Enums.Type;
import Nodes.Interfaces.JasminConvertable;
import Nodes.RoutineCallParameter;
import Nodes.expression.UnaryExpression;
import Nodes.jasmine.CodeGenerator;
import Nodes.jasmine.RoutineInfo;
import Nodes.jasmine.VariableInfo;
import Nodes.primary.*;
import main.IndentManager;
import main.MyLangParser;
import org.antlr.v4.misc.OrderedHashMap;
import org.antlr.v4.runtime.tree.ParseTree;

import java.time.temporal.UnsupportedTemporalTypeException;
import java.util.ArrayList;
import java.util.List;

public class RoutineCallStatement extends Statement implements JasminConvertable {
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
        /*
        Need to check:
        1. if routine with this name exists
        2. Does parameters type match

        If everything is fine, we need to call this routine. Example:
        invokestatic SumProgram/printMessage()V
         */


        if (!generator.isRoutine(identifier)) {
            throw new IllegalStateException("Routine: " + this.identifier + " is not declared yet");
        }

        // Which types should be
        RoutineInfo routineInfo = generator.getRoutineInfo(identifier);
        ArrayList<Type> parametersTypes = routineInfo.getParametersTypes();

        // With which types routine is actually called
        ArrayList<Type> thisParametersTypes = new ArrayList<>();

        for (RoutineCallParameter thisParameter : parameters) {
            // Special case for modifiable primary
            if (thisParameter.expression instanceof UnaryExpression
                    && ((UnaryExpression) thisParameter.expression).primary instanceof ModifiablePrimary modPrim) {
                thisParametersTypes.add(modPrim.getType(generator));
            } else {
                thisParametersTypes.add(thisParameter.getType());
            }
        }

        if (parametersTypes.size() != thisParametersTypes.size()) {
            throw new IllegalStateException("Routine: " + this.identifier +
                    " was called with incorrect number of parameters");
        }

        StringBuilder parametersTypesString = new StringBuilder();

        for (int i = 0; i < parametersTypes.size(); i++) {
            if (parametersTypes.get(i) != thisParametersTypes.get(i)) {
                throw new IllegalStateException("Routine call for " + this.identifier + ": Parameters types do not match." +
                        " Should be " + parametersTypes.get(i) + " instead of " + thisParametersTypes.get(i));
            }
        }

        for (int i = parametersTypes.size() - 1; i >= 0; i--) {
            /* For each variable need to
            1. Save its type
            2. Load it on top of the stack
             */
            if (parameters.get(i).expression instanceof UnaryExpression paramUnary) {
                switch (paramUnary.primary) {
                    case IntegerLiteral paramPrimary -> {
                        parametersTypesString.append("I");
                        if (paramUnary.sign == Sign.MINUS) {
                            generator.writeToProgram("ldc -" + paramPrimary.getValue());
                        } else {
                            generator.writeToProgram("ldc " + paramPrimary.getValue());
                        }
                    }
                    case RealLiteral paramPrimary -> {
                        parametersTypesString.append("F");
                        if (paramUnary.sign == Sign.MINUS) {
                            generator.writeToProgram("ldc -" + paramPrimary.getValue());
                        } else {
                            generator.writeToProgram("ldc " + paramPrimary.getValue());
                        }
                    }
                    case BooleanLiteral paramPrimary -> {
                        parametersTypesString.append("I");
                        generator.writeToProgram(paramPrimary.getLoadCode(generator));
                    }
                    case ModifiablePrimary paramPrimary -> {
                        Type type = generator.getVariable(paramPrimary.identifier).getType();
                        switch (type) {
                            case Type.INTEGER, Type.BOOLEAN -> parametersTypesString.append("I");
                            case Type.REAL -> parametersTypesString.append("F");
                            default -> throw new IllegalStateException(this.identifier + ": Type "
                                    + type.toString().toLowerCase() + " is not supported");
                            // Maybe need to check the INDENTIFIER type (user-defined)
                        }
                        generator.writeToProgram(paramPrimary.getLoadCode(generator));
                    }
                    case RoutineCallPrimary paramPrimary -> {
                        paramPrimary.generateCode(generator);
                        final Type type = paramPrimary.getType(generator);
                        final String typeStr = switch(type) {
                            case Type.INTEGER, Type.BOOLEAN -> "I";
                            case Type.REAL -> "F";
                            default -> throw new UnsupportedTemporalTypeException("Type " + type.toString() + " not supported!");
                        };
                        parametersTypesString.append(typeStr);
                    }
                    case null, default -> throw new IllegalStateException("Undefined parameter type." +
                            " Please check 'RoutineCallStatement' class");
                }
            } else {
                parameters.get(i).expression.generateCode(generator);
                final Type type = parameters.get(i).expression.getType(generator);
                final String typeStr = switch(type) {
                    case Type.INTEGER, Type.BOOLEAN -> "I";
                    case Type.REAL -> "F";
                    default -> throw new UnsupportedTemporalTypeException("Type " + type.toString() + " not supported!");
                };
                parametersTypesString.append(typeStr);
            }
        }

        parametersTypesString.reverse();

        String returnType = "";
        // TODO: MAYBE need to handle Type.IDENTIFIER

        if (routineInfo.getReturnType() == null) {
            returnType = "V";
        } else {
            switch (routineInfo.getReturnType()) {
                case Type.REAL -> returnType = "F";
                case Type.INTEGER, Type.BOOLEAN -> returnType = "I";
                default -> returnType = "V";
            }
        }

        // TODO: Jasmin class name may be changed
        generator.writeToProgram("invokestatic SumProgram/" + this.identifier
                + "(" + parametersTypesString + ")" + returnType);

        // TODO: no handling of return type
        // TODO: check for nested routine calls
    }
}
