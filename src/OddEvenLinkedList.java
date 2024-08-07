import base.ListNode;

/*
LC#328
Given a singly linked list, group all odd nodes together followed by the even nodes. Please note here we are talking about the node number and not the value in the nodes.

You should try to do it in place. The program should run in O(1) space complexity and O(nodes) time complexity.

Example 1:

Input: 1->2->3->4->5->NULL
Output: 1->3->5->2->4->NULL
Example 2:

Input: 2->1->3->5->6->4->7->NULL
Output: 2->3->6->7->1->5->4->NULL
Note:

The relative order inside both the even and odd groups should remain as it was in the input.
The first node is considered odd, the second node even and so on ...
 */
public class OddEvenLinkedList {
    public ListNode oddEvenList(ListNode head) {
        ListNode odd = new ListNode(-1);
        ListNode even = new ListNode(-2);
        ListNode oddhead = odd;
        ListNode evenhead = even;
        int c = 1;
        ListNode p = head;
        while (p != null) {
            if (c % 2 == 1) {
                odd.next = p;
                odd = p;
            } else {
                even.next = p;
                even = p;
            }
            p = p.next;
            c++;
        }
        even.next = null;  // must terminate the list!
        odd.next = evenhead.next;
        return oddhead.next;
    }
}
