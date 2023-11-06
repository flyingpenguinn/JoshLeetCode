import java.util.HashSet;
import java.util.Set;

public class FindChampionI {
    public int findChampion(int[][] a) {
        int m = a.length;
        int n = a[0].length;
        Set<Integer> cand = new HashSet<>();
        for (int i = 0; i < m; ++i) {
            cand.add(i);
        }
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (a[i][j] == 1) {
                    cand.remove(j);
                }
            }
        }
        return cand.iterator().next();
    }
}
