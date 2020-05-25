package contest;

import java.util.Map;

public class ContestUtils {

    long Mod = 1000000007L;


    private int[][] dirs = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};


    void updatemap(Map<Integer, Integer> m, int k, int d) {
        int nv = m.getOrDefault(k, 0) + d;
        if (nv <= 0) {
            m.remove(k);
        } else {
            m.put(k, nv);
        }
    }

    protected void initsum(int[][] a, int[][] sum) {
        int m = a.length;
        int n = a[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int cur = a[i][j] == 1 ? 1 : 0;
                sum[i][j] = (i == 0 ? 0 : sum[i - 1][j]) + (j == 0 ? 0 : sum[i][j - 1]) - ((i == 0 || j == 0) ? 0 : sum[i - 1][j - 1]) + cur;
            }
        }
    }

    private int sum(int i, int j, int k, int l, int[][] sum) {
        return sum[k][l] - (i == 0 ? 0 : sum[i - 1][l]) - (j == 0 ? 0 : sum[k][j - 1]) + ((i == 0 || j == 0) ? 0 : sum[i - 1][j - 1]);
    }
}
