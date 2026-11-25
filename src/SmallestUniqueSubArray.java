import java.util.HashMap;
import java.util.Map;

public class SmallestUniqueSubArray {
    // LC 3934, daily redo 2026-11-24 (first written 2026-05-17), 2 submissions (first TLE).
    // Monotone: if every subarray of length v appears at least twice, so does every shorter one (it sits inside
    // a repeated length-v one), so binary search the smallest length that has a subarray appearing exactly once.
    // Check: double rolling hash (two mods packed into one long key) + count per key.
    // Note: base 1007 is below the value range; a base above max(a) (the 2026-05-17 version used 100003) is safer.
    public int smallestUniqueSubarray(int[] a) {
        int n = a.length;
        int l = 1;
        int u = n;
        while (l <= u) {
            int mid = l + (u - l) / 2;
            if (alldupe(a, mid)) {
                l = mid + 1;
            } else {
                u = mid - 1;
            }
        }
        return l;
    }

    private long base = 1007;
    private long mod1 = 1_000_000_007L;
    private long mod2 = 1_000_000_009L;

    private boolean alldupe(int[] a, int len) {
        int n = a.length;
        Map<Long, Long> m = new HashMap<>();
        long cur1 = 0;
        long cur2 = 0;
        long multibase1 = 1;
        long multibase2 = 1;
        for (int i = 0; i < len - 1; ++i) {
            multibase1 *= base;
            multibase1 %= mod1;
            multibase2 *= base;
            multibase2 %= mod2;
        }

        for (int i = 0; i < n; ++i) {
            int cind = a[i];
            cur1 = cur1 * base + cind;
            cur1 %= mod1;
            cur2 = cur2 * base + cind;
            cur2 %= mod2;

            int head = i - len + 1;
            if (head < 0) {
                continue;
            }
            long key = (cur1 << 32) ^ cur2;
            long nv = m.getOrDefault(key, 0L) + 1;
            m.put(key, nv);

            int headind = a[head];
            cur1 -= multibase1 * headind;
            cur1 %= mod1;
            if (cur1 < 0) {
                cur1 += mod1;
            }
            cur2 -= multibase2 * headind;
            cur2 %= mod2;
            if (cur2 < 0) {
                cur2 += mod2;
            }
        }
        for (long key : m.keySet()) {
            if (m.get(key) == 1) {
                return false;
            }
        }
        return true;
    }
}
