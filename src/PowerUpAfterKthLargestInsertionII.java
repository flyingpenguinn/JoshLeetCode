import base.ArrayUtils;

import java.util.*;

public class PowerUpAfterKthLargestInsertionII {
    // dynamic larget k where k can change. using bit already does counting so no need to count separately
    private int[] bit;

    private void u(int i) {
        while (i < bit.length) {
            ++bit[i];
            i += i & (-i);
        }
    }

    private int q(int i) {
        int res = 0;
        while (i > 0) {
            res += bit[i];
            i -= i & (-i);
        }
        return res;
    }

    public List<Integer> powerUpdate(int[] a, int p, int[][] queries) {
        int n = a.length;
        int qn = queries.length;
        int[] all = new int[n + qn];
        int aiindex = 0;
        for (int ai : a) {
            all[aiindex++] = ai;
        }
        for (int[] q : queries) {
            int cv = q[0];
            all[aiindex++] = cv;
        }
        Arrays.sort(all);
        int rank = 1;
        Map<Integer, Integer> rm = new HashMap<>();
        Map<Integer, Integer> rrm = new HashMap<>();
        int i = 0;
        while (i < all.length) {
            int j = i;
            while (j < all.length && all[i] == all[j]) {
                ++j;
            }
            rrm.put(rank, all[i]);
            rm.put(all[i], rank);
            ++rank;
            i = j;
        }
        bit = new int[rank];
        List<Integer> res = new ArrayList<>();
        for (int ai : a) {
            u(rm.get(ai));
        }
        int count = a.length;
        long cp = p;
        for (int[] q : queries) {
            int cv = q[0];
            int crank = rm.get(cv);
            u(crank);
            ++count;
            int ck = count - q[1] + 1;

            int l = 1;
            int u = rank;
            while (l <= u) {
                int mid = l + (u - l) / 2;
                int pos = q(mid);
                if (pos >= ck) {
                    u = mid - 1;
                } else {
                    l = mid + 1;
                }
            }
            int x = rrm.get(l);
            cp = solvepow(cp, x);

            res.add((int) cp);
        }
        return res;
    }

    private long Mod = (long) (1e9 + 7);

    private long solvepow(long p, long x) {
        if (x == 0) {
            return 1L;
        }
        long half = solvepow(p, x / 2);
        long hres = half * half;
        hres %= Mod;
        if (x % 2 == 1) {
            hres *= p;
            hres %= Mod;
        }
        return hres;
    }

    static void main() {
      //  System.out.println(new PowerUpAfterKthLargestInsertionII().powerUpdate(ArrayUtils.read1d("[2]"), 4, ArrayUtils.read("[[3,1],[1,2]]")));
        System.out.println(new PowerUpAfterKthLargestInsertionII().powerUpdate(ArrayUtils.read1d("[7,5]"), 6, ArrayUtils.read("[[4,3],[7,1]]")));
    }
}
