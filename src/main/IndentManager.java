package main;

public class IndentManager {
    public IndentManager() {}

    private static int indent = 0;

    public static void goDown() {
        ++indent;
    }

    public static void goUp() {
        --indent;
        if (indent < 0) {
            indent = 0;
        }
    }

    public static String getIndent() {
        return "\t".repeat(Math.max(0, indent));
    }

    public static void print(String string) {
        System.out.println(IndentManager.getIndent() + " " + string);
    }
}
