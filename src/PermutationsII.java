import base.ArrayUtils;

import java.util.*;

/*
LC#47
Given a collection of numbers that might contain duplicates, return all possible unique permutations.

Example:

Input: [1,1,2]
Output:
[
  [1,1,2],
  [1,2,1],
  [2,1,1]
]
 */
public class PermutationsII {

    // for each position check a map (bag) to loop in available elements
    private List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> permuteUnique(int[] a) {
        Map<Integer, Integer> m = new HashMap<>();
        for (int i = 0; i < a.length; i++) {
            update(m, a[i], 1);
        }
        dfs(0, m, a.length, new ArrayList<>());
        return res;
    }

    private void dfs(int i, Map<Integer, Integer> m, int n, List<Integer> cur) {
        if (i == n) {
            res.add(new ArrayList<>(cur));
            return;
        }
        Set<Integer> keys = new HashSet<>(m.keySet());
        for (int k : keys) {
            update(m, k, -1);
            cur.add(k);
            dfs(i + 1, m, n, cur);
            cur.remove(cur.size() - 1);
            update(m, k, 1);
        }
    }

    private void update(Map<Integer, Integer> m, int k, int d) {
        int nv = m.getOrDefault(k, 0) + d;
        if (nv <= 0) {
            m.remove(k);
        } else {
            m.put(k, nv);
        }
    }

    public static void main(String[] args) {
        System.out.println(new PermutationsII().permuteUnique(ArrayUtils.read1d("1,1,2")));
    }
}
