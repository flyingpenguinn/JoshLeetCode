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
    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }


    // gist is to use already built "next" for this and next level, to avoid a queue
    public Node connect(Node root) {
        Node nh = null;
        Node cur = root;
        while (cur != null) {
            while (cur != null) {
                if (cur.left == null && cur.right == null) {
                    cur = cur.next;
                } else {
                    if (cur.left != null && cur.right != null) {
                        cur.left.next = cur.right;
                    }
                    Node nxt = cur.next;
                    while (nxt != null && nxt.left == null && nxt.right == null) {
                        nxt = nxt.next;
                    }
                    Node cright = cur.right == null ? cur.left : cur.right;
                    Node cleft = cur.left == null ? cur.right : cur.left;
                    if (nh == null) {
                        nh = cleft;
                    }
                    if (nxt != null) {
                        Node nleft = nxt.left == null ? nxt.right : nxt.left;
                        cright.next = nleft;
                        cur = nxt;
                    } else {
                        break;
                    }
                }
            }
            cur = nh;
            nh = null;
        }
        return root;

    }

}
