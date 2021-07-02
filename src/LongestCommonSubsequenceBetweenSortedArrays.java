import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LongestCommonSubsequenceBetweenSortedArrays {
    public List<Integer> longestCommomSubsequence(int[][] a) {
        Map<Integer, Integer> m = new HashMap<>();
        for (int[] ai : a) {
            for (int aii : ai) {
                m.put(aii, m.getOrDefault(aii, 0) + 1);
            }
        }
        List<Integer> res = new ArrayList<>();
        for (int k : m.keySet()) {
            if (m.get(k) == a.length) {
                res.add(k);
            }
        }
        return res;
    }
}
