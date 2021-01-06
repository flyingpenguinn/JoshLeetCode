import base.ListNode;

/*
LC#83
Given a sorted linked list, delete all duplicates such that each element appear only once.

Example 1:

Input: 1->1->2
Output: 1->2
Example 2:

Input: 1->1->2->3->3
Output: 1->2->3
 */
public class RemoveDuplicatesFromSortedList {
    public ListNode deleteDuplicates(ListNode head) {
        ListNode dummy = new ListNode(-1);
        ListNode q = dummy;
        ListNode p = head;
        while(p!= null){
            q.next = p;
            q = q.next;
            int v = p.val;
            while(p!= null && p.val == v){
                p = p.next;
            }
        }
        q.next = null;
        return dummy.next;
    }
}
