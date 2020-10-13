import base.ListNode;

/*
LC#148
Sort a linked list in O(n log n) time using constant space complexity.

Example 1:

Input: 4->2->1->3
Output: 1->2->3->4
Example 2:

Input: -1->5->3->4->0
Output: -1->0->3->4->5
 */
public class SortList {
    // merge sort
    private ListNode dummy = new ListNode(-1);

    public ListNode sortList(ListNode head) {
        int len = getlen(head);
        if(len<=1){
            return head;
        }
        return mergeSort(head, len);
    }

    private int getlen(ListNode p){
        int res = 0;
        while(p != null){
            res++;
            p = p.next;
        }
        return res;
    }

    private ListNode mergeSort(ListNode p1, int len){
        if(len<=1){
            return p1;
        }
        int mid = len/2-1;
        ListNode p = p1;
        while(mid>0){
            p = p.next;
            mid--;
        }
        ListNode p2 = p.next;
        p.next  = null;
        ListNode sp1 = mergeSort(p1, len/2);
        ListNode sp2 = mergeSort(p2, len-len/2);
        p = dummy;
        while(sp1!= null && sp2 != null){
            if(sp1.val < sp2.val){
                p.next = sp1;
                sp1 = sp1.next;
            }else{
                p.next = sp2;
                sp2 = sp2.next;
            }
            p = p.next;
        }
        if(sp1 != null){
            p.next= sp1;
        }
        if(sp2 != null){
            p.next = sp2;
        }
        ListNode rt = dummy.next;
        dummy.next = null;
        return rt;
    }
}
