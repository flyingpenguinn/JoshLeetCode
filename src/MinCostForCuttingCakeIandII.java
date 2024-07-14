import java.util.Arrays;
import java.util.Collections;

public class MinCostForCuttingCakeIandII {
    public long minimumCost(int m, int n, int[] ihs, int[] ivs) {
        long res = 0;
        final int hn = ihs.length;
        Long[] hs = new Long[hn];
        for (int i = 0; i < hn; ++i) {
            hs[i] = Long.valueOf(ihs[i]);
        }
        final int vn = ivs.length;
        Long[] vs = new Long[vn];
        for (int i = 0; i < vn; ++i) {
            vs[i] = Long.valueOf(ivs[i]);
        }
        Arrays.sort(hs, Collections.reverseOrder());
        Arrays.sort(vs, Collections.reverseOrder());
        long hp = 1;
        long vp = 1;
        int i = 0, j = 0;
        while (i < hn && j < vn) {
            if (hs[i] > vs[j]) {
                res += hs[i] * vp;
                hp++;
                ++i;
            } else {
                res += vs[j] * hp;
                vp++;
                ++j;
            }
        }
        long total = 0;
        while (i < m - 1) {
            total += hs[i++];
        }
        res += total * vp;
        total = 0;
        while (j < n - 1) {
            total += vs[j++];
        }
        res += total * hp;
        return res;
    }
}
