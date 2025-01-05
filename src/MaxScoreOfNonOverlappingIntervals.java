import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MaxScoreOfNonOverlappingIntervals {
    private long[][] dp;
    private int[][] dpc;
    private int[] nexts;

    public int[] maximumWeight(List<List<Integer>> a) {

        int n = a.size();
        for (int i = 0; i < n; ++i) {
            a.get(i).add(i);
        }
        Collections.sort(a, (x, y) -> Integer.compare(x.get(0), y.get(0)));
        dp = new long[n + 1][4];
        dpc = new int[n + 1][4];
        nexts = new int[n + 1];
        Arrays.fill(nexts, -1);
        for (int i = 0; i <= n; ++i) {
            Arrays.fill(dp[i], -1);
            Arrays.fill(dpc[i], -1);
        }
        long best = solve(a, 0, 0);


        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            if (dp[i][0] != best) {
                continue;
            }

            if (res.isEmpty() || res.get(0) > a.get(dpc[i][0]).get(3)) {
                int k = i;
                int j = 0;
                List<Integer> cres = new ArrayList<>();
                while (k >= 0 && j < 4 && dpc[k][j] != -1) {
                    final int topick = dpc[k][j];
                    cres.add(a.get(topick).get(3));
                    k = nexts[dpc[k][j]];
                    j += 1;
                }
                Collections.sort(cres);
                res = cres;
            }
        }
        int[] rres = new int[res.size()];
        for (int i = 0; i < res.size(); ++i) {
            rres[i] = res.get(i);
        }
        return rres;
    }


    private long solve(List<List<Integer>> a, int i, int j) {
        int n = a.size();
        if (i == n) {
            return 0;
        }
        if (j == 4) {
            return 0;
        }
        if (dp[i][j] != -1) {
            return dp[i][j];
        }
        long way1 = solve(a, i + 1, j);
        int end = a.get(i).get(1);
        int next = binary(a, end);
        nexts[i] = next;
        long way2 = a.get(i).get(2) + solve(a, next, j + 1);
        if (way2 > way1) {
            dpc[i][j] = i;
        } else if (way2 < way1) {
            dpc[i][j] = dpc[i + 1][j];
        } else {
            int v1 = a.get(dpc[i + 1][j]).get(3);
            int v2 = a.get(i).get(3);
            if (v1 < v2) {
                dpc[i][j] = dpc[i + 1][j];
            } else {
                dpc[i][j] = i;
            }
        }
        long res = Math.max(way1, way2);
        dp[i][j] = res;
        return res;
    }

    private int binary(List<List<Integer>> a, int t) {
        int n = a.size();
        int l = 0;
        int u = a.size() - 1;
        while (l <= u) {
            int mid = l + (u - l) / 2;
            if (a.get(mid).get(0) <= t) {
                l = mid + 1;
            } else {
                u = mid - 1;
            }
        }
        return l;
    }
}
