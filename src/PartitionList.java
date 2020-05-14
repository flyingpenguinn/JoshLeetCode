import base.ListNode;

/*
LC#86
Given a linked list and a value x, partition it such that all nodes less than x come before nodes greater than or equal to x.

You should preserve the original relative order of the nodes in each of the two partitions.

Example:

Input: head = 1->4->3->2->5->2, x = 3
Output: 1->2->2->4->3->5
 */
public class PartitionList {
    public ListNode partition(ListNode head, int x) {
        ListNode sm= new ListNode(-1);
        ListNode bg= new ListNode(-1);
        ListNode p= head;
        ListNode psm=sm;
        ListNode pbg=bg;

        while(p!=null){
            if(p.val<x){
                psm.next=p;
                psm=psm.next;
            }else{
                pbg.next=p;
                pbg=pbg.next;
            }
            p=p.next;
        }
        pbg.next=null;
        psm.next=bg.next;
        return sm.next;
    }
}
