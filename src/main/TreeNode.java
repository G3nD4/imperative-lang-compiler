package main;

import java.util.ArrayList;
import java.util.List;

public abstract class TreeNode<T> {
    public abstract boolean isLeaf();
    public T data;
    public abstract String toString(String indent);
}
