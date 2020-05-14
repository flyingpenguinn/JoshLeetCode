import base.ListNode;

import java.util.Random;

/*
LC#382
Given a singly linked list, return a random node's value from the linked list. Each node must have the same probability of being chosen.

Follow up:
What if the linked list is extremely large and its length is unknown to you? Could you solve this efficiently without using extra space?

Example:

// Init a singly linked list [1,2,3].
ListNode head = new ListNode(1);
head.next = new ListNode(2);
head.next.next = new ListNode(3);
Solution solution = new Solution(head);

// getRandom() should return either 1, 2, or 3 randomly. Each element should have equal probability of returning.
solution.getRandom();
 */
public class LinkedListRandomNode {
    // reservoir sampling. first pick the head k. for i it has k/(k+i) probability to be chosen. here k==1
    private class Solution {
        ListNode head = null;

        public Solution(ListNode head) {
            this.head = head;
        }

        Random rand = new Random();

        public int getRandom() {
            ListNode p = head;
            int r = -1;
            int cur = 0;
            while (p != null) {
                cur++;
                int ran = rand.nextInt(cur);
                if (ran == 0) {
                    r = p.val;
                }
                p = p.next;
            }
            return r;
        }
    }
}
