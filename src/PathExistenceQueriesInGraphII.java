import java.util.Arrays;

public class PathExistenceQueriesInGraphII {
    // TODO do it myself. binary lifting
    public int[] pathExistenceQueries(int n, int[] nums, int maxDiff, int[][] queries) {
        int N = n, Q = queries.length;
        int[][] arr = new int[N][2];
        for (int i = 0; i < N; i++) {
            arr[i][0] = nums[i];
            arr[i][1] = i;
        }
        Arrays.sort(arr, (a, b) -> a[0] - b[0]);
        int[] pos = new int[N];
        int[] sorted = new int[N];
        for (int i = 0; i < N; i++) {
            sorted[i] = arr[i][0];
            pos[arr[i][1]] = i;
        }
        // components by adjacent gaps
        int[] comp = new int[N];
        comp[0] = 0;
        for (int i = 1; i < N; i++) {
            comp[i] = comp[i - 1] + (sorted[i] - sorted[i - 1] > maxDiff ? 1 : 0);
        }
        // next reach in one hop
        int[] nxt = new int[N];
        for (int i = 0, j = 0; i < N; i++) {
            while (j + 1 < N && sorted[j + 1] - sorted[i] <= maxDiff) j++;
            nxt[i] = j;
        }
        // prev reach in one hop
        int[] prv = new int[N];
        for (int i = N - 1, j = N - 1; i >= 0; i--) {
            while (j - 1 >= 0 && sorted[i] - sorted[j - 1] <= maxDiff) j--;
            prv[i] = j;
        }
        // binary lifting tables
        int LG = 1;
        while ((1 << LG) <= N) LG++;
        int[][] up = new int[LG][N], down = new int[LG][N];
        up[0] = nxt;
        down[0] = prv;
        for (int k = 1; k < LG; k++) {
            for (int i = 0; i < N; i++) {
                up[k][i] = up[k - 1][up[k - 1][i]];
                down[k][i] = down[k - 1][down[k - 1][i]];
            }
        }
        int[] ans = new int[Q];
        for (int i = 0; i < Q; i++) {
            int u = queries[i][0], v = queries[i][1];
            int p = pos[u], q = pos[v];
            if (p == q) {
                ans[i] = 0;
            } else if (comp[p] != comp[q]) {
                ans[i] = -1;
            } else if (p < q) {
                if (nxt[p] >= q) {
                    ans[i] = 1;
                } else {
                    int hops = 0;
                    int cur = p;
                    for (int k = LG - 1; k >= 0; k--) {
                        int to = up[k][cur];
                        if (to < q) {
                            hops += 1 << k;
                            cur = to;
                        }
                    }
                    ans[i] = hops + 1;
                }
            } else { // p > q
                if (prv[p] <= q) {
                    ans[i] = 1;
                } else {
                    int hops = 0;
                    int cur = p;
                    for (int k = LG - 1; k >= 0; k--) {
                        int to = down[k][cur];
                        if (to > q) {
                            hops += 1 << k;
                            cur = to;
                        }
                    }
                    ans[i] = hops + 1;
                }
            }
        }
        return ans;
    }
}

