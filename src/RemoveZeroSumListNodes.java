import base.ListNode;
import base.Lists;

import java.util.HashMap;
import java.util.Map;

public class RemoveZeroSumListNodes {
    public ListNode removeZeroSumSublists(ListNode head) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode p = dummy;

        // listnode form prefix sum = key
        Map<Integer, ListNode> summap = new HashMap<>();
        summap.put(0, dummy);
        int sum = 0;
        while (p.next != null) {
            ListNode pn = p.next;
            sum += pn.val;
            ListNode prev = summap.get(sum);
            if (prev != null) {
                // remove nodes from prev.next to pn, inclusive
                prev.next = pn.next;
            } else {
                summap.put(sum, pn);
            }
            // prev might == p
            p = pn;
        }
        return dummy.next;
    }

    public static void main(String[] args) {
        Lists.printLinkedList(new RemoveZeroSumListNodes().removeZeroSumSublists(Lists.stringToListNode("2->0")));
    }
}

class RemoveZerosumListNodesBruteForce {
    public ListNode removeZeroSumSublists(ListNode head) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode p = dummy;
        while (p.next != null) {
            ListNode pn = p.next;
            int sum = 0;
            boolean found = false;
            while (pn != null) {
                sum += pn.val;
                if (sum == 0) {
                    p.next = pn.next;
                    found = true;
                    break;
                }
                pn = pn.next;
            }
            if (!found) {
                p = p.next;
            }
        }
        return dummy.next;
    }
}
