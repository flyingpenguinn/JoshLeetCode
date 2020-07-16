/*
LC#430
You are given a doubly linked list which in addition to the next and previous pointers, it could have a child pointer, which may or may not point to a separate doubly linked list. These child lists may have one or more children of their own, and so on, to produce a multilevel data structure, as shown in the example below.

Flatten the list so that all the nodes appear in a single-level, doubly linked list. You are given the head of the first level of the list.



Example 1:

Input: head = [1,2,3,4,5,6,null,null,null,7,8,9,10,null,null,11,12]
Output: [1,2,3,7,8,11,12,9,10,4,5,6]
Explanation:

The multilevel linked list in the input is as follows:



After flattening the multilevel linked list it becomes:


Example 2:

Input: head = [1,2,null,3]
Output: [1,3,2]
Explanation:

The input multilevel linked list is as follows:

  1---2---NULL
  |
  3---NULL
Example 3:

Input: head = []
Output: []


How multilevel linked list is represented in test case:

We use the multilevel linked list from Example 1 above:

 1---2---3---4---5---6--NULL
         |
         7---8---9---10--NULL
             |
             11--12--NULL
The serialization of each level is as follows:

[1,2,3,4,5,6,null]
[7,8,9,10,null]
[11,12,null]
To serialize all levels together we will add nulls in each level to signify no node connects to the upper node of the previous level. The serialization becomes:

[1,2,3,4,5,6,null]
[null,null,7,8,9,10,null]
[null,11,12,null]
Merging the serialization of each level and removing trailing nulls we obtain:

[1,2,3,4,5,6,null,null,null,7,8,9,10,null,null,11,12]


Constraints:

Number of Nodes will not exceed 1000.
1 <= Node.val <= 10^5
 */
public class FlattenMultiDoubleList {
    static class Node {
        public int val;
        public Node prev;
        public Node next;
        public Node child;

        public Node() {
        }

        public Node(int _val, Node _prev, Node _next, Node _child) {
            val = _val;
            prev = _prev;
            next = _next;
            child = _child;
        }
    }

    // pure recursion no need to keep "last" node
    public Node flatten(Node head) {
        flattenGetTail(head);
        return head;
    }

    private Node flattenGetTail(Node head) {
        if (head == null) {
            return null;
        }
        Node tail = head;
        Node headNext = head.next;
        if (head.child != null) {
            Node childHead = head.child;
            Node childTail = flattenGetTail(childHead);
            childHead.prev = head;
            head.next = childHead;
            head.child = null;  // set child to null to clear it up!
            tail = childTail;
        }
        if (headNext != null) {
            tail.next = headNext;
            headNext.prev = tail;
            tail = flattenGetTail(headNext);
        }
        return tail;
    }

    public static void main(String[] args) {
        Node three = new Node(3, null, null, null);
        Node two = new Node(2, null, null, null);
        Node one = new Node(1, null, null, null);
        one.next = two;
        two.prev = one;
        two.child = three;
        new FlattenMultiDoubleList().flatten(one);
    }

}
