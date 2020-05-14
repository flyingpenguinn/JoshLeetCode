import base.ListNode;

public class DeleteNodeInLinkedList {
    public void deleteNode(ListNode node) {
        if(node.next==null){
            return;
        }
        node.val= node.next.val;
        ListNode next=node.next;
        node.next=next.next;
        next.next=null;
    }
}
