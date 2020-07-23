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
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        // check null, all numbers >0. otherwise error out
        List<List<Integer>> r = new ArrayList<>();
        dfs(0, candidates, 0, target, r, new ArrayList<>());
        return r;
    }

    private void dfs(int i, int[] candidates, int curNum, int target, List<List<Integer>> r, List<Integer> cur) {
        if (i == candidates.length) {
            if (curNum == target) {
                r.add(new ArrayList<>(cur));
            }
            return;
        }
        dfs(i + 1, candidates, curNum, target, r, cur); // no pick
        if (curNum < target) {
            cur.add(candidates[i]);
            dfs(i, candidates, curNum + candidates[i], target, r, cur); // stay on i to pick more same element
            cur.remove(cur.size() - 1);
        }
    }
}
