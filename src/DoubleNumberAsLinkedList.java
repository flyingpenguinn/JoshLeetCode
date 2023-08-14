import base.ListNode;

public class DoubleNumberAsLinkedList {
    public ListNode doubleIt(ListNode head) {
        int ov = solve(head);
        if (ov > 0) {
            ListNode nv = new ListNode(ov);
            nv.next = head;
            return nv;
        }
        return head;

    }

    private int solve(ListNode n) {
        if (n == null) {
            return 0;
        }
        int cr = solve(n.next);
        int rv = n.val * 2 + cr;
        n.val = rv % 10;
        int ncr = rv / 10;
        return ncr;

    }
}
