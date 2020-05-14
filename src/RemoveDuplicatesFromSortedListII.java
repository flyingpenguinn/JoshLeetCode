import base.ListNode;

/*
LC#82
Given a sorted linked list, delete all nodes that have duplicate numbers, leaving only distinct numbers from the original list.

Example 1:

Input: 1->2->3->3->4->4->5
Output: 1->2->5
Example 2:

Input: 1->1->1->2->3
Output: 2->3
 */
public class RemoveDuplicatesFromSortedListII {
    public ListNode deleteDuplicates(ListNode head) {
        ListNode dm = new ListNode(-1);
        dm.next = head;
        ListNode p = dm;
        ListNode q = head;
        // q: next candidate.p: last known good one
        while (q != null) {
            ListNode k = q.next;
            while (k != null && k.val == q.val) {
                k = k.next;
            }
            if (k == q.next) {// no duplication
                p.next = q;
                p = p.next; // move p too!
            }
            q = k;
        }
        p.next = null; // cut short
        return dm.next;
    }
}
