import base.TreeNode;
import base.Trees;

import java.util.*;

/*
LC#1028
We run a preorder depth first search on the root of a binary tree.

At each node in this traversal, we output D dashes (where D is the depth of this node), then we output the value of this node.  (If the depth of a node is D, the depth of its immediate child is D+1.  The depth of the root node is 0.)

If a node has only one child, that child is guaranteed to be the left child.

Given the output S of this traversal, recover the tree and return its root.



Example 1:



Input: "1-2--3--4-5--6--7"
Output: [1,2,5,3,4,6,7]
Example 2:



Input: "1-2--3---4-5--6---7"
Output: [1,2,5,3,null,6,null,4,null,7]


Example 3:



Input: "1-401--349---90--88"
Output: [1,401,null,349,88,90]


Note:

The number of nodes in the original tree is between 1 and 1000.
Each node will have a value between 1 and 10^9.
 */
public class RecoverTreeFromPreOrder {
    // in pre order how do we nkow which parent to connect to? keep popping until the depth of the node is < this node
    class StackItem {
        TreeNode n;
        int d;

        public StackItem(TreeNode n, int d) {
            this.n = n;
            this.d = d;
        }
    }

    public TreeNode recoverFromPreorder(String s) {
        Deque<StackItem> st = new ArrayDeque<>();
        int i = 0;
        int n = s.length();
        TreeNode root = null;
        while (i < n) {
            int j = i;
            while (i < n && s.charAt(i) == '-') {
                i++;
            }
            int depth = i - j;
            int v = 0;
            while (i < n && s.charAt(i) != '-') {
                v = v * 10 + (s.charAt(i) - '0');
                i++;
            }
            while (!st.isEmpty() && st.peek().d >= depth) {
                st.pop();
            }
            TreeNode node = new TreeNode(v);
            if (st.isEmpty()) {
                root = node;
            } else {
                if (st.peek().n.left == null) {
                    st.peek().n.left = node;
                } else {
                    st.peek().n.right = node;
                }
            }
            st.push(new StackItem(node, depth));
        }
        return root;
    }

    public static void main(String[] args) {
        System.out.println(Trees.toString(new RecoverTreeFromPreOrder().recoverFromPreorder("1-401--349---90--88")));
    }
}
