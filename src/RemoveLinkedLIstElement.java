import base.ListNode;

/*
LC#203
Remove all elements from a linked list of integers that have value val.

Example:

Input:  1->2->6->3->4->5->6, val = 6
Output: 1->2->3->4->5
 */
public class RemoveLinkedLIstElement {
    public ListNode removeElements(ListNode head, int val) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode p = dummy;
        // up to p good. now checking p.next
        while(p.next != null){
            if(p.next.val == val){
                p.next = p.next.next;
                // cant move p. we want to check the new pnn
            }else{
                p = p.next;
            }
        }
        return dummy.next;
    }
}
