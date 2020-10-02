import java.util.*;

/*
LC#40
Given a collection of candidate numbers (candidates) and a target number (target), find all unique combinations in candidates where the candidate numbers sums to target.

Each number in candidates may only be used once in the combination.

Note:

All numbers (including target) will be positive integers.
The solution set must not contain duplicate combinations.
Example 1:

Input: candidates = [10,1,2,7,6,1,5], target = 8,
A solution set is:
[
  [1, 7],
  [1, 2, 5],
  [2, 6],
  [1, 1, 6]
]
Example 2:

Input: candidates = [2,5,2,1,2], target = 5,
A solution set is:
[
  [1,2,2],
  [5]
]
 */
public class CombinationSumII {
    // for each i pick 1,2,3...times
    private List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> combinationSum2(int[] a, int t) {
        Arrays.sort(a);
        dfs(0, a, t, new ArrayList<>());
        return res;
    }

    private void dfs(int i, int[] a, int rem, List<Integer> cur) {
        // rem>=0. we pick 0, 1, 2...k of a[i]s where k is count of a[i] in one try
        if (i == a.length) {
            if (rem == 0) {
                res.add(new ArrayList<>(cur));
            }
            return;
        }
        int j = i;
        while (j < a.length && a[j] == a[i]) {
            j++;
        }
        dfs(j, a, rem, cur);
        int oldsize = cur.size();
        while (i < j && rem - a[i] >= 0) {
            rem -= a[i];
            cur.add(a[i]);
            dfs(j, a, rem, cur);
            i++;
        }
        while (cur.size() > oldsize) {
            cur.remove(cur.size() - 1);
        }
    }
}
