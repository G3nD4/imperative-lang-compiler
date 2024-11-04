package Nodes;

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
        return '\n' + indent + "Parameter:\n" +
                indent + "          |" + '\n' +
                indent +  "---name=" + name + '\n' +
                indent + "---type=" + type.toString().toLowerCase() + '\n';
    }
}
