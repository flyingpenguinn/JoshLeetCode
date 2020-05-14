import base.ListNode;
import base.TreeNode;

/*
LC#109
Given a singly linked list where elements are sorted in ascending order, convert it to a height balanced BST.

For this problem, a height-balanced binary tree is defined as a binary tree in which the depth of the two subtrees of every node never differ by more than 1.

Example:

Given the sorted linked list: [-10,-3,0,5,9],

One possible answer is: [0,-3,9,-10,null,5], which represents the following height balanced BST:

      0
     / \
   -3   9
   /   /
 -10  5
 */
public class ConvertSortedListToBst {
    // use two pointers dont need to get len. Onlgn
    ListNode dummy = new ListNode(-1);

    public TreeNode sortedListToBST(ListNode head) {
        return dos(head);
    }

    TreeNode dos(ListNode head) {
        if (head == null) {
            return null;
        }
        if (head.next == null) {
            return new TreeNode(head.val);
        }
        dummy.next = head;
        ListNode fast = dummy.next;//!!!
        ListNode slow = dummy;
        //slow.next points to middle
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }

        ListNode nh = slow.next;
        slow.next = null;
        TreeNode left = dos(head);
        TreeNode right = dos(nh.next);

        TreeNode root = new TreeNode(nh.val);
        root.left = left;
        root.right = right;
        return root;
    }
}
