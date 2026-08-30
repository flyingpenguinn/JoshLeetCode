import base.ListNode;

public class DeleteNNodesAfterMNodes {
    public ListNode deleteNodes(ListNode head, int m, int n) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode p = dummy;
        while(p.next != null){
            int i = 0;
            while(p.next!= null && i<m){ p = p.next; i++; }
            int j = 0;
            while(p.next != null && j<n){
                ListNode pn = p.next;
                ListNode pnn = pn.next;
                p.next = pnn;
                j++;
            }
        }
        return dummy.next;
    }
}
