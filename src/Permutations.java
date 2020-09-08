import base.ArrayUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/*
LC#46
Given a collection of distinct integers, return all possible permutations.

Example:

Input: [1,2,3]
Output:
[
  [1,2,3],
  [1,3,2],
  [2,1,3],
  [2,3,1],
  [3,1,2],
  [3,2,1]
]
 */

public class Permutations {

    // using combination (st) to get permutations
    // if we dp it, we can reduce to n^2*n^n because when we are at i facing next choice, we only care what's selected, not their sequence...
    private List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> permute(int[] a) {
        dfs(a, 0, 0, new ArrayList<>());
        return res;
    }

    private void dfs(int[] a, int i, int st, List<Integer> cur) {
        System.out.println(i + " " + st);
        int n = a.length;
        if (i == n) {
            res.add(new ArrayList<>(cur));
            return;
        }
        for (int j = 0; j < n; j++) {
            if (((st >> j) & 1) == 0) {
                int nst = st | (1 << j);
                cur.add(a[j]);
                dfs(a, i + 1, nst, cur);
                cur.remove(cur.size() - 1);
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(new Permutations().permute(ArrayUtils.read1d("1,2,3")));
    }
}
