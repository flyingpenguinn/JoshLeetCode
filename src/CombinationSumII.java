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
    public List<List<Integer>> combinationSum2(int[] a, int t) {
        Arrays.sort(a);
        return doc(a, t, 0);
    }

    private List<List<Integer>> doc(int[] a, int t, int i) {
        List<List<Integer>> r = new ArrayList<>();
        if (i == a.length) {

            if (t == 0) {
                List<Integer> empty = new ArrayList<>();
                r.add(empty);
            }
            return r;
        }

        int j = i;
        while (j < a.length && a[j] == a[i]) {
            j++;
        }
        List<List<Integer>> without = doc(a, t, j);
        r.addAll(without);
        List<Integer> cur = new ArrayList<>();
        // i...j-1
        for (int k = i; k < j; k++) {
            cur.add(a[i]);
            if (t >= a[i]) {
                t -= a[i];
                // 1,2,3...counts of i. jump to j
                List<List<Integer>> with = doc(a, t, j);
                for (List<Integer> w : with) {

                    w.addAll(cur);
                    r.add(w);
                }
            }
        }
        return r;
    }
}
