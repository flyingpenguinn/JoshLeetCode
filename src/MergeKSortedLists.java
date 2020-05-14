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

    // O nlogk
    public ListNode mergeKLists(ListNode[] lists) {
        // value and which list it came from, sort by value itself. if certain value is the smallest take the next from the list
        PriorityQueue<int[]> heap = new PriorityQueue<>(new Comparator<int[]>() {

            @Override
            public int compare(int[] o1, int[] o2) {
                return Integer.compare(o1[0], o2[0]);
            }
        });
        int k = lists.length;
        ListNode dummy = new ListNode(-1);
        ListNode pr = dummy;
        for (int i = 0; i < k; i++) {
            if (lists[i] != null) {
                heap.offer(new int[]{lists[i].val, i});
                lists[i] = lists[i].next;
            }
        }
        while (!heap.isEmpty()) {
            int[] top = heap.poll();
            pr.next = new ListNode(top[0]);
            pr = pr.next;
            int from = top[1];
            if (lists[from] != null) {
                heap.offer(new int[]{lists[from].val, from});
                lists[from] = lists[from].next;
            }
        }
        return dummy.next;
    }
}

class MergeKSortedListsDivideConquer {
    public ListNode mergeKLists(ListNode[] lists) {
        int interval = 1;
        // 0 and 1 into 0, 1 and 2 into 1...etc
        // then 0 and 2 into 0, 4 and 6 into 4, etc
        int k = lists.length;
        while (interval < k) {
            // logk steps each step merge all numbers so nlogk in all
            for (int i = 0; i + interval < k; i += 2 * interval) {
                System.out.println(i + " " + (i + interval));
                merge(lists, i, i + interval);
            }
            interval *= 2;
        }
        return lists[0];
    }

    private void merge(ListNode[] lists, int i, int j) {
        ListNode r = new ListNode(-1);
        ListNode pr = r;
        ListNode p = lists[i];
        ListNode q = lists[j];
        while (p != null && q != null) {
            if (p.val < q.val) {
                pr.next = p;
                p = p.next;
            } else {
                pr.next = q;
                q = q.next;
            }
            pr = pr.next;
        }
        while (p != null) {
            pr.next = p;
            p = p.next;
            pr = pr.next;
        }
        while (q != null) {
            pr.next = q;
            q = q.next;
            pr = pr.next;
        }
        lists[i] = r.next;
    }
}
