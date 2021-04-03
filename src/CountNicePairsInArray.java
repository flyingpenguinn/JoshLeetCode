import java.util.HashMap;
import java.util.Map;

public class CountNicePairsInArray {
    private long mod = 1000000007;

    public int countNicePairs(int[] a) {
        Map<Long, Long> m = new HashMap<>();
        int n = a.length;
        long res = 0;
        for (int i = 0; i < n; i++) {
            long cnum = a[i];
            long cur = cnum - rev(cnum);
            long curres = m.getOrDefault(cur, 0L);
            res += curres;
            m.put(cur, curres + 1);
        }
        return (int) (res % mod);
    }

    private long rev(long n) {
        long res = 0;
        while (n > 0) {
            long mod = n % 10;
            res = res * 10 + mod;
            n /= 10;
        }
        return res;
    }
}
