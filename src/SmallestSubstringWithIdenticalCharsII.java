import java.util.ArrayList;
import java.util.List;

public class SmallestSubstringWithIdenticalCharsII {
    // special processing of len = 1
    public int minLength(String s, int numOps) {
        char[] c = s.toCharArray();
        int n = c.length;
        int[] a = new int[n];
        for (int i = 0; i < n; ++i) {
            a[i] = c[i] - '0';
        }
        int l = 1;
        int u = n;
        List<Integer> segs = new ArrayList<>();
        int i = 0;
        while (i < n) {
            int j = i;
            while (j < n && a[i] == a[j]) {
                ++j;
            }
            int len = j - i;
            segs.add(len);
            i = j;
        }
        while (l <= u) {
            int mid = l + (u - l) / 2;
            if (need(segs, a, mid) > numOps) {
                l = mid + 1;
            } else {
                u = mid - 1;
            }
        }
        return l;
    }

    private int need(List<Integer> segs, int[] a, int mid) {
        int n = a.length;
        if (mid == 1) {
            // if mid==1, compare with 010101 or 101010
            return Math.min(steps(a, 0), steps(a, 1));
        }
        int sn = segs.size();
        int res = 0;
        for (int i = 0; i < sn; ++i) {
            int si = segs.get(i);
            int needed = (si) / (mid + 1);
            res += needed;
        }
        return res;
    }

    private int steps(int[] a, int exp) {
        int n = a.length;
        int res = 0;
        for (int i = 0; i < n; ++i) {
            if (a[i] != exp) {
                ++res;
            }
            exp ^= 1;
        }
        return res;
    }
}
