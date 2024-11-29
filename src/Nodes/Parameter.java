package Nodes;

import Nodes.Enums.Type;
import main.IndentManager;

public class Parameter {
    private final String name;
    private final Type type;

    public Parameter(String name, Type type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public Type getType() {
        return type;
    }

    public String toString(String indent) {
        IndentManager.print("Parameter:");
        IndentManager.goDown();
        IndentManager.print("name: " + name);
        IndentManager.print("type: " + type.toString().toLowerCase());
        IndentManager.goUp();

        return "";
    }
}
