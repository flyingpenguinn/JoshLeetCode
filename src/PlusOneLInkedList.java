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
    // find right most non nine, add one and change all right to it to 0, like plus one
    public ListNode plusOne(ListNode head) {
        ListNode cand = null;
        ListNode p = head;
        // cand will be right most non 9 node
        while (p != null) {
            if (p.val != 9) {
                cand = p;
            }
            p = p.next;
        }
        if (cand == null) {
            ListNode nh = new ListNode(1);
            p = head;
            while (p != null) {
                p.val = 0;
                p = p.next;
            }
            nh.next = head;
            return nh;
        } else {
            cand.val++;
            p = cand.next;
            while (p != null) {
                p.val = 0;
                p = p.next;
            }
            return head;
        }
    }
}
