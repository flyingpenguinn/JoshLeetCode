import base.ListNode;
import base.TreeNode;

import java.util.HashMap;
import java.util.Map;

/*
LC#1367
Given a binary tree root and a linked list with head as the first node.

Return True if all the elements in the linked list starting from the head correspond to some downward path connected in the binary tree otherwise return False.

In this context downward path means a path that starts at some node and goes downwards.



Example 1:



Input: head = [4,2,8], root = [1,4,4,null,2,2,null,1,null,6,8,null,null,null,null,1,3]
Output: true
Explanation: Nodes in blue form a subpath in the binary Tree.
Example 2:



Input: head = [1,4,2,6], root = [1,4,4,null,2,2,null,1,null,6,8,null,null,null,null,1,3]
Output: true
Example 3:

Input: head = [1,4,2,6,8], root = [1,4,4,null,2,2,null,1,null,6,8,null,null,null,null,1,3]
Output: false
Explanation: There is no path in the binary tree that contains all the elements of the linked list from head.


Constraints:

1 <= node.val <= 100 for each node in the linked list and binary tree.
The given linked list will contain between 1 and 100 nodes.
The given binary tree will contain between 1 and 2500 nodes.
 */
public class LinkedListInBinaryTree {
    // similar to check if a tree is a contained in another tree: fix the root then rest must equal.
    // if we nerge the two dfs we will have to dp
    public boolean isSubPath(ListNode head, TreeNode root) {
        if (head == null) {
            return true;
        }
        if (root == null) {
            return false;
        }
        boolean rt = doi(root, head);
        if (rt) {
            return true;
        } else {
            return isSubPath(head, root.left) || isSubPath(head, root.right);
        }
    }

    private boolean doi(TreeNode root, ListNode head) {
        if (root == null && head == null) {
            return true;
        }
        if (head == null) {
            return true;
        }
        if (root == null) {
            return false;
        }
        if (root.val == head.val) {
            boolean rt = doi(root.left, head.next) || doi(root.right, head.next);
            if (rt) {
                return true;
            }
        }
        return false;
    }
}

class LinkedListBinaryTreeDp {
    Map<TreeNode, Map<ListNode, Boolean>> dp = new HashMap<>();

    public boolean isSubPath(ListNode head, TreeNode root) {
        if (head == null) {
            return false;
        }
        return doi(root, head, head);
    }

    private boolean doi(TreeNode root, ListNode head, ListNode oldhead) {
        if (root == null && head == null) {
            return true;
        }
        if (head == null) {
            return true;
        }
        if (root == null) {
            return false;
        }
        Map<ListNode, Boolean> cm = dp.getOrDefault(root, new HashMap<>());
        Boolean ch = cm.get(head);
        if (ch != null) {
            return ch;
        }
        if (root.val == head.val) {
            boolean rt = doi(root.left, head.next, oldhead) || doi(root.right, head.next, oldhead);
            if (rt) {
                cm.put(head, true);
                dp.put(root, cm);
                return true;
            }
        }
        boolean rt = doi(root.left, oldhead, oldhead) || doi(root.right, oldhead, oldhead);
        cm.put(head, rt);
        dp.put(root, cm);
        return rt;
    }
}
