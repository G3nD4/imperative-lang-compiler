package Nodes.Interfaces;

import main.antlrTree.MyLangParser;
import org.antlr.v4.runtime.tree.ParseTree;

public interface IParserBase<T> {
    public T parse(ParseTree tree, MyLangParser parser);
}
