import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/*
LC#590
Given an n-ary tree, return the postorder traversal of its nodes' values.

Nary-Tree input serialization is represented in their level order traversal, each group of children is separated by the null value (See examples).



Follow up:

Recursive solution is trivial, could you do it iteratively?



Example 1:



Input: root = [1,null,3,2,4,null,5,6]
Output: [5,6,3,2,4,1]
Example 2:



Input: root = [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14]
Output: [2,6,14,11,7,3,12,8,4,13,9,10,5,1]


Constraints:

The height of the n-ary tree is less than or equal to 1000
The total number of nodes is between [0, 10^4]
 */
public class NrayTreePostOrderTraversal {
    class Node {
        public int val;
        public List<Node> children;

        public Node() {
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }

    class Item {
        Node n;
        boolean pushed = false;

        public Item(Node n, boolean p) {
            this.n = n;
            this.pushed = p;
        }
    }

    public List<Integer> postorder(Node root) {
        Deque<Item> stack = new LinkedList<>();
        // pushkids only pushed one layer so need a flag to tell when we see a node: whether we pushed its kids or not
        stack.push(new Item(root, false));
        List<Integer> r = new ArrayList<>();
        while (!stack.isEmpty()) {
            Item top = stack.pop();
            if (top.n != null) {
                if (!top.pushed) {
                    pushkids(top, stack);
                } else {
                    r.add(top.n.val);
                }
            }
        }
        return r;
    }

    void pushkids(Item ni, Deque<Item> stack) {
        // root visited the last so into stack first
        ni.pushed = true;
        stack.push(ni);

        for (int i = ni.n.children.size() - 1; i >= 0; i--) {
            stack.push(new Item(ni.n.children.get(i), false));
        }
    }
}
