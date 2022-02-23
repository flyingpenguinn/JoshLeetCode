import base.ListNode;

public class MergeNodesInbetweenZeros {
    public ListNode mergeNodes(ListNode head) {
        ListNode p = head.next;
        ListNode pre = head;
        ListNode dummy = new ListNode(-1);
        ListNode rp = dummy;
        while (p != null) {
            int sum = 0;
            while (p.val != 0) {
                sum += p.val;
                p = p.next;
            }
            ListNode nn = new ListNode(sum);
            rp.next = nn;
            rp = rp.next;
            pre = p;
            p = p.next;
        }
        return dummy.next;
    }
}
