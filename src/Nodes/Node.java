package Nodes;

import java.util.ArrayList;

public class Node<T> {
    public Node parent;
    public ArrayList<Node> children;
    public T value;

    public Node(Node parent, ArrayList<Node> children, T value) {
        this.parent = parent;
        this.children = children;
        this.value = value;
    }

    public boolean isLeaf() {
        return children.isEmpty();
    }

    @Override
    public String toString() {

        return "Node{" +
                "parent=" + parent.toString() +
                ", children=" + children.toString() +
                '}';
    }
}
