import java.util.*;

public class MergeSimliarItems {
    public List<List<Integer>> mergeSimilarItems(int[][] it1, int[][] it2) {
        List<List<Integer>> res = new ArrayList<>();
        Map<Integer, Integer> m = new HashMap<>();
        for (int[] it : it1) {
            m.put(it[0], m.getOrDefault(it[0], 0) + it[1]);
        }
        for (int[] it : it2) {
            m.put(it[0], m.getOrDefault(it[0], 0) + it[1]);
        }
        for (int k : m.keySet()) {
            res.add(List.of(k, m.get(k)));
        }
        Collections.sort(res, (x, y) -> Integer.compare(x.get(0), y.get(0)));
        return res;
    }
}
