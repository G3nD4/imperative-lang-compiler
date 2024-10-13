package main;

import java.util.ArrayList;
import java.util.List;

abstract class TreeNode {
    public abstract boolean isLeaf();
}

class InternalNode extends TreeNode {
    private String ruleName;
    private List<TreeNode> children = new ArrayList<>();

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
        return false;
    }
}

class LeafNode extends TreeNode {
    private String value;

    public LeafNode(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean isLeaf() {
        return true;
    }
}
