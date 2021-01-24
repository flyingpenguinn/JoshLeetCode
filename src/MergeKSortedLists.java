import base.ListNode;

import java.util.Comparator;
import java.util.PriorityQueue;

/*
LC#23

Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity.

Example:

Input:
[
  1->4->5,
  1->3->4,
  2->6
]
Output: 1->1->2->3->4->4->5->6

 */
public class MergeKSortedLists {

    // O nlogk. note we dont really need to remember which index: just need to store pointer
    public ListNode mergeKLists(ListNode[] lists) {
        // whcih list, index in the list, value
        PriorityQueue<ListNode> pq = new PriorityQueue<>((x,y) -> Integer.compare(x.val, y.val));
        for(ListNode l: lists){
            if(l!= null){
                pq.offer(l);
            }
        }
        ListNode dummy = new ListNode(-1);
        ListNode p = dummy;
        while(!pq.isEmpty()){
            ListNode cur = pq.poll();
            if(cur.next != null){
                pq.offer(cur.next);
            }
            p.next = cur;
            p = cur;
            cur.next = null;
        }
        return dummy.next;
    }
}

class MergeKSortedListsDivideConquer {
    // Onlogk because there are k lists
    public ListNode mergeKLists(ListNode[] lists) {
        return domerge(lists, 0, lists.length - 1);
    }

    ListNode domerge(ListNode[] lists, int l, int u) {
        if (l > u) {
            return null;
        }
        if (l == u) {
            return lists[l];
        }
        int mid = l + (u - l) / 2;
        ListNode l1 = domerge(lists, l, mid);
        ListNode l2 = domerge(lists, mid + 1, u);
        return mergelist(l1, l2);
    }

    ListNode mergelist(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(-1);
        ListNode rp = head;
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                rp.next = l1;
                l1 = l1.next;
            } else {
                rp.next = l2;
                l2 = l2.next;
            }
            rp = rp.next;
        }
        while (l1 != null) {
            rp.next = l1;
            l1 = l1.next;
            rp = rp.next;
        }
        while (l2 != null) {
            rp.next = l2;
            l2 = l2.next;
            rp = rp.next;
        }
        return head.next;
    }
}
