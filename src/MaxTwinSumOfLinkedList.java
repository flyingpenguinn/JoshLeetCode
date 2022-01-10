import base.ListNode;

public class MaxTwinSumOfLinkedList {
    public int pairSum(ListNode head) {
        if (head == null) {
            return 0;
        }
        ListNode p = head;
        ListNode q = head;
        ListNode pre = null;
        while (p != null && p.next != null) {
            p = p.next.next;
            ListNode qn = q.next;
            q.next = pre;
            pre = q;
            q = qn;
        }
        p = pre;
        int res = 0;
        while (p != null && q != null) {
            int cur = p.val + q.val;
            res = Math.max(res, cur);
            p = p.next;
            q = q.next;
        }
        return res;
    }
}
