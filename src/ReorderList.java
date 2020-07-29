import base.ListNode;
import base.Lists;

import java.util.ArrayDeque;
import java.util.Deque;

public class ReorderList {
    public void reorderList(ListNode head) {
        if (head == null || head.next == null) {
            return;
        }
        ListNode p = head;
        ListNode q = head;
        while (p != null && p.next != null) {
            p = p.next.next;
            q = q.next;
        }
        // q is at the breaking point, reverse from q.next
        p = q.next;
        q.next = null;
        ListNode pre = reverse(p);
        // now pre and head are two parts that needs to be merged
        merge(head, pre);
    }

    protected ListNode reverse(ListNode p) {
        ListNode pre = null;
        while (p != null) {
            ListNode pnext = p.next;
            p.next = pre;
            pre = p;
            p = pnext;
        }
        return pre;
    }

    private ListNode merge(ListNode head1, ListNode head2) {
        ListNode dummy = new ListNode(-1);
        int round = 0;
        ListNode p = head1;
        ListNode q = head2;
        ListNode rp = dummy;
        while (p != null && q != null) {
            if (round == 0) {
                rp.next = p;
                p = p.next;
            } else {
                rp.next = q;
                q = q.next;
            }
            rp = rp.next;
            round ^= 1;
        }
        if (p != null) {
            rp.next = p;
        } else {
            rp.next = q;
        }
        return dummy.next;
    }
}