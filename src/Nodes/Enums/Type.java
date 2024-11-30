package Nodes.Enums;

import Nodes.jasmine.CodeGenerator;

import java.util.HashMap;

/*
[Type.IDENTIFIER] is ONLY needed for optimization process.

FIXME: If you meet type [Type.IDENTIFIER] during code-generation process,
FIXME: SOMETHING IS WRONG in OPTIMIZATION ALGORITHM !!!
 */
public enum Type {
    INTEGER("integer"), REAL("real"), BOOLEAN("boolean"), IDENTIFIER(""); // IDENTIFIER = user-defined type

    Type(String type) {}

    void setType(String type) {
        this.type = type;
    }

    public static Type fromString(String typeString) {
        if (!typeString.equals("integer") && !typeString.equals("real") && !typeString.equals("boolean")) {
            final Type result = CodeGenerator.getType(typeString);
            if (result == null) {
                System.out.println("Type " + typeString + " is not defined! (Output from Type.fromString)");
                System.exit(1);
            }
            return result;
        }
        return switch (typeString) {
            case "integer" -> Type.INTEGER;
            case "real" -> Type.REAL;
            case "boolean" -> Type.BOOLEAN;
            default -> Type.IDENTIFIER;
        };
    }
    private String type;
}
