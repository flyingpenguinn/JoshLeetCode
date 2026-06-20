import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MinLightsToIlluminateRoad {
    private int div3(int n) {
        return (int) Math.ceil(n * 1.0 / 3);
    }

    public int minLights(int[] a) {
        int n = a.length;
        List<int[]> v = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            if (a[i] == 0) {
                continue;
            }
            int start = Math.max(0, i - a[i]);
            int end = Math.min(n - 1, i + a[i]);
            v.add(new int[]{start, end});
        }
        Collections.sort(v, (x, y) -> Integer.compare(x[0], y[0]));
        int vn = v.size();
        if (vn == 0) {
            return div3(n);
        }
        int start = v.get(0)[0];
        int end = v.get(0)[1];
        int cur = 0;
        int res = 0;
        if (start > 0) {
            int gap = div3(start);
            res += gap;
        }
        for (int i = 1; i < vn; ++i) {
            int cs = v.get(i)[0];
            int ce = v.get(i)[1];
            if (cs > end + 1) {
                int cgap = cs - end - 1;
                int gap = div3(cgap);
                res += gap;
                end = ce;
            } else {
                end = Math.max(ce, end);
            }
        }
        if (end < n - 1) {
            int cgap = n - 1 - end;
            int gap = div3(cgap);
            res += gap;
        }
        return res;
    }
}
