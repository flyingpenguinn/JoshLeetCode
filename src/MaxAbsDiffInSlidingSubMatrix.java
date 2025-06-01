import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MaxAbsDiffInSlidingSubMatrix {
    public int[][] minAbsDiff(int[][] a, int k) {
        int m = a.length;
        int n = a[0].length;
        int[][] res = new int[m - k + 1][n - k + 1];
        int Max = (int) 1e9;
        for (int i = 0; i + k - 1 < m; ++i) {
            for (int j = 0; j + k - 1 < n; ++j) {
                int cres = Max;
                List<Integer> vs = new ArrayList<>();
                for (int p = 0; p < k; ++p) {
                    for (int q = 0; q < k; ++q) {
                        vs.add(a[i + p][j + q]);
                    }
                }
                Collections.sort(vs);
                int vn = vs.size();
                for (int l = 0; l + 1 < vn; ++l) {
                    int diff = vs.get(l + 1) - vs.get(l);
                    // SHOULD NOT CHECK == here must use equals or diff == 0
                    if (diff == 0) {
                        continue;
                    }
                    cres = Math.min(cres, diff);
                }
                if (cres == Max) {
                    res[i][j] = 0;
                } else {
                    res[i][j] = cres;
                }
            }
        }
        return res;
    }
}
