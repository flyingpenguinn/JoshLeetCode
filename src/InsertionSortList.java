import base.ListNode;

/*
LC#147
Sort a linked list using insertion sort.


A graphical example of insertion sort. The partial sorted list (black) initially contains only the first element in the list.
With each iteration one element (red) is removed from the input data and inserted in-place into the sorted list


Algorithm of Insertion Sort:

Insertion sort iterates, consuming one input element each repetition, and growing a sorted output list.
At each iteration, insertion sort removes one element from the input data, finds the location it belongs within the sorted list, and inserts it there.
It repeats until no input elements remain.

Example 1:

Input: 4->2->1->3
Output: 1->2->3->4
Example 2:

Input: -1->5->3->4->0
Output: -1->0->3->4->5
 */
public class InsertionSortList {
    public ListNode insertionSortList(ListNode head) {
        ListNode dummy = new ListNode(-1); // dummy.next starts the sorted list
        ListNode tosort = head;
        while (tosort != null) {
            ListNode nextsort = tosort.next;
            tosort.next = null;
            ListNode q = dummy;
            while (q.next != null && q.next.val < tosort.val) {
                q = q.next;
            }
            ListNode qn = q.next;
            q.next = tosort;
            tosort.next = qn;
            tosort = nextsort;
        }
        return dummy.next;
    }
}
