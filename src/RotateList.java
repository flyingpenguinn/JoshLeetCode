import base.ListNode;
import base.Lists;

/*
LC#61
Given a linked list, rotate the list to the right by k places, where k is non-negative.

Example 1:

Input: 1->2->3->4->5->NULL, k = 2
Output: 4->5->1->2->3->NULL
Explanation:
rotate 1 steps to the right: 5->1->2->3->4->NULL
rotate 2 steps to the right: 4->5->1->2->3->NULL
Example 2:

Input: 0->1->2->NULL, k = 4
Output: 2->0->1->NULL
Explanation:
rotate 1 steps to the right: 2->0->1->NULL
rotate 2 steps to the right: 1->2->0->NULL
rotate 3 steps to the right: 0->1->2->NULL
rotate 4 steps to the right: 2->0->1->NULL
 */
public class RotateList {
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null) {
            return head;
        }
        int len = 0;
        ListNode p = head;
        ListNode tail = null;
        while (p != null) {
            len++;
            tail = p;
            p = p.next;
        }
        if (k % len == 0) {
            return head;
        }
        int rt = len - k % len;
        p = head;
        while (rt > 1) {
            p = p.next;
            rt--;
        }
        ListNode nh = p.next;
        p.next = null;
        tail.next = head;
        return nh;
    }

    public static void main(String[] args) {
        System.out.println(new RotateList().rotateRight(Lists.stringToListNode("0->1->2"), 4));
    }
}
