import base.TreeNode;

import java.util.ArrayList;
import java.util.List;

/*
LC#257
Given a binary tree, return all root-to-leaf paths.

Note: A leaf is a node with no children.

Example:

Input:

   1
 /   \
2     3
 \
  5

Output: ["1->2->5", "1->3"]

Explanation: All root-to-leaf paths are: 1->2->5, 1->3
 */
public class BinaryTreePaths {
    List<String> r = new ArrayList<>();

    public List<String> binaryTreePaths(TreeNode root) {
        dob(root, new StringBuilder());
        return r;
    }

    void dob(TreeNode n, StringBuilder cur) {
        if (n == null) {
            return;
        }
        int ol = cur.length();
        String toadd = cur.length() == 0 ? "" + n.val : "->" + n.val;
        cur.append(toadd);

        if (n.left == null && n.right == null) {
            r.add(cur.toString());
        } else {
            dob(n.left, cur);
            dob(n.right, cur);
        }
        cur.setLength(ol); //! set length
    }

}
