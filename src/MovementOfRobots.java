import java.util.Arrays;

public class MovementOfRobots {
    private long Mod = (long) (1e9 + 7);

    public int sumDistance(int[] ia, String s, int d) {
        int n = ia.length;
        long[] a = new long[n];
        for (int i = 0; i < n; ++i) {
            a[i] = ia[i];
        }
        long[] r = new long[n];
        int ri = 0;
        long sum = 0;
        for (int i = 0; i < n; ++i) {
            long cur = 0;
            if (s.charAt(i) == 'L') {
                cur = a[i] - d;
            } else {
                cur = a[i] + d;
            }
            r[ri++] = cur;
            //    System.out.println(cur);
            sum += cur;
            sum %= Mod;
        }
        Arrays.sort(r);
        long res = 0;
        for (int i = 0; i < n; ++i) {
            sum -= r[i];
            long rp = sum - (n - 1 - i) * r[i];
            rp %= Mod;
            res += rp;
            res %= Mod;
        }
        if (res < 0) {
            res += Mod;
            res %= Mod;
        }
        return (int) res;
    }
}
