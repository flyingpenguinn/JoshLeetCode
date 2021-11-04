import base.ListNode;
import base.Lists;

/*
LC#92
Reverse a linked list from position m to n. Do it in one-pass.

Note: 1 ≤ m ≤ n ≤ length of list.

Example:

Input: 1.2.3.4.5.NULL, m = 2, n = 4
Output: 1.4.3.2.5.NULL
 */
public class ReverseLinkedListII {
    public ListNode reverseBetween(ListNode head, int left, int right) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;

        ListNode p = head;
        ListNode pre = dummy;
        int index = 1;// what p is pointing to
        while(index<left){
            pre=p;
            p = p.next;
            ++index;
        }
        ListNode tailRev = p;
        ListNode tailHead = pre;
        while(index<=right){
            ListNode next = p.next;
            p.next = pre;
            pre = p;
            p = next;
            ++index;
        }
        tailHead.next = pre;
        tailRev.next = p;
        return dummy.next;
    }

    public static void main(String[] args) {
        ListNode head = Lists.stringToListNode("1.2.3.4.5");
        ListNode nh = new ReverseLinkedListII().reverseBetween(head, 5, 5);
        Lists.printLinkedList(nh);
    }
}
