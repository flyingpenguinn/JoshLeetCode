import java.util.ArrayList;
import java.util.List;

public class LexMaxMexArray {
    public int[] maximumMEX(int[] a) {
        int n = a.length;
        int maxv = 0;
        for (int ai : a) {
            maxv = Math.max(maxv, ai);
        }
        maxv = Math.max(2 * n, maxv);
        int[] suf = new int[n + 1];
        boolean[] seen = new boolean[maxv + 1];
        int mex = 0;

        for (int i = n - 1; i >= 0; --i) {
            if (a[i] <= n) {
                seen[a[i]] = true;
            }
            while (seen[mex]) {
                ++mex;
            }
            suf[i] = mex;
        }

        List<Integer> res = new ArrayList<>();
        int i = 0;

        while (i < n) {
            int target = suf[i];

            if (target == 0) {
                res.add(0);
                ++i;
                continue;
            }

            boolean[] have = new boolean[target];
            int cnt = 0;
            int j = i;

            while (j < n && cnt < target) {
                if (a[j] < target && !have[a[j]]) {
                    have[a[j]] = true;
                    ++cnt;
                }
                ++j;
            }

            res.add(target);
            i = j;
        }

        int[] rres = new int[res.size()];
        for (i = 0; i < res.size(); ++i) {
            rres[i] = res.get(i);
        }
        return rres;
    }
}
