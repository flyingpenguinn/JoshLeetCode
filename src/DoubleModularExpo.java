import java.util.ArrayList;
import java.util.List;

public class DoubleModularExpo {
    public List<Integer> getGoodIndices(int[][] a, int t) {
        List<Integer> res = new ArrayList<>();
        int n = a.length;
        for (int i = 0; i < n; ++i) {
            int[] q = a[i];
            long ai = q[0];
            long bi = q[1];
            long ci = q[2];
            long mi = q[3];
            long p1 = pow(ai, bi, 10);
            long p2 = pow(p1, ci, mi);
            if (p2 == t) {
                res.add(i);
            }
        }
        return res;
    }

    private long pow(long a, long b, long m) {
        if (b == 0) {
            return 1L;
        }
        long half = pow(a, b / 2, m);
        long cur = half * half;
        cur %= m;
        if (b % 2 == 1) {
            cur *= a;
            cur %= m;
        }
        return cur;
    }
}
