import java.util.ArrayDeque;
import java.util.Deque;

/*
LC#255
Given an array of numbers, verify whether it is the correct preorder traversal sequence of a binary search tree.

You may assume each number in the sequence is unique.

Consider the following binary search tree:

     5
    / \
   2   6
  / \
 1   3
Example 1:

Input: [5,2,6,1,3]
Output: false
Example 2:

Input: [5,2,1,3,6]
Output: true
Follow up:
Could you do it using only constant space complexity?
 */
public class VerifyPreorderSequenceBst {
    // if big, keep bigging. this is the only possible violation. i.e. on some right tree
    // keep root in stack when we are on left subtree
    // can do o1 space if we reuse old array for stack
    public boolean verifyPreorder(int[] a) {
        Deque<Integer> st = new ArrayDeque<>();
        int min = Integer.MIN_VALUE;

        for (int i = 0; i < a.length; i++) {

            if (a[i] < min) {
                return false;
            }
            if (st.isEmpty()) {
                st.push(a[i]);
            } else if (a[i] < st.peek()) {
                st.push(a[i]);
            } else {
                int root = -1;
                while (!st.isEmpty() && st.peek() < a[i]) {
                    root = st.pop();
                }
                min = root + 1;
                st.push(a[i]);
            }

        }
        return true;
    }
}

// worst case o(n2)
class VerifyPreorderSequenceBstRecursion {
    public boolean verifyPreorder(int[] a) {
        return dov(a, 0, a.length - 1, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    boolean dov(int[] a, int l, int u, long min, long max) {
        if (l > u) {
            return true;
        }
        if (a[l] < min || a[l] > max) {
            return false;
        }
        int i = l + 1;
        for (; i <= u; i++) {
            if (a[i] > a[l]) {
                break;
            }
        }
        return dov(a, l + 1, i - 1, min, a[l] - 1L) && dov(a, i, u, a[l] + 1L, max);
    }

}
