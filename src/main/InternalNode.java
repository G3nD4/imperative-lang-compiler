package main;

import Nodes.statement.Declarations.Declaration;
import Nodes.statement.Statement;

import java.util.ArrayList;
import java.util.List;

public class InternalNode<T> extends TreeNode<T> {
    public String ruleName;
    public T data;
    public List<TreeNode<T>> children = new ArrayList<>();

    public InternalNode(String ruleName) {
        this.ruleName = ruleName;
    }

    public String getRuleName() {
        return ruleName;
    }

    public List<TreeNode<T>> getChildren() {
        return children;
    }

    public void addChild(TreeNode<T> child) {
        children.add(child);
    }

    @Override
    public boolean isLeaf() {
        return children.isEmpty();
    }

    public String toString(String indent) {
        if (data instanceof Statement) {
            return ((Statement)data).toString(indent);
        } else if (data instanceof Declaration) {
//            return ((Declaration)data).toString(indent);

        }
        return data.toString();

    }
}