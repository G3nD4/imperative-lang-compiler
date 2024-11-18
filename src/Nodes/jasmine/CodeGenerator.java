package Nodes.jasmine;

import Nodes.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CodeGenerator {
    public CodeGenerator() {}

    private final StringBuilder assemblyProgram = new StringBuilder();
    private int currentStackIndex = 0;
    private final Map<String, VariableInfo> variables = new HashMap<>();
    private int labelCounter = 0; // Counter for generating unique labels

    public void registerVariable(String name, Type type) {
        variables.put(name, new VariableInfo(currentStackIndex, type));
        ++currentStackIndex;
    }

    public VariableInfo getVariable(String name) {
        final VariableInfo info = variables.get(name);
        // If trying to access a variable that does not exist, log an error and exit the program
        if (info == null) {
            System.out.println(("Variable " + name + " does not exist!"));
            System.exit(1);
        }
        return info;
    }

    public String getProgramText() {
        return assemblyProgram.toString();
    }

    public void writeToProgram(String instruction) {
        assemblyProgram.append(instruction).append('\n');
    }

    public void writeToProgram(ArrayList<String> instructions) {
        for (String instruction : instructions) {
            assemblyProgram.append(instruction).append('\n');
        }
    }

    public String generateUniqueLabel(String baseLabel) {
        return baseLabel + "_" + (labelCounter++);
    }

    public void writeLabel(String label) {
        assemblyProgram.append(label).append(":\n");
    }

    public int getCurrentStackIndex() {
        return currentStackIndex > 0 ? currentStackIndex - 1 : 0;
    }

    public int getFreeIndex() {
        return currentStackIndex;
    }

    public int getAndIncreaseCurrentStackIndex() {
        if (currentStackIndex == 0) {
            return 0;
        }
        return ++currentStackIndex;
    }

    public void increaseStackIndex() {
        ++currentStackIndex;
    }
}
