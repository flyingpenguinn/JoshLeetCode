public class MinCostToMoveBetweenNumberAndIndices {
    public int[] minCost(int[] a, int[][] queries) {
        int n = a.length;
        int[] closest = new int[n];
        closest[0] = 1;
        closest[n - 1] = n - 2;
        for (int i = 1; i + 1 < n; ++i) {
            if (Math.abs(a[i] - a[i - 1]) <= Math.abs(a[i + 1] - a[i])) {
                closest[i] = i - 1;
            } else {
                closest[i] = i + 1;
            }
        }
        int[] godown = new int[n];
        for (int i = 1; i < n; ++i) {
            if (closest[i] == i - 1) {
                godown[i] = 1;
            } else {
                godown[i] = a[i] - a[i - 1];
            }
        }
        int[] sumgodown = new int[n];
        sumgodown[n - 1] = godown[n - 1];
        for (int i = n - 2; i >= 0; --i) {
            sumgodown[i] = sumgodown[i + 1] + godown[i];
        }

        int[] goup = new int[n];
        for (int i = 0; i + 1 < n; ++i) {
            if (closest[i] == i + 1) {
                goup[i] = 1;
            } else {
                goup[i] = a[i + 1] - a[i];
            }
        }
        int[] sumgoup = new int[n];
        sumgoup[0] = goup[0];
        for (int i = 1; i < n; ++i) {
            sumgoup[i] = sumgoup[i - 1] + goup[i];
        }
        int qn = queries.length;
        int[] res = new int[qn];
        for (int i = 0; i < qn; ++i) {
            int v1 = queries[i][0];
            int v2 = queries[i][1];
            int cur = 0;
            if (v1 < v2) {
                cur = (v2 == 0 ? 0 : sumgoup[v2 - 1]) - (v1 == 0 ? 0 : sumgoup[v1 - 1]);
            } else {
                cur = (v2 + 1 == n ? 0 : sumgodown[v2 + 1]) - (v1 + 1 == n ? 0 : sumgodown[v1 + 1]);
            }
            res[i] = cur;
        }
        return res;
    }
}
