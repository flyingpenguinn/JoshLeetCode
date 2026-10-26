public class CountFrequencyStaircaseSubarraysII {
    public int countStaircase(int[] a) {
        int n = a.length;
        int[] seen = new int[100001];
        int[] cnt = new int[100001];
        int[] rank = new int[100001];

        int res = 0;
        int maxm = 0;
        while ((maxm + 1) * (maxm + 2) / 2 <= n) {
            ++maxm;
        }

        for (int l = 0; l < n; ++l) {
            int m = 0;
            for (int r = l; r < n; ++r) {
                int v = a[r];

                if (seen[v] != l + 1) {
                    seen[v] = l + 1;
                    cnt[v] = 0;
                    rank[v] = ++m;
                }

                if (m > maxm) {
                    break;
                }

                ++cnt[v];
                if (cnt[v] > rank[v]) {
                    break;
                }

                if (r - l + 1 == m * (m + 1) / 2) {
                    ++res;
                }
            }
        }
        return res;
    }
}
