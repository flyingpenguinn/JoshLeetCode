import base.ArrayUtils;

import java.util.*;

/*
LC#39

Given a set of candidate numbers (candidates) (without duplicates) and a target number (target), find all unique combinations in candidates where the candidate numbers sums to target.

The same repeated number may be chosen from candidates unlimited number of times.

Note:

All numbers (including target) will be positive integers.
The solution set must not contain duplicate combinations.
Example 1:

Input: candidates = [2,3,6,7], target = 7,
A solution set is:
[
  [7],
  [2,2,3]
]
Example 2:

Input: candidates = [2,3,5], target = 8,
A solution set is:
[
  [2,2,2,2],
  [2,3,3],
  [3,5]
]
 */
public class CombinationSum {
    // pick or no pick. note we stood on the position i when we pick to allow picking multiple times
    private List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> combinationSum(int[] a, int t) {
        dfs(0, a, t, new ArrayList<>());
        return res;
    }

    private void dfs(int i, int[] a, int rem, List<Integer> cur) {
        // invariant: rem>=0
        if (i == a.length) {
            if (rem == 0) {
                res.add(new ArrayList<>(cur));
            }
            return;
        }
        dfs(i + 1, a, rem, cur);
        if (rem >= a[i]) {
            cur.add(a[i]);
            dfs(i, a, rem - a[i], cur);
            cur.remove(cur.size() - 1);
        }
    }
}
