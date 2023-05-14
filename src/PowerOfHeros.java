import java.util.Arrays;

public class PowerOfHeros {
    // it is a[i]*(1*a[i+1] + 2*a[i+1] + 4*a[i+2] + 8*....)
    private long mod = (long) (1e9 + 7);

    public int sumOfPower(int[] a) {
        Arrays.sort(a);
        int n = a.length;
        long sumsq = 1L * a[n - 1] * a[n - 1];
        sumsq %= mod;
        long res = 1L * a[n - 1] * a[n - 1];
        res %= mod;
        res *= a[n - 1];
        res %= mod;
        for (int i = n - 2; i >= 0; --i) {
            long cur = 1L * a[i] * a[i];
            cur %= mod;
            cur *= a[i];
            cur %= mod;
            res += cur;
            res %= mod;
            long cur2 = a[i] * sumsq;
            cur2 %= mod;
            res += cur2;
            res %= mod;
            sumsq *= 2;
            sumsq += 1L * a[i] * a[i];
            sumsq %= mod;
        }
        return (int) res;
    }


}
