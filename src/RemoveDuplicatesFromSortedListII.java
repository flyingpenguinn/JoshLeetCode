import base.ListNode;

/*
LC#82
Given a sorted linked list, delete all nodes that have duplicate numbers, leaving only distinct numbers from the original list.

Example 1:

Input: 1->2->3->3->4->4->5
Output: 1->2->5
Example 2:

Input: 1->1->1->2->3
Output: 2->3
 */
public class RemoveDuplicatesFromSortedListII {
    public ListNode deleteDuplicates(ListNode head) {
        ListNode dummy = new ListNode(-1);
        ListNode p = head;
        ListNode q = dummy;
        while(p != null){
            // p is a new distinct value
            int v = p.val;
            ListNode cur = p;
            int dup = 0;
            while(p!= null && p.val == v){
                dup++;
                p = p.next;
            }
            if(dup==1){
                q.next = cur;
                q = q.next;
            }
        }
        q.next = null;
        return dummy.next;
    }
}
