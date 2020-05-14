import base.ListNode;
import base.Lists;

import java.util.ArrayDeque;
import java.util.Deque;

public class ReorderList {
    public static void main(String[] args) {
        ListNode head = Lists.stringToListNode("1");
        new ReorderList().reorderList(head);
        Lists.printLinkedList(head);
    }

    public void reorderList(ListNode head) {
        if (head == null) {
            return;
        }
        int len = getLen(head);
        ListNode mid = gotoN(head, (len + 1) / 2);
        ListNode midNext = mid.next;
        mid.next = null;
        ListNode reversed = reverse(midNext);
        merge(head, reversed);
    }

    private void merge(ListNode head1, ListNode head2) {
        // merge head2 to head1, in alternating sequence. head2 is shorter than or equal to head1
        ListNode p1 = head1;
        ListNode p2 = head2;
        while (p2 != null) {
            // p2 is exhausted first in our algo
            ListNode oldp1next = p1.next;
            ListNode oldp2next = p2.next;
            p1.next = p2;
            p2.next = oldp1next;
            p2 = oldp2next;
            p1 = oldp1next;
        }

    }

    private ListNode reverse(ListNode head) {
        ListNode prev = null;
        ListNode p = head;
        while (p != null) {
            ListNode next = p.next;
            p.next = prev;
            prev = p;
            p = next;
        }
        //new head is prev
        return prev;
    }

    // get nth node. n <= the length
    private ListNode gotoN(ListNode head, int n) {
        ListNode p = head;
        int count = 1;
        while (count < n && p != null) {
            p = p.next;
            count++;
        }
        return p;
    }

    private int getLen(ListNode head) {
        int len = 0;
        ListNode p = head;
        while (p != null) {
            len += 1;
            p = p.next;
        }
        return len;
    }
}

class ReorderListStack {
    public void reorderList(ListNode head) {
        Deque<ListNode> stack = new ArrayDeque<>();
        ListNode p = head;
        int size = 0;
        while (p != null) {
            size++;
            stack.push(p);
            p = p.next;
        }
        ListNode nh = new ListNode(-1);
        ListNode pn = nh;
        p = head;
        int i = 0;
        while (i < size) {
            if (i % 2 == 0) {
                pn.next = p;
                p = p.next;
            } else {
                pn.next = stack.pop();
            }
            pn = pn.next;
            i++;
        }
        pn.next = null;  // dont miss the last step to close it off
    }
}