package Nodes.Interfaces;

import org.antlr.v4.runtime.tree.ParseTree;

public interface IParsable<T> {
    public T parse(ParseTree tree);
}
