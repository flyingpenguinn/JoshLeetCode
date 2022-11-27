import base.ListNode;

import java.util.ArrayDeque;
import java.util.Deque;

public class RemoveNodesFromLinkedList {
    public ListNode removeNodes(ListNode head) {
        Deque<ListNode> dq = new ArrayDeque<>();
        ListNode nh = new ListNode((int) (1e9));
        nh.next = head;
        dq.push(nh);
        ListNode p = head;
        while (p != null) {
            while (!dq.isEmpty() && dq.peek().val < p.val) {
                dq.pop();
            }
            dq.peek().next = p;
            dq.push(p);
            p = p.next;
        }
        dq.peek().next = null;
        return nh.next;
    }
}
