package Nodes.Enums;

public enum Sign {
    PLUS, MINUS, NOT;

    public static Sign fromString(final String op) {
        switch (op) {
            case "+":
                return Sign.PLUS;
            case "-":
                return Sign.MINUS;
            case "not":
                return Sign.NOT;
            default:
                System.out.println("Unexpected operation: " + op);
                System.exit(1);
                return null;
        }
    }
}
