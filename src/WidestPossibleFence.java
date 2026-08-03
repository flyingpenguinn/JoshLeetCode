import base.ArrayUtils;

import java.util.HashMap;
import java.util.Map;

public class WidestPossibleFence {
    // value range too large? convert to freqency map and count
    public int maximumWidth(int[] a) {
        Map<Integer, Integer> f = new HashMap<>();
        int n = a.length;
        Map<Integer, Integer> rm = new HashMap<>();
        for (int i = 0; i < n; ++i) {
            f.put(a[i], f.getOrDefault(a[i], 0) + 1);
            rm.put(a[i], rm.getOrDefault(a[i], 0) + 1);
        }
        for (int v1 : f.keySet()) {
            int c1 = f.get(v1);
            for (int v2 : f.keySet()) {
                int c2 = f.get(v2);
                if (v1 > v2) {
                    continue;
                }
                int ccnt = 0;
                int csum = 0;
                if (v1 != v2) {
                    ccnt = Math.min(c1, c2);
                    csum = v1 + v2;
                } else {
                    ccnt = (c1) / 2;
                    csum = v1 + v2;
                }
                rm.put(csum, rm.getOrDefault(csum, 0) + ccnt);
            }
        }
        int res = 0;
        for (int rk : rm.keySet()) {
            res = Math.max(res, rm.get(rk));
        }
        return res;
    }

    static void main() {
        System.out.println(new WidestPossibleFence().maximumWidth(ArrayUtils.read1d("[1,3,2,5,7,5,4,2,1]")));
        System.out.println(new WidestPossibleFence().maximumWidth(ArrayUtils.read1d("2,3,7")));
    }
}
