import java.util.Arrays;

import java.util.Arrays;

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
    private int res = 0;

    public int pathSum(int[] a) {
        int n = a.length;
        int[] t = new int[32];
        Arrays.fill(t, -1);
        for (int i = 0; i < n; ++i) {
            int dep = a[i] / 100;
            int seq = (a[i] - 100 * dep) / 10;
            int v = a[i] % 10;
            int index = (1 << (dep - 1)) + seq - 1;
            t[index] = v;
        }
        dfs(t, 1, 0);
        return res;
    }

    private void dfs(int[] t, int i, int csum) {
        if (t[i] == -1) {
            return;
        }
        csum += t[i];
        int li = 2 * i;
        int ri = 2 * i + 1;
        if (t[li] == -1 && t[ri] == -1) {
            res += csum;
            return;
        }
        dfs(t, li, csum);
        dfs(t, ri, csum);
    }
}
