package AST;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import Lexical_analyzer.Token;
import Lexical_analyzer.TokenType;

public class AbstractSyntaxTree {

    private final List<Token> tokens;
    private Node root;
    HashMap<Token, String> childrenErrors = new HashMap<>();

    public AbstractSyntaxTree(List<Token> tokens) {
        this.tokens = tokens;
        root = breakElements(this.tokens);
        int height = getHeight(root) - 1;

        for (int i = 0; i < height; i++) {
            checkElements(root);
        }

        System.out.println(this);

        if (childrenErrors.size() == 0) {
            while (root.data.code == TokenType.IDENTIFIER || root.data.code == TokenType.KEY_WORD) {
                buildElements(root);
            }
        } else {
            for (String str : childrenErrors.values()) {
                if (!str.equalsIgnoreCase("null")) {
                    System.out.println(str);
                }
            }
        }

        System.out.println(root.data.sourceValue);
    }

    private Node breakElements(List<Token> tokens) {
        tokens.remove(0);
        tokens.remove(tokens.size() - 1);
        Node parent = new Node();
        int status = -1;
        ArrayList<ArrayList<Token>> children = new ArrayList<>();
        Stack<Token> parentheses = new Stack<>();

        for (Token t : tokens) {
            if (children.size() == status) {
                children.add(new ArrayList<>());
            }

            if (t.sourceValue.equalsIgnoreCase("(")) {
                parentheses.add(t);
            } else if (t.sourceValue.equalsIgnoreCase(")")) {
                parentheses.pop();

                if (status == -1) {
                    parent.data = t;
                } else {
                    children.get(status).add(t);
                }

                if (parentheses.isEmpty()) {
                    status++;
                }
                continue;
            }

            if (parentheses.isEmpty()) {
                if (status == -1) {
                    parent.data = t;
                } else {
                    children.get(status).add(t);
                }
                status++;
            } else {
                if (status == -1) {
                    parent.data = t;
                } else {
                    children.get(status).add(t);
                }
            }
        }

        for (ArrayList<Token> child : children) {
            if (child.get(0).sourceValue.equalsIgnoreCase("(")) {
                Node nextNode = breakElements(child);
                nextNode.parent = parent;
                parent.children.add(nextNode);
            } else {
                Node nextNode = new Node();
                nextNode.data = child.get(0);
                nextNode.parent = parent;
                parent.children.add(nextNode);
            }

        }

        return parent;
    }

    public void checkElements(Node node) {
        if (node.parent != null) {
            if (node.parent.data.sourceValue.equalsIgnoreCase("setq")
                    && node == node.parent.children.get(0)) {
                return;
            }
        }

        ArrayList<Token> children = new ArrayList<>();
        for (Node child : node.children) {
            children.add(child.data);
        }

        checkFunction(node, children, node.children);

        if (node.function != null) {
            if (!node.function.childrenCondition()) {
                node.error = true;
            }

            for (HashMap.Entry<Token, String> entry : node.function.childrenErrors().entrySet()) {
                int index = children.indexOf(entry.getKey());
                if (index != -1) {
                    node.children.get(index).error = true;
                }

                childrenErrors.put(node.data, entry.getValue());
            }
        }

        if (!node.children.isEmpty()) {
            for (Node child : node.children) {
                if (child.data.code == TokenType.IDENTIFIER) {
                    checkElements(child);
                }
            }
        }
    }

    public void buildElements(Node node) {
        if (node.data.code == TokenType.KEY_WORD
                && node.data.sourceValue == null) {
            return;
        }

        if (node.parent != null) {
            if (node.parent.data.sourceValue.equalsIgnoreCase("setq")
                    && node == node.parent.children.get(0)) {
                return;
            }
        }

        if (node.data.code == TokenType.INTEGER
                || node.data.code == TokenType.BOOL
                || node.data.code == TokenType.REAL) {
            return;
        }

        ArrayList<Token> children = new ArrayList<>();
        for (Node child : node.children) {
            children.add(child.data);
        }

        setFunction(node, children, node.children);

        boolean containsIdentifier = false;
        for (Node child : node.children) {
            if (child.data.code == TokenType.IDENTIFIER && !Globals.atoms.containsKey(child.data.sourceValue)) {
                containsIdentifier = true;
            }
        }

        if (!containsIdentifier) {
            if (node.function != null) {
                node.data.sourceValue = node.function.returnValue();
                node.data.code = node.function.returnType();
            }
            node.children = new ArrayList<>();
        }

        if (!node.children.isEmpty()) {
            for (Node child : node.children) {
                if (child.data.code == TokenType.IDENTIFIER) {
                    buildElements(child);
                }
            }
        }
    }

    void checkFunction(Node node, ArrayList<Token> children, ArrayList<Node> children1) {
        switch (node.data.sourceValue) {
            case ("plus"):
                node.function = new Plus(node.data, children);
                break;
            case ("minus"):
                node.function = new Minus(node.data, children);
                break;
            case ("times"):
                node.function = new Times(node.data, children);
                break;
            case ("divide"):
                node.function = new Divide(node.data, children);
                break;
            case ("equal"):
                node.function = new Equal(node.data, children);
                break;
            case ("nonequal"):
                node.function = new Nonequal(node.data, children);
                break;
            case ("less"):
                node.function = new Less(node.data, children);
                break;
            case ("lesseq"):
                node.function = new Lesseq(node.data, children);
                break;
            case ("greater"):
                node.function = new Greater(node.data, children);
                break;
            case ("greatereq"):
                node.function = new Greatereq(node.data, children);
                break;
            case ("cond"):
                node.function = new Cond(node.data, children1);
                break;
            case ("while"):
                node.function = new While(node.data, children1);
                break;
            case ("setq"):
                node.function = new Setq(node.data, children1);
                break;
            case ("print"):
                node.function = new Print(node.data, children1);
                break;
            default:
                if (!Globals.atoms.containsKey(node.data.sourceValue)) {
                    node.function = new UnknownFunction(node.data);
                }
                break;
        }

    }

    void setFunction(Node node, ArrayList<Token> children, ArrayList<Node> children1) {
        switch (node.data.sourceValue) {
            case ("plus"):
                node.function = new Plus(node.data, children);
                node.finished = true;
                break;
            case ("minus"):
                node.function = new Minus(node.data, children);
                node.finished = true;
                break;
            case ("times"):
                node.function = new Times(node.data, children);
                node.finished = true;
                break;
            case ("divide"):
                node.function = new Divide(node.data, children);
                node.finished = true;
                break;
            case ("equal"):
                node.function = new Equal(node.data, children);
                node.finished = true;
                break;
            case ("nonequal"):
                node.function = new Nonequal(node.data, children);
                node.finished = true;
                break;
            case ("less"):
                node.function = new Less(node.data, children);
                node.finished = true;
                break;
            case ("lesseq"):
                node.function = new Lesseq(node.data, children);
                node.finished = true;
                break;
            case ("greater"):
                node.function = new Greater(node.data, children);
                node.finished = true;
                break;
            case ("greatereq"):
                node.function = new Greatereq(node.data, children);
                node.finished = true;
                break;
            case ("cond"):
                node.function = new Cond(node.data, children1);
                node.finished = true;
                break;
            case ("while"):
                node.function = new While(node.data, children1);
                Node condition = children1.get(0).clone();
                condition.parent = node.clone();
                ArrayList<Node> whileResults = new ArrayList<>();

                for (int i = 1; i < children1.size(); i++) {
                    whileResults.add(children1.get(i).clone());
                    whileResults.get(i - 1).parent = node.clone();
                }

                while (condition.data.code == TokenType.IDENTIFIER || condition.data.code == TokenType.KEY_WORD) {
                    buildElements(condition);
                }

                if (condition.data.sourceValue.equalsIgnoreCase("true")) {
                    for (Node n : whileResults) {
                        buildElements(n);
                    }
                    setFunction(node, children, children1);
                }

                node.data.sourceValue = "null";
                node.data.code = TokenType.NULL;

                break;
            case ("setq"):
                node.function = new Setq(node.data, children1);
                Node result = children1.get(1).clone();
                result.parent = node.clone();

                while (result.data.code == TokenType.IDENTIFIER || result.data.code == TokenType.KEY_WORD) {
                    buildElements(result);
                }

                Globals.atoms.put(
                        children1.get(0).data.sourceValue,
                        result.data
                );
                node.finished = true;
                break;
            case ("print"):
                node.function = new Setq(node.data, children1);
                Node printResult = children1.get(0).clone();
                printResult.parent = node.clone();

                while (printResult.data.code == TokenType.IDENTIFIER || printResult.data.code == TokenType.KEY_WORD) {
                    if (Globals.atoms.containsKey(printResult.data.sourceValue)) {
                        try (FileWriter writer = new FileWriter("src/com/company/output.txt", false)) {
                            writer.write(Globals.atoms.get(printResult.data.sourceValue).sourceValue);
                            writer.flush();
                        } catch (IOException ex) {
                            System.out.println(ex.getMessage());
                        }
                        break;
                    }
                    buildElements(printResult);
                }

                if (!(printResult.data.code == TokenType.IDENTIFIER || printResult.data.code == TokenType.KEY_WORD)) {
                    try (FileWriter writer = new FileWriter("src/com/company/output.txt", true)) {
                        writer.write(printResult.data.sourceValue);
                        writer.flush();
                    } catch (IOException ex) {
                        System.out.println(ex.getMessage());
                    }
                }

                node.data.sourceValue = "null";
                node.data.code = TokenType.NULL;
            default:
                if (!Globals.atoms.containsKey(node.data.sourceValue)) {
                    node.function = new UnknownFunction(node.data);
                }
                break;
        }

    }

    public String toString() {
        return root.toString();
    }

    public static class Node implements Cloneable {

        public Token data;
        public Node parent;
        public ArrayList<Node> children = new ArrayList<>();
        public Function function;
        public boolean finished = false;
        private boolean error = false;

        public Node clone() {
            Node copy = new Node();

            copy.data = new Token();
            copy.data.code = this.data.code;
            copy.data.sourceValue = this.data.sourceValue;
            copy.data.span = new Span();
            copy.data.span.positionBegin = this.data.span.positionBegin;
            copy.data.span.positionEnd = this.data.span.positionEnd;
            copy.data.span.lineNum = this.data.span.lineNum;

            copy.children = new ArrayList<>();
            for (Node child : this.children) {
                copy.children.add(child.clone());
            }
            for (Node child : copy.children) {
                child.parent = copy;
            }

            return copy;
        }

        public String toString() {
            StringBuilder buffer = new StringBuilder(50);
            print(buffer, "", "");
            return buffer.toString();
        }

        private void print(StringBuilder buffer, String prefix, String childrenPrefix) {
            buffer.append(prefix);
            buffer.append(error ? "\u001B[31m" : "").append(data.sourceValue).append("\u001B[0m");
            buffer.append('\n');
            for (Iterator<Node> it = children.iterator(); it.hasNext();) {
                Node next = it.next();
                if (it.hasNext()) {
                    next.print(buffer, childrenPrefix + "├── ", childrenPrefix + "│   ");
                } else {
                    next.print(buffer, childrenPrefix + "└── ", childrenPrefix + "    ");
                }
            }
        }
    }

    public int getHeight(Node node) {
        if (node != null) {
            int maxValue = 0;
            for (Node child : node.children) {
                int height = getHeight(child);
                if (height > maxValue) {
                    maxValue = height;
                }
            }
            return 1 + maxValue;
        } else {
            return 0;
        }
    }
}
