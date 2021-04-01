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
        int len = getlen(head);
        if(len<=1){
            return true;
        }
        int half = len/2;
        ListNode p = head;
        while(half>0){
            p = p.next;
            half--;
        }
        if(len%2==1){
            p = p.next;
        }
        ListNode head2 = reverse(p);
        return same(head, head2);
    }

    private ListNode reverse(ListNode p){
        ListNode pre = null;
        while(p!= null){
            ListNode next = p.next;
            p.next = pre;
            pre = p;
            p = next;
        }
        return pre;
    }

    private boolean same(ListNode p1, ListNode p2){
        while(p1 != null && p2 != null){
            if(p1.val != p2.val){
                return false;
            }
            p1 = p1.next;
            p2 = p2.next;
        }
        return true;
    }

    private int getlen(ListNode h){
        return h==null? 0: 1+getlen(h.next);
    }

    public static void main(String[] args) {
        System.out.println(new PalindromeLinkedList().isPalindrome(Lists.stringToListNode("1->2->1")));
    }
}
