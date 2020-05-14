import java.util.Arrays;

public class SumOfSubsequenceWidth {
    int MOD = 1000000007;

    // sort then for each its sum of all aj...ai for j<i. the ... are powers of 2
    public int sumSubseqWidths(int[] a) {
        if (a.length == 1) {
            return 0;
        }
        Arrays.sort(a);
        int n = a.length;
        // 2^n+2^n-1...+1
        long twopsum = 0;
        // a1*2^n+a2*2^n-1+..
        long accu = a[0];
        long sum = 0;
        for (int i = 1; i < n; i++) {
            twopsum = (twopsum * 2 + 1) % MOD;

            long cur = a[i] * twopsum - accu;
            //  System.out.println(cur);
            accu = ((accu * 2) % MOD + a[i]) % MOD;
            sum = (sum + cur) % MOD;
        }
        return (int) sum;
    }
}
