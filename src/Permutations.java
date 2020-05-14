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

    // the same code can be used in permutations II.
    // another way: get later permutations then insert current i in between each number (first and last included) in later results
    List<List<Integer>> r = new ArrayList<>();

    public List<List<Integer>> permute(int[] a) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int i : a) {
            map.put(i, map.getOrDefault(i, 0) + 1);
        }
        dop(0, a.length, new ArrayList<>(), map);
        return r;
    }

    void dop(int i, int n, List<Integer> cur, Map<Integer, Integer> map) {
        if (i == n) {
            r.add(new ArrayList<>(cur));
            return;
        }
        for (int rem : map.keySet()) {
            if (map.get(rem) > 0) {
                cur.add(rem);
                map.put(rem, map.get(rem) - 1);
                dop(i + 1, n, cur, map);
                cur.remove(cur.size() - 1);
                map.put(rem, map.get(rem) + 1);

            }
        }
    }
}
