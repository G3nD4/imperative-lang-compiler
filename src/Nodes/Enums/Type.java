package Nodes;

/*
[Type.IDENTIFIER] is ONLY needed for optimization process.

FIXME: If you meet type [Type.IDENTIFIER] during code-generation process,
FIXME: SOMETHING IS WRONG in OPTIMIZATION ALGORITHM !!!
 */
public enum Type {
    INTEGER, REAL, BOOLEAN, IDENTIFIER; // IDENTIFIER = user-defined type
    public static Type fromString(String typeString) {
        return switch (typeString) {
            case "integer" -> Type.INTEGER;
            case "real" -> Type.REAL;
            case "boolean" -> Type.BOOLEAN;
            default -> Type.IDENTIFIER;
        };
    }
}
