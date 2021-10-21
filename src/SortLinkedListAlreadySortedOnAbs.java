import base.ListNode;

public class SortLinkedListAlreadySortedOnAbs {
    public ListNode sortLinkedList(ListNode head) {
        ListNode head1 = new ListNode(-1);
        ListNode head2 = new ListNode(-1);
        ListNode tail1 = head1;
        ListNode tail2 = head2;
        ListNode p = head;
        while(p!= null){
            ListNode next = p.next;
            p.next = null;
            if(p.val>=0){
                tail2.next = p;
                tail2 = p;
            }else{
                ListNode oldhead = head1.next ;
                p.next = oldhead;
                head1.next = p;
                if(tail1==head1){
                    tail1 = p;
                }
            }
            p = next;
        }
        tail1.next = head2.next;
        return head1.next;
    }
}
