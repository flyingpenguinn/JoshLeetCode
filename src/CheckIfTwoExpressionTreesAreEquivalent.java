import java.util.ArrayList;
import java.util.Collections;
import java.util.List;



public class CheckIfTwoExpressionTreesAreEquivalent {

    class Node {
        char val;
        Node left;
        Node right;
        Node() {this.val = ' ';}
        Node(char val) { this.val = val; }
        Node(char val, Node left, Node right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }


    public boolean checkEquivalence(Node root1, Node root2) {
        List<Character> l1 = dfs(root1);
        List<Character> l2 = dfs(root2);
        Collections.sort(l1);
        Collections.sort(l2);
        return l1.equals(l2);
    }

    private List<Character> dfs(Node n) {
        if (n == null) {
            return new ArrayList<>();
        }
        if (n.val == '+') {
            List<Character> res = dfs(n.left);
            res.addAll(dfs(n.right));
            return res;
        } else {
            List<Character> cur = new ArrayList<>();
            cur.add(n.val);
            return cur;
        }
    }
}
