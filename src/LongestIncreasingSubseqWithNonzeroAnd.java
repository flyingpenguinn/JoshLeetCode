import java.util.ArrayList;
import java.util.List;

public class LongestIncreasingSubseqWithNonzeroAnd {
    public int longestSubsequence(int[] a) {
        int n = a.length;
        int res = 0;
        for (int j = 0; j < 32; ++j) {
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < n; ++i) {
                int v = a[i];
                if (((v >> j) & 1) == 1) {
                    list.add(v);
                }
            }
            int cur = lis(list);
            res = Math.max(res, cur);
        }
        return res;
    }

    private int lis(List<Integer> a) {
        int n = a.size();
        List<Integer> cur = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            int v = a.get(i);
            int pos = binary(cur, v);
            if (pos >= cur.size()) {
                cur.add(v);
            } else {
                cur.set(pos, v);
            }
        }
        return cur.size();
    }

    private int binary(List<Integer> a, int v) {
        int l = 0;
        int u = a.size() - 1;
        while (l <= u) {
            int mid = l + (u - l) / 2;
            if (a.get(mid) >= v) {
                u = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }
}
