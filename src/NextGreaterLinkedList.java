import base.ListNode;

import java.util.ArrayDeque;
import java.util.Deque;

public class NextGreaterLinkedList {
    class StackItem {
        int val;
        int index;

        StackItem(int val, int index) {
            this.val = val;
            this.index = index;
        }

        public String toString() {
            return val + "," + index;
        }
    }

    public int[] nextLargerNodes(ListNode head) {
        int len = getlen(head);
        int[] r = new int[len];
        Deque<StackItem> stack = new ArrayDeque<>();
        ListNode p = head;
        int ind = 0;
        while (p != null) {
            //  System.out.println(stack);
            if (stack.isEmpty()) {
                stack.push(new StackItem(p.val, ind));
            } else {
                while (!stack.isEmpty() && stack.peek().val < p.val) {
                    int indsmall = stack.pop().index;
                    r[indsmall] = p.val;
                }
                stack.push(new StackItem(p.val, ind));
            }
            p = p.next;
            ind++;
        }
        return r;
    }

    private int getlen(ListNode head) {
        return head == null ? 0 : 1 + getlen(head.next);
    }
}
