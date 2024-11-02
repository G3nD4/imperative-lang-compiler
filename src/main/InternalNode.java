package main;

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
}