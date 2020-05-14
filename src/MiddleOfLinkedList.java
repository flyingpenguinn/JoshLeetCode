import base.ListNode;

public class MiddleOfLinkedList {
    public ListNode middleNode(ListNode head) {
        // slow and fast
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
}

class MiddeleOfListRecursive {
    public ListNode middleNode(ListNode head) {
        int len = getlen(head);

        ListNode node = getNode(head, len / 2);
        return node;
    }

    int getlen(ListNode head) {
        return head == null ? 0 : 1 + getlen(head.next);
    }

    ListNode getNode(ListNode p, int t) {
        if (t == 0) {
            return p;
        }
        return p == null ? null : getNode(p.next, t - 1);
    }
}
