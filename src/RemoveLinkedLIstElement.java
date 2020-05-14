import base.ListNode;

/*
LC#203
Remove all elements from a linked list of integers that have value val.

Example:

Input:  1->2->6->3->4->5->6, val = 6
Output: 1->2->3->4->5
 */
public class RemoveLinkedLIstElement {
    public ListNode removeElements(ListNode head, int val) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode p = dummy;
        ListNode q = head;
        while (q != null) {
            if (q.val != val) {
                p.next = q;
                p = p.next;
            }
            q = q.next;
        }
        p.next = null; // dont forget this to cut the last node off
        return dummy.next;
    }
}
