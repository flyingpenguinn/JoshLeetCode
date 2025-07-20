import java.util.HashMap;
import java.util.Map;

public class CountNumberOfTrapezoidsI {
    private long Mod = (long) (1e9 + 7);

    public int countTrapezoids(int[][] a) {
        int m = a.length;
        Map<Integer, Long> cnt = new HashMap<>();
        for (int[] p : a) {
            int x = p[0];
            int y = p[1];
            cnt.put(y, cnt.getOrDefault(y, 0L) + 1L);
        }
        long all = 0;
        for (int k : cnt.keySet()) {
            long cur = cnt.get(k);
            long ccombi = cur * (cur - 1) / 2;
            all += ccombi;
        }
        long res = 0;
        for (int k : cnt.keySet()) {
            long cur = cnt.get(k);
            long ccombi = cur * (cur - 1) / 2;
            long other = all - ccombi;
            long cres = ccombi * other;
            res += cres;
        }
        res /= 2;
        res %= Mod;
        return (int) res;
    }
}
