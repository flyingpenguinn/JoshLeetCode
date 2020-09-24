import java.util.ArrayDeque;
import java.util.Deque;

public class BuildBinaryExpressionTreeGivenInfix {

    private class Node {
        char val;
        Node left;
        Node right;

        Node() {
            this.val = ' ';
        }

        Node(char val) {
            this.val = val;
        }

        Node(char val, Node left, Node right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    // almost the same as basical calculator II we just push nodes here rather than numbers
    public Node expTree(String s) {
        Deque<Node> nodes = new ArrayDeque<>();
        Deque<Character> op = new ArrayDeque<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                nodes.push(new Node(c));
            } else if (c == '(') {
                op.push(c);
            } else if (c == ')') {
                collapseUntil(nodes, op, '(');
            } else {
                collapse(nodes, op, c);
            }
        }
        collapseAll(nodes, op);
        return nodes.pop();
    }

    private boolean shouldCollapse(Deque<Character> op, char c) {
        if (op.isEmpty()) {
            return false;
        }
        if (op.peek() == '(') {
            return false;
        }
        if ((op.peek() == '+' || op.peek() == '-') && (c == '*' || c == '/')) {
            return false;
        }
        return true;
    }

    private void collapseUntil(Deque<Node> nodes, Deque<Character> op, char until) {
        while (op.peek() != until) {
            doCollapse(nodes, op);
        }
        op.pop(); // pop up the left bracket
    }

    private void collapse(Deque<Node> nodes, Deque<Character> op, char c) {
        while (shouldCollapse(op, c)) {
            // need a while because if it's 1+2*3*4*5 + 6 and we are at the last +then all the calculations before it can be collapsed
            doCollapse(nodes, op);
        }
        op.push(c);
    }

    private void collapseAll(Deque<Node> nodes, Deque<Character> op) {
        while (nodes.size() > 1) {
            doCollapse(nodes, op);
        }
    }

    private void doCollapse(Deque<Node> nodes, Deque<Character> op) {
        Node n1 = nodes.pop();
        Node n2 = nodes.pop();
        Node cur = new Node(op.pop());
        cur.left = n2;
        cur.right = n1;
        nodes.push(cur);
    }
}

class BinaryExpressionTreeOn2 {
    private class Node {
        char val;
        Node left;
        Node right;

        Node() {
            this.val = ' ';
        }

        Node(char val) {
            this.val = val;
        }

        Node(char val, Node left, Node right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    // TLE when it's all * together...
    public Node expTree(String s) {
        return dfs(s, 0, s.length() - 1);
    }

    private Node dfs(String s, int l, int u) {
        if (l > u) {
            return null;
        }
        if (l == u) {// only possibility is single char
            return new Node(s.charAt(l));
        }

        Node cur = null;
        int level = 0;
        int pos1 = -1;
        int pos2 = -1;
        for (int i = u; i >= l; i--) {
            char c = s.charAt(i);
            if ((c == '+' || c == '-') && level == 0) {
                pos1 = i;
                break;
            } else if ((c == '*' || c == '/') && level == 0) {
                pos2 = i;
            } else if (c == '(') {
                level--;
            } else if (c == ')') {
                level++;
            }
        }
        int pos = pos1 != -1 ? pos1 : pos2;
        if (pos == -1) {
            // only exp is all in ()
            return dfs(s, l + 1, u - 1);
        }
        cur = new Node(s.charAt(pos));
        Node left = dfs(s, l, pos - 1);
        Node right = dfs(s, pos + 1, u);
        cur.left = left;
        cur.right = right;
        return cur;
    }

    private boolean isop(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }
}