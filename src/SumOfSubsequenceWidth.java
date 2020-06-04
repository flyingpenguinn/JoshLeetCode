import java.util.Arrays;

public class SumOfSubsequenceWidth {

    // sort then for each its sum of all aj...ai for j<i. the ... are powers of 2
    // subsequence problems are subset problems, we can sort them first
    // naively, for each i, we find numbers j< it and all numbers in between to count. sorting makes getting all numbers in between easier
    int MOD = 1000000007;

    public int sumSubseqWidths(int[] a) {
        long Mod = 1000000007;
        Arrays.sort(a);
        int n = a.length;
        long r = 0;
        long basesum = 1L; // base == 1+2+4+....
        long presum = a[0];// presum == a[i-1]+a[i-2]*2+a[i-3]*4+....
        for (int i = 1; i < n; i++) {
            long cur = (a[i] * basesum - presum) % Mod;
            if (cur < 0) {
                cur += Mod;
            }
            presum = presum * 2 + a[i];
            presum %= Mod;
            basesum = basesum * 2 + 1;
            basesum %= Mod;
            r += cur;
            r %= Mod;
        }
        return (int) r;
    }
}
