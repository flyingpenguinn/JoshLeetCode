import base.ListNode;
import base.Lists;

/*
LC#92
Reverse a linked list from position m to n. Do it in one-pass.

Note: 1 ≤ m ≤ n ≤ length of list.

Example:

Input: 1->2->3->4->5->NULL, m = 2, n = 4
Output: 1->4->3->2->5->NULL
 */
public class ReverseLinkedListII {
    public ListNode reverseBetween(ListNode head, int m, int n) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode pretail = dummy;
        ListNode p = head;
        while (m > 1) {
            pretail = p;
            p = p.next;
            m--;
            n--;
        }
        // pretail is the tail of the first part. p is the first to be reversed
        ListNode reverseLast = p;
        ListNode pre = null;
        while (n > 0) {
            ListNode pnext = p.next;
            p.next = pre;
            pre = p;
            p = pnext;
            n--;
        }
        // pre is the head of the reversed. p is the head of the 3rd part
        pretail.next = pre;
        reverseLast.next = p;
        return dummy.next;
    }

    public static void main(String[] args) {
        ListNode head = Lists.stringToListNode("1->2->3->4->5");
        ListNode nh = new ReverseLinkedListII().reverseBetween(head, 5, 5);
        Lists.printLinkedList(nh);
    }
}
