import java.util.ArrayList;
import java.util.List;


public class MoveSubtreeOfNrayTree {
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

        public Node(int _val, ArrayList<Node> _child) {
            val = _val;
            children = _child;
        }
    }

    // make p a child of q. note if q==p's father then we shouldnt do the cust paste because it changes orders.
    // also when we let q to replace p, should also take care of orcer
    public Node moveSubTree(Node root, Node p, Node q) {
        if (inSubTree(p, q)) {
            return case1(root, p, q);
        } else {
            return case23(root, p, q);
        }
    }

    // q in p's subtree
    private boolean inSubTree(Node p, Node q) {
        if (p == null) {
            return false;
        }
        if (p == q) {
            return true;
        }
        for (Node ch : p.children) {
            if (inSubTree(ch, q)) {
                return true;
            }
        }
        return false;
    }

    private Node findFather(Node root, Node p, Node parent) {
        if (root == null) {
            return null;
        }
        if (root == p) {
            return parent;
        }
        for (Node ch : root.children) {
            Node rt = findFather(ch, p, root);
            if (rt != null) {
                return rt;
            }
        }
        return null;
    }

    // q in p subtree. note p and q are not the same
    private Node case1(Node root, Node p, Node q) {
        Node pfather = findFather(root, p, null);
        Node qfather = findFather(root, q, null);
        qfather.children.remove(q);
        q.children.add(p);
        if (root == p) { // pfather would be null then
            return q;
        } else {
            int oldp = pfather.children.indexOf(p);
            pfather.children.remove(oldp);
            pfather.children.add(oldp, q);
            return root;
        }
    }

    private Node case23(Node root, Node p, Node q) {
        Node pfather = findFather(root, p, null);
        if (q != pfather) {
            pfather.children.remove(p);
            q.children.add(p);
        }
        return root;
    }
}
