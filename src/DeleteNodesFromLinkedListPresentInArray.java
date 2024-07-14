import base.ListNode;

import java.util.HashSet;
import java.util.Set;

public class DeleteNodesFromLinkedListPresentInArray {
    public ListNode modifiedList(int[] a, ListNode head) {
        Set<Integer> set = new HashSet<>();
        for (int ai : a) {
            set.add(ai);
        }
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode p = dummy;
        while (p.next != null) {
            ListNode pn = p.next;
            if (set.contains(pn.val)) {
                p.next = pn.next;
            } else {
                p = p.next;
            }
        }
        return dummy.next;
    }
}
