import java.util.ArrayDeque;
import java.util.Deque;

/*
LC#1265
You are given an immutable linked list, print out all values of each node in reverse with the help of the following interface:

ImmutableListNode: An interface of immutable linked list, you are given the head of the list.
You need to use the following functions to access the linked list (you can't access the ImmutableListNode directly):

ImmutableListNode.printValue(): Print value of the current node.
ImmutableListNode.getNext(): Return the next node.
The input is only given to initialize the linked list internally. You must solve this problem without modifying the linked list. In other words, you must operate the linked list using only the mentioned APIs.



Follow up:

Could you solve this problem in:

Constant space complexity?
Linear time complexity and less than linear space complexity?


Example 1:

Input: head = [1,2,3,4]
Output: [4,3,2,1]
Example 2:

Input: head = [0,-4,-1,3,-5]
Output: [-5,3,-1,-4,0]
Example 3:

Input: head = [-2,0,6,4,4,-6]
Output: [-6,4,4,6,0,-2]


Constraints:

The length of the linked list is between [1, 1000].
The value of each node in the linked list is between [-1000, 1000].
 */
public class ImmutableListReversal {
    // cut to sqrt pieces and reverse one by one. note we dont need to reset the next pointers just printing
    interface ImmutableListNode {
        public void printValue(); // print the value of this node.

        public ImmutableListNode getNext(); // return the next node.
    }

    static class ImmutableListNodeImpl implements ImmutableListNode {

        int val;

        public ImmutableListNodeImpl(int val) {
            this.val = val;
        }

        ImmutableListNode next;

        @Override
        public void printValue() {
            System.out.print(val + ",");
        }

        @Override
        public ImmutableListNode getNext() {
            return next;
        }
    }

    public void printLinkedListInReverse(ImmutableListNode head) {
        ImmutableListNode p = head;
        int len = 0;
        while (p != null) {
            len++;
            p = p.getNext();
        }
        int seglen = (int) Math.sqrt(len);
        int seg = (int) Math.ceil(len * 1.0 / seglen);
        Deque<ImmutableListNode> stack = new ArrayDeque<>();
        p = head;
        for (int i = 0; i < seg; i++) {
            if (p != null) { // in case there is a stub
                stack.push(p);
                for (int j = 1; j <= seglen && p != null; j++) {
                    p = p.getNext();
                }
            }
        }
        while (!stack.isEmpty()) {
            printreversed(stack.pop(), seglen);
        }
    }

    private void printreversed(ImmutableListNode p, int seglen) {
        Deque<ImmutableListNode> stack = new ArrayDeque<>();
        while (seglen > 0 && p != null) { // in case seglen too big there is a stub
            stack.push(p);
            p = p.getNext();
            seglen--;
        }
        while (!stack.isEmpty()) {
            stack.pop().printValue();
        }
    }

    public static void main(String[] args) {
        ImmutableListNodeImpl n1 = new ImmutableListNodeImpl(1);
        ImmutableListNodeImpl n2 = new ImmutableListNodeImpl(2);
        ImmutableListNodeImpl n3 = new ImmutableListNodeImpl(3);
        ImmutableListNodeImpl n4 = new ImmutableListNodeImpl(4);
        ImmutableListNodeImpl n5 = new ImmutableListNodeImpl(5);
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;
        new ImmutableListReversal().printLinkedListInReverse(n1);
    }
}
