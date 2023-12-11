import java.util.*;

public class CountNumberOfGoodPartitions {
    // treat as interval problem, count number of non overlapping intervals. then count the number of subsets of n
    private Map<Integer, Integer> ms = new HashMap<>();
    private Map<Integer, Integer> me = new HashMap<>();
    private List<int[]> list = new ArrayList<>();
    private long Mod = (long) (1e9 + 7);

    public int numberOfGoodPartitions(int[] a) {
        int n = a.length;

        for (int i = 0; i < n; ++i) {
            me.put(a[i], i);
            ms.putIfAbsent(a[i], i);
        }
        for (int k : me.keySet()) {
            int start = ms.get(k);
            int end = me.get(k);
            list.add(new int[]{start, end});
        }
        Collections.sort(list, (x, y) -> Integer.compare(x[0], y[0]));
        int i = 0;
        int res = 0;
        while (i < list.size()) {
            int cend = list.get(i)[1];
            int j = i + 1;
            while (j < list.size() && list.get(j)[0] <= cend) {
                cend = Math.max(cend, list.get(j)[1]);
                ++j;
            }
            // i..j-1
            ++res;
            i = j;
        }
        return (int) pow2(res - 1);
    }

    private long pow2(long p) {
        if (p == 0) {
            return 1L;
        }
        long half = pow2(p / 2);
        long res = half * half;
        res %= Mod;
        if (p % 2 == 1) {
            res *= 2;
            res %= Mod;
        }
        return res;
    }
}
