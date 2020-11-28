import base.ListNode;

public class MergeInbetweenLinkedList {
    public ListNode mergeInBetween(ListNode l1, int a, int b, ListNode l2) {
        int step = 0;
        ListNode p = l1;
        while (step < a - 1) {
            p = p.next;
            step++;
        }
        ListNode pa = p;
        // put after pa
        while (step < b + 1) {
            p = p.next;
            step++;
        }
        pa.next = l2;
        ListNode p2 = l2;
        while (p2.next != null) {
            p2 = p2.next;
        }
        p2.next = p;
        return l1;
    }
}
