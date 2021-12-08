import base.ListNode;

public class DeleteMidleNodeOfLinkedList {
    public ListNode deleteMiddle(ListNode head) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode p = head;
        ListNode q = dummy;
        while(p!= null && p.next != null){
            p = p.next.next;
            q = q.next;
        }
        q.next = q.next.next;
        return dummy.next;
    }
}
