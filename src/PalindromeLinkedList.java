import base.ListNode;
import base.Lists;

/*
LC#234
Given a singly linked list, determine if it is a palindrome.

Example 1:

Input: 1->2
Output: false
Example 2:

Input: 1->2->2->1
Output: true
Follow up:
Could you do it in O(n) time and O(1) space?

Accepted
 */
public class PalindromeLinkedList {
    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }
        int len = getlen(head); // len>=2
        ListNode p = head;
        int i = 1;
        while (i < len / 2) {
            p = p.next;
            i++;
        }
        ListNode np = p.next;
        p.next = null;
        if (len % 2 == 1) {
            np = np.next; // 1->2->3, we need to land on 3
        }
        ListNode reversed = reverse(np);
        return same(reversed, head);
    }

    boolean same(ListNode n1, ListNode n2) {
        while (n1 != null && n2 != null) {
            if (n1.val != n2.val) {
                return false;
            }
            n1 = n1.next;
            n2 = n2.next;
        }
        return n1 == null && n2 == null;
    }

    ListNode reverse(ListNode p) {
        ListNode pre = null;
        while (p != null) {
            ListNode next = p.next;
            p.next = pre;
            pre = p;
            p = next;
        }
        return pre;
    }

    int getlen(ListNode head) {
        return head == null ? 0 : getlen(head.next) + 1;
    }

    public static void main(String[] args) {
        System.out.println(new PalindromeLinkedList().isPalindrome(Lists.stringToListNode("1->2->1")));
    }
}
