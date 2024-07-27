import java.util.Arrays;
import java.util.PriorityQueue;

public class MinCostToConvertStrngI {
    // floyd
    public long minimumCost(String s, String t, char[] orig, char[] chs, int[] cost) {
        int n = s.length();
        int Max = (int) 1e9;
        long[][] dist = new long[26][26];
        for (int i = 0; i < 26; i++) {
            Arrays.fill(dist[i], Max);
            dist[i][i] = 0;
        }
        for (int i = 0; i < orig.length; i++) {
            int o = orig[i] - 'a';
            int ch = chs[i] - 'a';
            long cst = cost[i];
            dist[o][ch] = Math.min(dist[o][ch], cst);
        }
        for (int k = 0; k < 26; k++) {
            for (int i = 0; i < 26; i++) {
                for (int j = 0; j < 26; j++) {
                    if (dist[i][k] + dist[k][j] < dist[i][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }
        long res = 0;
        for (int i = 0; i < n; i++) {
            int sc = s.charAt(i) - 'a';
            int tc = t.charAt(i) - 'a';
            if (sc == tc) {
                continue;
            }
            if (dist[sc][tc] >= Max) {
                return -1;
            }
            res += dist[sc][tc];
        }
        return res;
    }
}
