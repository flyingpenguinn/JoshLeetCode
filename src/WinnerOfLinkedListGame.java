import base.ListNode;

public class WinnerOfLinkedListGame {
    public String gameResult(ListNode head) {
        ListNode p = head;
        int odd = 0;
        int even = 0;
        while (p != null) {
            ListNode next = p.next;
            int v1 = p.val;
            int v2 = next.val;
            if (v1 > v2) {
                ++even;
            } else {
                ++odd;
            }
            p = next.next;
        }
        if (odd > even) {
            return "Odd";
        } else if (even > odd) {
            return "Even";
        } else {
            return "Tie";
        }
    }
}
