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
        ListNode dummy = new ListNode(-1);
        ListNode newHead = head;
        ListNode lastTail = dummy;
        while (true) {
            ListNode p = newHead;
            int rem = k;
            while (p != null && rem > 0) {
                p = p.next;
                --rem;
            }
            if (rem > 0) {
                lastTail.next = newHead;
                //lastTail pointing to null
                break;
            }
            ListNode pre = null;
            p = newHead;
            rem = k;
            while (rem > 0) {
                ListNode next = p.next;
                p.next = pre;
                pre = p;
                p = next;
                --rem;
            }
            lastTail.next = pre;
            lastTail = newHead;
            newHead = p;
        }
        return dummy.next;
    }
}
