
/*
LC#708
Given a node from a Circular Linked List which is sorted in ascending order, write a function to insert a value insertVal into the list such that it remains a sorted circular list. The given node can be a reference to any single node in the list, and may not be necessarily the smallest value in the circular list.

If there are multiple suitable places for insertion, you may choose any place to insert the new value. After the insertion, the circular list should remain sorted.

If the list is empty (i.e., given node is null), you should create a new single circular list and return the reference to that single node. Otherwise, you should return the original given node.



Example 1:



Input: head = [3,4,1], insertVal = 2
Output: [3,4,1,2]
Explanation: In the figure above, there is a sorted circular list of three elements. You are given a reference to the node with value 3, and we need to insert 2 into the list. The new node should be inserted between node 1 and node 3. After the insertion, the list should look like this, and we should still return node 3.



Example 2:

Input: head = [], insertVal = 1
Output: [1]
Explanation: The list is empty (given head is null). We create a new single circular list and return the reference to that single node.
Example 3:

Input: head = [1], insertVal = 0
Output: [1,0]


Constraints:

0 <= Number of Nodes <= 5 * 10^4
-10^6 <= Node.val <= 10^6
-10^6 <= insertVal <= 10^6
 */
public class InsertIntoSortedCircularList {
    class Node {
        public int val;
        public Node next;

        public Node(int v) {
            this.val = v;
        }

        public Node(int _val, Node _next) {
            val = _val;
            next = _next;
        }
    }

    // differentiate - previous <= and next >=
    // after max, number is min
    // after max, number is max
    public Node insert(Node head, int v) {
        Node nn = new Node(v);
        if (head == null) {
            nn.next = nn;
            return nn;
        }
        if (head.next == head) {
            head.next = nn;
            nn.next = head;
            return head;
        }
        Node prev = head;
        Node next = head.next;
        boolean inserted = false;
        while (true) {
            if ((prev.val <= v && next.val >= v) ||  // just fit in
                    (prev.val > next.val && prev.val < v) ||  // max
                    (prev.val > next.val && next.val > v)) {  // min
                inserted = true;
                prev.next = nn;
                nn.next = next;
                break;
            }
            prev = prev.next;
            next = next.next;
            if (prev == head) {
                break;
            }
        }
        if (!inserted) {
            // only possibility is all num equal and not = 0. insert anywhere
            nn.next = head.next;
            head.next = nn;
        }
        return head;
    }
}
