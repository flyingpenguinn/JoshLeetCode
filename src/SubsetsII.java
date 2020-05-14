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
        Arrays.sort(a);
        return dos(a, 0);
    }

    private List<List<Integer>> dos(int[] a, int i) {
        List<List<Integer>> r = new ArrayList<>();
        if (i == a.length) {
            List<Integer> ri = new ArrayList<>();
            r.add(ri);
            return r;
        }
        // skip all equal ones
        int j = i;
        while (j < a.length && a[j] == a[i]) {
            j++;
        }
        int count = j - i;
        // either add 0 ai, 1 ai,... or k ai, k equals to the repetition
        List<List<Integer>> later = dos(a, j);
        List<Integer> curai = new ArrayList<>();
        for (int k = 0; k <= count; k++) {
            if (k > 0) {
                curai.add(a[i]);
            }
            for (List<Integer> li : later) {
                // ai can repeat count times in each subset
                List<Integer> ri = new ArrayList<>(li);
                ri.addAll(curai);
                r.add(ri);
            }
        }
        return r;
    }
}
