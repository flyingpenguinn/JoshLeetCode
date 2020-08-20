import base.ListNode;
import base.Lists;

import java.util.ArrayDeque;
import java.util.Deque;

public class ReorderList {
    public void reorderList(ListNode head) {
        if (head == null || head.next == null) {
            return;
        }
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        ListNode h2 = slow.next;
        slow.next = null;
        h2 = reverse(h2);
        merge(head, h2);
    }

    private ListNode reverse(ListNode head) {
        ListNode pre = null;
        ListNode p = head;
        while (p != null) {
            ListNode pnext = p.next;
            p.next = pre;
            pre = p;
            p = pnext;
        }
        return pre;
    }

    private void merge(ListNode h1, ListNode h2) {
        ListNode dummy = new ListNode(-1);
        ListNode p = dummy;
        boolean from1 = true;
        while (h1 != null && h2 != null) {
            if (from1) {
                p.next = h1;
                h1 = h1.next;
            } else {
                p.next = h2;
                h2 = h2.next;
            }
            p = p.next;
            from1 = !from1;
        }
        if (h1 != null) {
            p.next = h1;
        }
        if (h2 != null) {
            p.next = h2;
        }
    }
}