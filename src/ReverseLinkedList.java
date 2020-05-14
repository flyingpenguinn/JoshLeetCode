import base.ListNode;

/*
LC#206
Reverse a singly linked list.

Example:

Input: 1->2->3->4->5->NULL
Output: 5->4->3->2->1->NULL
Follow up:

A linked list can be reversed either iteratively or recursively. Could you implement both?
 */
public class ReverseLinkedList {
    public ListNode reverseList(ListNode head) {
        ListNode pre = null;
        ListNode p = head;
        while (p != null) {
            ListNode np = p.next;
            p.next = pre;
            pre = p;
            p = np;
        }
        return pre;
    }
}

class ReverseLinkedListRecursion {
    public ListNode reverseList(ListNode head) {
        return dor(head)[0];
    }

    ListNode[] dor(ListNode n) {
        if (n == null || n.next == null) {
            return new ListNode[]{n, n};
        }
        ListNode[] rn = dor(n.next);
        n.next = null;
        rn[1].next = n;
        rn[1] = n; // dont forget this
        return rn;
    }
}