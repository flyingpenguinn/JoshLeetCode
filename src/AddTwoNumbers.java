
/* LC#2

You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.

You may assume the two numbers do not contain any leading zero, except the number 0 itself.

Example:

Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
Output: 7 -> 0 -> 8
Explanation: 342 + 465 = 807.

https://leetcode.com/problems/add-two-numbers/
 */

import base.ListNode;

public class AddTwoNumbers {

    // don't forget the carry and move p1 p2 themselves
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode p1 = l1;
        ListNode p2 = l2;
        ListNode dummy = new ListNode(-1);
        ListNode r = dummy;
        int carry = 0;
        while (p1 != null || p2 != null) {
            int raw = getval(p1) + getval(p2) + carry;
            carry = raw >= 10 ? 1 : 0;
            ListNode np = new ListNode(raw % 10);
            r.next = np;
            r = np;
            if (p1 != null) {
                p1 = p1.next;
            }
            if (p2 != null) {
                p2 = p2.next;
            }
        }
        if (carry != 0) {
            ListNode np = new ListNode(1);
            r.next = np;
        }
        return dummy.next;
    }

    int getval(ListNode l) {
        return l == null ? 0 : l.val;
    }
}
