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

    // this way is usually faster if we want to catch results
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        return doc(candidates, target, candidates.length - 1);
    }

    private List<List<Integer>> doc(int[] a, int t, int i) {
        if (i == -1) {
            List<List<Integer>> r = new ArrayList<>();
            if (t != 0) {
                // must return total empty for invalid results
                return r;
            } else {
                List<Integer> empty = new ArrayList<>();
                r.add(empty);
                return r;
            }
        }
        // without this i
        List<List<Integer>> base = doc(a, t, i - 1);
        if (t >= a[i]) {
            // or with it. note we are not moving i here for another match
            List<List<Integer>> with = doc(a, t - a[i], i);
            for (List<Integer> w : with) {
                List<Integer> nw = new ArrayList<>(w);
                nw.add(a[i]);
                base.add(nw);
            }
        }
        return base;
    }

    public static void main(String[] args) {
        System.out.println(new CombinationSum().combinationSum(ArrayUtils.read1d("2,3,6,7"), 7));
    }
}


// this is usually faster in pure backtracking
class CombinationSumGlobalVar {
    List<List<Integer>> r = new ArrayList<>();

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        doc(candidates, target, 0, new ArrayList<>());
        return r;
    }

    private void doc(int[] a, int t, int i, List<Integer> cur) {
        if (i == a.length) {
            if (t == 0) {
                // otherwise cur is going to be stripped by backtracking
                r.add(new ArrayList<>(cur));
            }
            return;
        }
        // without this i
        doc(a, t, i + 1, cur);
        if (t >= a[i]) {
            // or with it. note we are not moving i here for another match
            cur.add(a[i]);
            doc(a, t - a[i], i, cur);
            cur.remove(cur.size() - 1);
        }
    }
}
