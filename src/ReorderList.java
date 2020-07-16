import base.ListNode;
import base.Lists;

import java.util.ArrayDeque;
import java.util.Deque;

public class ReorderList {
    public void reorderList(ListNode head) {
        // find middle, cut and reverse, then merge
        if (head == null || head.next == null) {
            return;
        }
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        // this trick will land us on 3 when it's 5 nodes, or 4 when it's 6 nodes
        ListNode tail2 = slow.next;
        slow.next = null;
        ListNode head2 = reverse(tail2);
        ListNode mergeTail = head;
        ListNode head1 = head.next;
        int i = 1;
        while (head1 != null && head2 != null) {
            if (i % 2 == 0) {
                mergeTail.next = head1;
                head1 = head1.next;
            } else {
                mergeTail.next = head2;
                head2 = head2.next;
            }
            mergeTail = mergeTail.next;
            i++;
        }
        if (head1 != null) {
            mergeTail.next = head1;
        }
    }

    private ListNode reverse(ListNode head) {
        ListNode pre = null;
        ListNode p = head;
        while (p != null) {
            ListNode next = p.next;
            p.next = pre;
            pre = p;
            p = next;
        }
        return pre;
    }
}