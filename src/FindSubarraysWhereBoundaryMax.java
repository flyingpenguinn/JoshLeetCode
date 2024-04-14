import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FindSubarraysWhereBoundaryMax {
    // sparse table solution can also use stack to see if the first >= element on the left is itself and count
    private int M = 17;
    private int[][] d;

    public long numberOfSubarrays(int[] a) {
        int n = a.length;
        d = new int[n + 10][M];
        createSparseTable(a);
        Map<Integer, List<Integer>> m = new HashMap<>();
        for (int i = 0; i < n; ++i) {
            m.computeIfAbsent(a[i], k -> new ArrayList<>()).add(i);
        }
        long res = 0;
        for (int k : m.keySet()) {
            List<Integer> l = m.get(k);
            int ln = l.size();
            int i = 0;
            while (i < ln) {
                int j = i + 1;
                while (j < ln && (querySparseTable(l.get(j - 1) + 1, l.get(j) + 1) <= k)) {
                    ++j;
                }
                // i... j-1 good
                long len = j - i;
                long cur = (1 + len) * len / 2;
                res += cur;
                i = j;

            }
        }
        return res;
    }


    private void createSparseTable(int[] w) {
        int n = w.length;
        for (int j = 0; j < M; j++) {
            for (int i = 1; i + (1 << j) - 1 <= n; i++) {
                if (j == 0) {
                    d[i][j] = w[i - 1];
                } else {
                    d[i][j] = Math.max(d[i][j - 1], d[i + (1 << (j - 1))][j - 1]);
                }
            }
        }
    }

    // index start from 1 !
    private int querySparseTable(int l, int r) {
        int len = r - l + 1;
        int k = (int) (Math.log(len) / Math.log(2));
        return Math.max(d[l][k], d[r - (1 << k) + 1][k]);
    }
}
