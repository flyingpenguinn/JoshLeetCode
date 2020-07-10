import java.util.ArrayList;
import java.util.List;

public class FindTheRootOfNrayTree {
    private class Node {
        public int val;
        public List<Node> children;

        public Node() {
            children = new ArrayList<Node>();
        }

        public Node(int _val) {
            val = _val;
            children = new ArrayList<Node>();
        }

        public Node(int _val, ArrayList<Node> _children) {
            val = _val;
            children = _children;
        }
    }

    public Node findRoot(List<Node> tree) {
        // all unique nodes
        if (tree == null) {
            return null;
        }
        long sum = 0;
        for (Node n : tree) {
            // and can be iterated once
            sum += n.val;
            // every node can be children once
            for (Node c : n.children) {
                sum -= c.val;
            }
        }
        for (Node n : tree) {
            if (n.val == sum) {
                return n;
            }
        }
        return null;
    }
}
