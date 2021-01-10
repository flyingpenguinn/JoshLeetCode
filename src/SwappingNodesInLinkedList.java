import base.ListNode;

public class SwappingNodesInLinkedList {
    public ListNode swapNodes(ListNode head, int k) {
        int ok = k;
        ListNode p = head;
        while (k > 1) {
            p = p.next;
            k--;
        }
        ListNode n1 = p;
        ListNode q = head;
        k = ok;
        while (k > 1) {
            q = q.next;
            k--;
        }
        //   System.out.println(q.val);
        ListNode n2 = head;
        while (q.next != null) {
            q = q.next;
            n2 = n2.next;
        }
        int tmp = n1.val;
        n1.val = n2.val;
        n2.val = tmp;
        return head;
    }
}
