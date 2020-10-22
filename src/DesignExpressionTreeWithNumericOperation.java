import java.util.ArrayDeque;
import java.util.Deque;

public class DesignExpressionTreeWithNumericOperation {

    abstract class Node {

        public abstract int evaluate();

        protected String val;
        protected Node left = null;
        protected Node right = null;
        // define your fields here
    }

    ;

    class NodeNumber extends Node {
        public NodeNumber(String val) {
            this.val = val;
        }

        public int evaluate() {
            return Integer.valueOf(val);
        }
    }

    ;

    class NodeOp extends Node {
        public NodeOp(String val) {
            this.val = val;
        }

        public int evaluate() {
            if ("+".equals(val)) {
                return left.evaluate() + right.evaluate();
            } else if ("-".equals(val)) {
                return left.evaluate() - right.evaluate();
            } else if ("*".equals(val)) {
                return left.evaluate() * right.evaluate();
            } else {
                return left.evaluate() / right.evaluate();
            }
        }
    }

    ;


    /**
     * This is the TreeBuilder class.
     * You can treat it as the driver code that takes the postinfix input
     * and returns the expression tree represnting it as a Node.
     */

    class TreeBuilder {
        private boolean isop(String s) {
            return "+".equals(s) || "-".equals(s) || "*".equals(s) || "/".equals(s);
        }

        public Node buildTree(String[] a) {
            Deque<Node> st = new ArrayDeque<>();
            for (int i = 0; i < a.length; i++) {
                if (isop(a[i])) {
                    Node cur = new NodeOp(a[i]);
                    cur.right = st.pop();
                    cur.left = st.pop();
                    st.push(cur);
                } else {
                    st.push(new NodeNumber(a[i]));
                }
            }
            return st.pop();
        }

    }

    ;
}
