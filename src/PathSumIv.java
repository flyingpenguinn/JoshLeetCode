import base.ArrayUtils;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;

/*
LC#666
If the depth of a tree is smaller than 5, then this tree can be represented by a list of three-digits integers.

For each integer in this list:

The hundreds digit represents the depth D of this node, 1 <= D <= 4.
The tens digit represents the position P of this node in the level it belongs to, 1 <= P <= 8. The position is the same as that in a full binary tree.
The units digit represents the value V of this node, 0 <= V <= 9.
Given a list of ascending three-digits integers representing a binary tree with the depth smaller than 5, you need to return the sum of all paths from the root towards the leaves.

It's guaranteed that the given list represents a valid connected binary tree.

Example 1:

Input: [113, 215, 221]
Output: 12
Explanation:
The tree that the list represents is:
    3
   / \
  5   1

The path sum is (3 + 5) + (3 + 1) = 12.


Example 2:

Input: [113, 221]
Output: 4
Explanation:
The tree that the list represents is:
    3
     \
      1

The path sum is (3 + 1) = 4.
 */
public class PathSumIv {
    // can use a map to cache the results
    int r = 0;

    public int pathSum(int[] a) {
        dop(1, 1, a, 0);
        return r;
    }

    void dop(int d, int p, int[] a, int sum) {
        int rn = find(a, d, p);
        if (rn == -1) {
            return;
        }
        int left = find(a, d + 1, p * 2 - 1);
        int right = find(a, d + 1, p * 2);
        if (left == -1 && right == -1) {
            r += sum + rn;
            return;
        }
        if (left != -1) {
            dop(d + 1, p * 2 - 1, a, sum + rn);
        }
        if (right != -1) {
            dop(d + 1, p * 2, a, sum + rn);
        }
    }

    int find(int[] a, int d, int p) {
        int n = a.length;
        for (int i = 0; i < n; i++) {
            if (a[i] >= d * 100 + p * 10 && a[i] <= d * 100 + p * 10 + 9) {
                return a[i] % 10;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(new PathSumIv().pathSum(ArrayUtils.read1d("113, 221")));
    }
}
