import java.util.*;

public class MinPairRemovalToSortArrayII {
    // TODO
    public int minimumPairRemoval(int[] nums) {
        int n = nums.length;
        if (n <= 1) {
            return 0; // A single-element array is already non-decreasing.
        }

        // Build a doubly-linked list for the input array.
        // Each node stores its value and an "order" (its original index) for tie-breaking.
        Node[] nodes = new Node[n];
        for (int i = 0; i < n; i++) {
            nodes[i] = new Node(nums[i], i);
        }
        for (int i = 0; i < n; i++) {
            if (i > 0) {
                nodes[i].prev = nodes[i - 1];
            }
            if (i < n - 1) {
                nodes[i].next = nodes[i + 1];
            }
        }
        Node head = nodes[0];

        // Count the initial number of violations (where a node's value is greater than its next node's value).
        int violations = 0;
        Node cur = head;
        while (cur != null && cur.next != null) {
            if (cur.val > cur.next.val) {
                violations++;
            }
            cur = cur.next;
        }
        if (violations == 0) {
            return 0; // Already non-decreasing.
        }

        // Use a priority queue to store all adjacent pairs.
        // Each pair is prioritized first by the sum of the two numbers,
        // and in case of ties, by the left node’s original order (leftmost first).
        PriorityQueue<Pair> pq = new PriorityQueue<>();
        cur = head;
        while (cur != null && cur.next != null) {
            pq.offer(new Pair(cur, cur.next));
            cur = cur.next;
        }

        int operations = 0;
        // Process merges until there are no violations.
        while (violations > 0) {
            if (pq.isEmpty()) {
                break; // Should not happen, as we can always merge until one element remains.
            }
            Pair pair = pq.poll();
            Node left = pair.left;
            Node right = pair.right;
            // Check if both nodes are still valid and adjacent.
            if (left.removed || right.removed || left.next != right) {
                continue;
            }

            // Merge the two nodes: the new node's value is the sum of left and right.
            long newVal = left.val + right.val;
            Node merged = new Node(newVal, left.order); // Preserve left's order.
            merged.prev = left.prev;
            merged.next = right.next;

            // Mark the original nodes as removed.
            left.removed = true;
            right.removed = true;

            // Rewire the list.
            if (merged.prev != null) {
                merged.prev.next = merged;
            } else {
                head = merged;
            }
            if (merged.next != null) {
                merged.next.prev = merged;
            }

            // Update the violation count.
            // Remove violations that disappear:
            int removedViolations = 0;
            if (left.prev != null && left.prev.val > left.val) {
                removedViolations++;
            }
            if (left.val > right.val) {
                removedViolations++;
            }
            if (right.next != null && right.val > right.next.val) {
                removedViolations++;
            }
            // Add violations from new adjacencies:
            int addedViolations = 0;
            if (merged.prev != null && merged.prev.val > merged.val) {
                addedViolations++;
            }
            if (merged.next != null && merged.val > merged.next.val) {
                addedViolations++;
            }
            violations = violations - removedViolations + addedViolations;

            // Add new adjacent pairs involving the merged node into the priority queue.
            if (merged.prev != null) {
                pq.offer(new Pair(merged.prev, merged));
            }
            if (merged.next != null) {
                pq.offer(new Pair(merged, merged.next));
            }

            operations++;
        }

        return operations;
    }

    // Doubly-linked list node.
    private static class Node {
        long val;
        int order; // Original index for tie-breaking.
        Node prev;
        Node next;
        boolean removed;

        Node(long val, int order) {
            this.val = val;
            this.order = order;
            this.removed = false;
        }
    }

    // Represents an adjacent pair in the list.
    private static class Pair implements Comparable<Pair> {
        Node left;
        Node right;
        long sum;
        int order; // Equal to left.order.

        Pair(Node left, Node right) {
            this.left = left;
            this.right = right;
            this.sum = left.val + right.val;
            this.order = left.order;
        }

        public int compareTo(Pair other) {
            if (this.sum != other.sum) {
                return Long.compare(this.sum, other.sum);
            }
            return Integer.compare(this.order, other.order);
        }
    }

}
