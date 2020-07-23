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
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        // check null error if so. target is positive
        Arrays.sort(candidates);
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
        int j = i;
        while (j < candidates.length && candidates[j] == candidates[i]) {
            j++;
        }
        dfs(j, candidates, curNum, target, r, cur);
        int oldSize = cur.size();
        for (int k = i; k < j; k++) {
            cur.add(candidates[k]);
            curNum += candidates[k];
            if (curNum > target) {
                break;
            }
            dfs(j, candidates, curNum, target, r, cur);
        }
        while (cur.size() > oldSize) {
            cur.remove(cur.size() - 1);
        }
    }
}
