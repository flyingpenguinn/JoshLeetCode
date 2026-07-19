public class MinAdjSwapsPartitionArray {
    private long mod = (long) (1e9 + 7);

    public int minAdjacentSwaps(int[] a, int v1, int v2) {
        int n = a.length;
        int[] na = new int[n];
        int ni = 0;
        long res = 0;
        for (int i = 0; i < n; ++i) {
            int v = a[i];
            if (v < v1) {
                na[ni] = v;
                int diff = i - ni;
                res += diff;
                res %= mod;
                ++ni;
            }
        }
        for (int i = 0; i < n; ++i) {
            int v = a[i];
            if (v >= v1) {
                na[ni++] = v;
            }
        }
        ni = n - 1;
        for (int i = n - 1; i >= 0; --i) {
            int v = na[i];
            if (v > v2) {
                na[ni] = v;
                int diff = ni - i;
                res += diff;
                res %= mod;
                --ni;
            }
        }
        return (int) res;
    }
}
