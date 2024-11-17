package Nodes.jasmine;

import Nodes.Type;

public class VariableInfo {
    private final int index;
    private final Type type;

    VariableInfo(int index, Type type) {
        this.index = index;
        this.type = type;
    }

    public int getIndex() {
        return index;
    }

    public Type getType() {
        return type;
    }
}