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
        ListNode dm = new ListNode(-1);
        dm.next = head;
        ListNode p = dm;
        ListNode pt = null;
        while (m > 0) {
            if (m == 1) {
                // remember prev tail
                pt = p;
            }
            p = p.next;
            m--;
            n--;
        }
        ListNode nt = p;
        ListNode q = p.next;
        while (n > 0) {
            ListNode qn = q.next;
            q.next = p;
            p = q;
            q = qn;
            n--;
        }
        // q is the next of last reverted node
        nt.next = q;
        pt.next = p;
        return dm.next;
    }

    public static void main(String[] args) {
        ListNode head = Lists.stringToListNode("1->2->3->4->5");
        ListNode nh = new ReverseLinkedListII().reverseBetween(head, 5, 5);
        Lists.printLinkedList(nh);
    }
}
