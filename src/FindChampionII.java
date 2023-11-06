import java.util.HashSet;
import java.util.Set;

public class FindChampionII {
    public int findChampion(int n, int[][] edges) {
        Set<Integer> cand = new HashSet<>();
        for (int i = 0; i < n; ++i) {
            cand.add(i);
        }
        for (int[] e : edges) {
            int v1 = e[0];
            int v2 = e[1];
            cand.remove(v2);
        }
        if (cand.size() != 1) {
            return -1;
        }
        return cand.iterator().next();
    }
}
