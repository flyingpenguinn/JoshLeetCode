import base.ListNode;

/*
LC#19
Given a linked list, remove the n-th node from the end of list and return its head.

Example:

Given linked list: 1->2->3->4->5, and n = 2.

After removing the second node from the end, the linked list becomes 1->2->3->5.
Note:

Given n will always be valid.

Follow up:

Could you do this in one pass?
 */
public class RemoveNthNodeFromEnd {
    // similar to get nth from back,but use .next.next
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode fast = dummy;
        while (n > 1) {
            if (fast.next == null) {
                throw new IllegalArgumentException();
            }
            fast = fast.next;
            n--;
        }
        ListNode slow = dummy;
        while (fast.next.next != null) {
            fast = fast.next;
            slow = slow.next;
        }
        slow.next = slow.next.next;
        return dummy.next;
    }
}
