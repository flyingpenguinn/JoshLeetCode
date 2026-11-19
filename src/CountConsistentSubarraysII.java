import java.util.*;

public class CountConsistentSubarraysII {
    // Weekly 528 Q4, redo 2026-11-18 with the log-values approach (the earlier wall + representative version is in git history).
    // Fix r. As l moves left, AND(l..r) only loses bits, so it takes <= ~30 values and each value is one contiguous range of l.
    // AND == min  <=>  the value AND itself appears in a[l..r]  <=>  last[AND] >= l.
    // So for a segment [from, to] with value k, the valid l are [from, min(to, last[k])].
    // Two old segments landing on the same new value are adjacent (AND only loses bits), so they merge into one range.
    // Merge with min(from) / max(to) so it does not depend on iteration order. An earlier version merged as
    // (first seen from, later seen to) with a HashMap: [65536, 65537, 0, 0] gave 9 instead of 10 (hash order != position order).
    public long countConsistent(int[] a) {
        int n = a.length;
        // v-> l from, to
        Map<Integer, int[]> cnt = new HashMap<>();
        Map<Integer, Integer> last = new HashMap<>();
        long res = 0;
        for (int i = 0; i < n; ++i) {
            int v = a[i];
            Map<Integer, int[]> ncnt = new HashMap<>();
            ++res;
            last.put(v, i);
            for (int k : cnt.keySet()) {
                var pair = cnt.get(k);
                int from = pair[0];
                int to = pair[1];
                int nk = k & v;
                putit(ncnt, nk, from, to);
                if (last.containsKey(nk)) {
                    int lp = last.get(nk);
                    if (lp > to) {
                        res += to - from + 1;
                    } else if (lp >= from) {
                        res += lp - from + 1;
                    }
                }

            }
            putit(ncnt, v, i, i);
            cnt = ncnt;
        }
        return res;
    }

    private static void putit(Map<Integer, int[]> ncnt, int v, int from, int to) {
        int[] old = ncnt.get(v);
        if (old != null) {
            int oldfrom = old[0];
            int oldto = old[1];
            ncnt.put(v, new int[]{Math.min(oldfrom, from), Math.max(oldto, to)});
        } else {
            ncnt.put(v, new int[]{from, to});
        }
    }

    private static long brute(int[] a) {
        long r = 0;
        for (int i = 0; i < a.length; ++i) {
            int and = -1, mn = Integer.MAX_VALUE;
            for (int j = i; j < a.length; ++j) {
                and &= a[j];
                mn = Math.min(mn, a[j]);
                if (and == mn) {
                    ++r;
                }
            }
        }
        return r;
    }

    public static void main(String[] args) {
        CountConsistentSubarraysII sol = new CountConsistentSubarraysII();
        System.out.println(sol.countConsistent(new int[]{5, 3, 1, 2})); // 6
        System.out.println(sol.countConsistent(new int[]{1, 1, 1})); // 6
        System.out.println(sol.countConsistent(new int[]{0})); // 1
        System.out.println(sol.countConsistent(new int[]{65536, 65537, 0, 0})); // 10
        Random rnd = new Random(528);
        int bad = 0;
        for (int t = 0; t < 5000; ++t) {
            int n = 1 + rnd.nextInt(10);
            int[] a = new int[n];
            for (int i = 0; i < n; ++i) {
                a[i] = rnd.nextBoolean() ? rnd.nextInt(16) : rnd.nextInt(1 << 30);
            }
            if (sol.countConsistent(a) != brute(a)) {
                ++bad;
            }
        }
        System.out.println("bad=" + bad);
    }
}
