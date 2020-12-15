import base.ListNode;

/*
LC#369
Given a non-negative integer represented as non-empty a singly linked list of digits, plus one to the integer.

You may assume the integer do not contain any leading zero, except the number 0 itself.

The digits are stored such that the most significant digit is at the head of the list.

Example :

Input: [1,2,3]
Output: [1,2,4]
 */
public class PlusOneLInkedList {
    // recursive solution
    public ListNode plusOne(ListNode head) {
        int carry = doplus(head);
        if (carry != 0) {
            ListNode nh = new ListNode(1);
            nh.next = head;
            return nh;
        }
        return head;
    }

    private int doplus(ListNode n) {
        if (n == null) {
            return 1;
        }
        int carry = doplus(n.next);
        int raw = n.val + carry;
        n.val = raw % 10;
        return raw / 10;
    }
}
