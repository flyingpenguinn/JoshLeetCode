import base.ArrayUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
LC#90
Given a collection of integers that might contain duplicates, nums, return all possible subsets (the power set).

Note: The solution set must not contain duplicate subsets.

Example:

Input: [1,2,2]
Output:
[
  [2],
  [1],
  [1,2,2],
  [2,2],
  [1,2],
  []
]
 */

public class SubsetsII {
    /*
Then how many subsets are there if there are duplicate elements? We can treat duplicate element as a spacial element. For example, if we have duplicate elements (5, 5), instead of treating them as two elements that are duplicate, we can treat it as one special element 5, but this element has more than two choices: you can either NOT put it into the subset, or put ONE 5 into the subset, or put TWO 5s into the subset. Therefore, we are given an array (a1, a2, a3, ..., an)
with each of them appearing (k1, k2, k3, ..., kn) times, the number of subset is (k1+1)(k2+1)...(kn+1).
 */
    public List<List<Integer>> subsetsWithDup(int[] a) {
        List<List<Integer>> r = new ArrayList<>();
        Arrays.sort(a);
        dfs(0, a, new ArrayList<>(), r);
        return r;
    }

    private void dfs(int i, int[] a, List<Integer> cur, List<List<Integer>> r) {
        int n = a.length;
        if (i == n) {
            r.add(new ArrayList<>(cur));
            return;
        }
        int j = i;
        while (j < n && a[j] == a[i]) {
            j++;
        }
        dfs(j, a, cur, r);
        int oldSize = cur.size();
        // i...j-1, j is different from i
        for (int k = i; k < j; k++) {
            cur.add(a[k]);
            dfs(j, a, cur, r);
        }
        while (cur.size() != oldSize) {
            cur.remove(cur.size() - 1);
        }
    }
}
