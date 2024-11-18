package Nodes.statement;

import Nodes.jasmine.CodeGenerator;
import main.IndentManager;

public class Break extends Statement {
    public Break() {}

    public String toString(String indent) {
        IndentManager.print("Break Statement");
        return "";
    }

    @Override
    public void generateCode(CodeGenerator generator) {

    }
}
