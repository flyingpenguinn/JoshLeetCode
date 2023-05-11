import base.ListNode;

public class SplitCircularList {
    public ListNode[] splitCircularLinkedList(ListNode head) {
        ListNode p = head;
        int len = 1;
        while(p.next != head){
            p = p.next;
            ++len;
        }
        ListNode tail = p;
        // p is the last node
        int mid = (int)Math.ceil(len*1.0/2);
        p = head;
        while(mid>1){
            p = p.next;
            --mid;
        }
        ListNode head2 = p.next;
        p.next = head;
        tail.next = head2;
        return new ListNode[]{head, head2};
        // p is the last node of the first half

    }
}
