import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InverseCoinChanges {
    public List<Integer> findCoins(int[] a) {
        int n = a.length;
        List<Integer> res = new ArrayList<>();
        int[] old = new int[n + 1];
        for (int i = 1; i <= n; ++i) {
            if (a[i - 1] == 0) {
                continue;
            }
            int[] cnt = Arrays.copyOf(old, n + 1);
            cnt[i] += 1;
            for (int j = 1; j <= n; ++j) {
                if (cnt[j] == 0) {
                    continue;
                }
                int added = j + i;
                if (added <= n) {
                    cnt[added] += cnt[j];
                }
            }
            boolean bad = false;
            for (int j = 1; j <= n; ++j) {
                if (cnt[j] > a[j - 1]) {
                    bad = true;
                    break;
                }
            }
            if (!bad) {
                res.add(i);
                old = cnt;
            }
        }
        for (int j = 1; j <= n; ++j) {
            if (old[j] != a[j - 1]) {
                return new ArrayList<>();
            }
        }
        return res;
    }
}
