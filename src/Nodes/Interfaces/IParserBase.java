package Parsing;

import main.MyLangParser;
import org.antlr.v4.runtime.tree.ParseTree;

public interface IParserBase<T> {
    public T parse(ParseTree tree, MyLangParser parser);
}
