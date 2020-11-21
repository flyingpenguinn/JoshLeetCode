import base.TreeNode;
import base.Trees;

import java.util.HashMap;
import java.util.Map;

/*
LC#968
Given a binary tree, we install cameras on the nodes of the tree.

Each camera at a node can monitor its parent, itself, and its immediate children.

Calculate the minimum number of cameras needed to monitor all nodes of the tree.



Example 1:


Input: [0,0,null,0,0]
Output: 1
Explanation: One camera is enough to monitor all nodes if placed as shown.
Example 2:


Input: [0,0,null,0,null,0,null,null,0]
Output: 2
Explanation: At least two cameras are needed to monitor all nodes of the tree. The above image shows one of the valid configurations of camera placement.

Note:

The number of nodes in the given tree will be in the range [1, 1000].
Every node has value 0.
 */

public class BinaryTreeCameras {
    // vertex cover in binary tree, with a twist on status maintaining.
    private int Max = 10000000;
    private Map<TreeNode, Map<Integer, Integer>> dp;

    public int minCameraCover(TreeNode root) {
        dp = new HashMap<>();
        return domin(root, 1);
    }

    // st==0: parent has camera: we can either put, or no
    // st==1: parent doesnt have camera but has cover by either grand parent or one of the children. we either put or no
    // st==2: parent relies on this node to cover
    private int domin(TreeNode n, int st) {
        if (n == null) {
            return st == 2 ? Max : 0;
        }
        Map<Integer, Integer> cm = dp.getOrDefault(n, new HashMap<>());
        Integer ch = cm.get(st);
        if (ch != null) {
            return ch;
        }
        int put = 1 + domin(n.left, 0) + domin(n.right, 0);
        int noput = Max;
        if (st == 1) {
            // to cover this node, either put on left, or right
            int onleft = domin(n.left, 2) + domin(n.right, 1);
            int onright = domin(n.right, 2) + domin(n.left, 1);
            noput = Math.min(onleft, onright);
        } else if (st == 0) {
            // using parent to cover this node, but we are not putting anything here, so 1
            noput = domin(n.left, 1) + domin(n.right, 1);
        }
        int cur = Math.min(put, noput);
        cm.put(st, cur);
        dp.put(n, cm);
        return cur;
    }
}
