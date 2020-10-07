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
    // check null! check null! check null!
    // use fast/slow to locate new tail and old tail
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null) {
            return null;
        }
        int len = getlen(head);
        k %= len;
        if (k == 0) {
            return head;
        }
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode fast = dummy;
        while (k > 0) {
            fast = fast.next;
            k--;
        }
        ListNode slow = dummy;
        while (fast.next != null) {
            slow = slow.next;
            fast = fast.next;
        }
        ListNode nh = slow.next;
        dummy.next = nh;
        fast.next = head;
        slow.next = null;
        return dummy.next;
    }

    private int getlen(ListNode head) {
        int res = 0;
        while (head != null) {
            res++;
            head = head.next;
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new RotateList().rotateRight(Lists.stringToListNode("0->1->2"), 4));
    }
}
