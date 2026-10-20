import java.util.ArrayList;
import java.util.List;

public class CreateComponentsWithSameValue {
    private List<Integer>[] t;
    private int[] sums;
    private int[] counts;

    public int componentValue(int[] a, int[][] edges) {
        int n = a.length;
        int sum = 0;
        t = new ArrayList[n];
        for (int i = 0; i < n; ++i) {
            t[i] = new ArrayList<>();
            sum += a[i];
        }

        for (int[] e : edges) {
            int v1 = e[0];
            int v2 = e[1];
            t[v1].add(v2);
            t[v2].add(v1);
        }
        sums = new int[n];
        counts = new int[n];
        dfs(a, 0, -1);
        int res = 0;
        for (int d = 1; d * d <= sum; ++d) {
            if (sum % d != 0) {
                continue;
            }

            if (sum <= d * counts[0]) {
                int parts = sum / d;
                int counts = docount(0, d, -1);
                if (counts >= parts) {
                    return parts - 1;
                }
            }
            int other = sum / d;
            if (sum <= other * counts[0]) {
                int parts = sum / other;
                int counts = docount(0, other, -1);
                if (counts >= parts) {
                    res = Math.max(res, parts - 1);
                }
            }
        }
        return res;

    }

    private int[] dfs(int[] a, int i, int pa) {
        int csum = a[i];
        int ccount = 1;
        for (int ne : t[i]) {
            if (ne == pa) {
                continue;
            }
            int[] cr = dfs(a, ne, i);
            csum += cr[0];
            ccount += cr[1];
        }
        sums[i] = csum;
        counts[i] = ccount;
        return new int[]{csum, ccount};
    }

    private int docount(int node, int d, int pa) {
        int res = 0;
        if (sums[node] % d == 0) {
            ++res;
        }
        for (int ne : t[node]) {
            if (ne == pa) {
                continue;
            }
            res += docount(ne, d, node);
        }
        return res;
    }
}
