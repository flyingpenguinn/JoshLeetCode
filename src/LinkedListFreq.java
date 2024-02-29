import base.ListNode;

import java.util.HashMap;
import java.util.Map;

public class LinkedListFreq {
    public ListNode frequenciesOfElements(ListNode head) {
        ListNode p = head;
        Map<Integer, Integer> m = new HashMap<>();
        while (p != null) {
            m.put(p.val, m.getOrDefault(p.val, 0) + 1);
            p = p.next;
        }
        p = head;
        ListNode dummy = new ListNode(-1);
        ListNode res = dummy;
        while (p != null) {
            int freq = m.get(p.val);
            ListNode q = p;
            while (q != null && p.val == q.val) {
                q = q.next;
            }
            res.next = new ListNode(freq);
            res = res.next;
            p = q;
        }
        return dummy.next;
    }
}
