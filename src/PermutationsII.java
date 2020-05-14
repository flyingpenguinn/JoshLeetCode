import base.ArrayUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    // for each position check a map to loop in available elements
    List<List<Integer>> r = new ArrayList<>();

    public List<List<Integer>> permuteUnique(int[] a) {
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

    public static void main(String[] args) {
        System.out.println(new PermutationsII().permuteUnique(ArrayUtils.read1d("1,1,2")));
    }
}
