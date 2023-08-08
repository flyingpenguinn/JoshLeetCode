import base.ListNode;

public class InertGcdInLinkedList {
    public ListNode insertGreatestCommonDivisors(ListNode head) {
        ListNode p = head;
        while (p != null && p.next != null) {
            ListNode pn = p.next;
            int gcd = gcd(p.val, p.next.val);
            ListNode gn = new ListNode(gcd);
            p.next = gn;
            gn.next = pn;
            p = pn;
        }
        return head;
    }

    private int gcd(int a, int b) {
        if (a < b) {
            return gcd(b, a);
        }
        return b == 0 ? a : gcd(b, a % b);
    }
}
