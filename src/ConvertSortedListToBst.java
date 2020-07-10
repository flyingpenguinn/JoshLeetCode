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
    // if we traverse in order, then the visiting sequence happens to be the linked list sequence. This makes it an o(n) algorithm
    /*
    The invariance that we maintain in this algorithm is that whenever we are done building the left half of the BST, the head pointer in the linked list will point to the root node or the middle node (which becomes the root).
    So, we simply use the current value pointed to by head as the root node and progress the head node by once i.e. head = head.next
     */
    private ListNode node = null;

    public TreeNode sortedListToBST(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode p = head;
        int count = 1;
        while (p.next != null) {
            p = p.next;
            count++;
        }
        node = head;
        return inorder(0, count - 1);
    }

    private TreeNode inorder(int l, int u) {
        if (l > u) {
            return null;
        }
        int mid = l + (u - l) / 2;
        TreeNode left = inorder(l, mid - 1);
        TreeNode cur = new TreeNode(node.val);
        node = node.next;
        TreeNode right = inorder(mid + 1, u);
        cur.left = left;
        cur.right = right;
        return cur;
    }
}
