import base.ListNode;

/*
Given a linked list, reverse the nodes of a linked list k at a time and return its modified list.

k is a positive integer and is less than or equal to the length of the linked list. If the number of nodes is not a multiple of k then left-out nodes in the end should remain as it is.

Example:

Given this linked list: 1->2->3->4->5

For k = 2, you should return: 2->1->4->3->5

For k = 3, you should return: 3->2->1->4->5

Note:

Only constant extra memory is allowed.
You may not alter the values in the list's nodes, only nodes itself may be changed.
 */
public class ReverseNodesInKGroup {
    // concat cur head to last tail
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode ptail = new ListNode(-1);
        ListNode dummy = ptail;
        ListNode p = head;
        ListNode q = head;
        // reverse q to p
        while (q != null) {
            int movek = k;
            while (p != null && movek > 1) {
                p = p.next;
                movek--;
            }
            if (p == null) {
                // not long enough,keep the same
                ptail.next = q;
                break;
            }
            // reverse q to p
            ListNode pre = null;
            ListNode oldq = q;
            // pnext is going to be overridden...so need a placeholder
            ListNode nextstart = p.next;
            while (q != nextstart) {
                ListNode qn = q.next;
                q.next = pre;
                pre = q;
                q = qn;
            }
            ptail.next = pre;
            q = nextstart;
            p = nextstart;
            ptail = oldq;
        }
        return dummy.next;
    }
}
