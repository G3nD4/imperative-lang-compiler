package Nodes.statement;

import main.IndentManager;

public class Break extends Statement {
    public Break() {}

    public String toString(String indent) {
        IndentManager.print("Break Statement");
        return "";
    }
}
