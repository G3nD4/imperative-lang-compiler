package main;

import java.util.ArrayList;
import java.util.List;

public class InternalNode<T> extends TreeNode<T> {
    public String ruleName;
    public T data;
    public List<TreeNode> children = new ArrayList<>();

    public InternalNode(String ruleName) {
        this.ruleName = ruleName;
    }

    public String getRuleName() {
        return ruleName;
    }

    public List<TreeNode> getChildren() {
        return children;
    }

    public void addChild(TreeNode child) {
        children.add(child);
    }

    @Override
    public boolean isLeaf() {
        return children.isEmpty();
    }
}