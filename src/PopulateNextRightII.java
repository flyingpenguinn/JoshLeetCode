import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

/*
LC#117
Given a binary tree

struct Node {
  int val;
  Node *left;
  Node *right;
  Node *next;
}
Populate each next pointer to point to its next right node. If there is no next right node, the next pointer should be set to NULL.

Initially, all next pointers are set to NULL.



Example:



Input: {"$id":"1","left":{"$id":"2","left":{"$id":"3","left":null,"next":null,"right":null,"val":4},"next":null,"right":{"$id":"4","left":null,"next":null,"right":null,"val":5},"val":2},"next":null,"right":{"$id":"5","left":null,"next":null,"right":{"$id":"6","left":null,"next":null,"right":null,"val":7},"val":3},"val":1}

Output: {"$id":"1","left":{"$id":"2","left":{"$id":"3","left":null,"next":{"$id":"4","left":null,"next":{"$id":"5","left":null,"next":null,"right":null,"val":7},"right":null,"val":5},"right":null,"val":4},"next":{"$id":"6","left":null,"next":null,"right":{"$ref":"5"},"val":3},"right":{"$ref":"4"},"val":2},"next":null,"right":{"$ref":"6"},"val":1}

Explanation: Given the above binary tree (Figure A), your function should populate each next pointer to point to its next right node, just like in Figure B.


Note:

You may only use constant extra space.
Recursive approach is fine, implicit stack space does not count as extra space for this problem.
 */
// works for both I and II
public class PopulateNextRightII {
    // don't really need to dfs, can use already populated next pointers to traverse the tree
    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node(int val) {
            this.val = val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }

    public Node connect(Node root) {
        if (root == null) {
            return null;
        }
        Node p = root;
        while (p != null) {
            // p standing on the first non null on this level
            Node head = new Node(-1);
            Node last = head;
            // head and last are for next levels
            while (p != null) {
                if (p.left != null || p.right != null) {
                    // if all empty, then nothing to weave
                    if (p.left != null && p.right != null) {
                        p.left.next = p.right;
                    }
                    last.next = p.left != null ? p.left : p.right;
                    last = p.right != null ? p.right : p.left;
                }
                p = p.next;
            }
            p = head.next;
            // head.next is the first non null at next level
        }
        return root;
    }

}
