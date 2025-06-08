public class CountNumberOfPermutations {
    // as long as 0 is doable...
    public int countPermutations(int[] a) {
        int n = a.length;
        int v0 = a[0];
        for (int i = 1; i < n; ++i) {
            if (a[i] <= v0) {
                return 0;
            }
        }
        long mod = (long) (1e9 + 7);
        long res = 1;
        for (int i = 1; i <= n - 1; ++i) {
            res *= i;
            res %= mod;
        }
        return (int) res;
    }
}
