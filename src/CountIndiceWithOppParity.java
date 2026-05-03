public class CountIndiceWithOppParity {
    public int[] countOppositeParity(int[] a) {
        int n = a.length;
        int[][] cnt = new int[n + 1][2];
        for (int i = n - 1; i >= 0; --i) {
            cnt[i][0] = cnt[i + 1][0];
            cnt[i][1] = cnt[i + 1][1];
            int mod = a[i] % 2;
            ++cnt[i][mod];
        }
        int[] res = new int[n];
        for (int i = 0; i < n; ++i) {
            int mod = a[i] % 2;
            res[i] = cnt[i + 1][mod ^ 1];
        }
        return res;
    }
}
