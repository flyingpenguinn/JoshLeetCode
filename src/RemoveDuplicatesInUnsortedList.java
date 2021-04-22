import base.ListNode;

import java.util.HashMap;
import java.util.Map;

public class RemoveDuplicatesInUnsortedList {
    public ListNode deleteDuplicatesUnsorted(ListNode head) {
        Map<Integer, Integer> m = new HashMap<>();
        ListNode p = head;
        while (p != null) {
            m.put(p.val, m.getOrDefault(p.val, 0) + 1);
            p = p.next;
        }
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        p = dummy;
        while (p != null && p.next != null) {
            // invariant: p is safe, p.next is undeMIr check. can't change this to if because p.next can still be wrong so p will be on wrong one
            while (p.next != null && m.get(p.next.val) > 1) {
                p.next = p.next.next;
            }
            p = p.next;
        }
        return dummy.next;
    }
}
