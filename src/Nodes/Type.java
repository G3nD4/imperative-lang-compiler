package Nodes;

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
