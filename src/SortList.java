import base.ListNode;

/*
LC#148
Sort a linked list in O(n log n) time using constant space complexity.

Example 1:

Input: 4->2->1->3
Output: 1->2->3->4
Example 2:

Input: -1->5->3->4->0
Output: -1->0->3->4->5
 */
public class SortList {
    // merge sort
    ListNode nh = new ListNode(-1);

    public ListNode sortList(ListNode head) {
        return dom(head);
    }

    private ListNode dom(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        // fast = head.next then fast 2 steps, slow one step. this is the way to split the list to halves
        ListNode fast = head.next;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        ListNode sn = slow.next;
        slow.next = null;
        ListNode l1 = dom(head);
        ListNode l2 = dom(sn);
        return merge(l1, l2);
    }

    private ListNode merge(ListNode l1, ListNode l2) {
        ListNode pn = nh;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                pn.next = l1;
                l1 = l1.next;
                pn = pn.next;
            } else {
                pn.next = l2;
                l2 = l2.next;
                pn = pn.next;
            }
        }
        if (l1 != null) {
            pn.next = l1;
        }
        if (l2 != null) {
            pn.next = l2;
        }
        ListNode rt = nh.next;
        nh.next = null;
        return rt;
    }
}
