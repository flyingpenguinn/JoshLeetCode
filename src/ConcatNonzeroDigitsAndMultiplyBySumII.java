import java.util.ArrayList;
import java.util.List;

public class ConcatNonzeroDigitsAndMultiplyBySumII {
    // pow10 must be done with binary trick and mod!
    private long Mod = (long) (1e9 + 7);

    public int[] sumAndMultiply(String s, int[][] qs) {
        char[] c = s.toCharArray();
        int n = c.length;
        long[] a = new long[n];
        for (int i = 0; i < n; ++i) {
            a[i] = c[i] - '0';
        }
        List<Integer> pos = new ArrayList<>();
        long[] sumdig = new long[n];
        long[] concat = new long[n];

        long ccnum = 0;
        for (int i = 0; i < n; ++i) {
            sumdig[i] = (i == 0 ? 0 : sumdig[i - 1]) + a[i];
            sumdig[i] %= Mod;
            if (a[i] != 0) {
                ccnum = ccnum * 10 + a[i];
                ccnum %= Mod;
                pos.add(i);
            }
            concat[i] = ccnum;
        }
        int[] res = new int[qs.length];
        int ri = 0;
        for (int[] q : qs) {
            int start = q[0];
            int end = q[1];
            int idstart = binaryfloor(pos, start - 1);
            int idend = binaryfloor(pos, end);
            if (idend == -1) {
                res[ri++] = 0;
            } else if (idstart == -1) {
                long cres = concat[pos.get(idend)] * sumdig[pos.get(idend)];
                cres %= Mod;
                res[ri++] = (int) cres;
            } else {
                long part2len = idend - idstart;
                long part1multi = pow10(part2len);
                long cpart1 = concat[pos.get(idstart)] * part1multi;
                cpart1 %= Mod;
                long cpart2 = concat[pos.get(idend)];
                long concatnow = cpart2 - cpart1;
                concatnow %= Mod;
                if (concatnow < 0) {
                    concatnow += Mod;
                }
                long csum = sumdig[pos.get(idend)] - sumdig[pos.get(idstart)];
                csum %= Mod;
                if (csum < 0) {
                    csum += Mod;
                }
                long cres = concatnow * csum;
                cres %= Mod;
                res[ri++] = (int) cres;
            }
        }
        return res;
    }

    private long pow10(long part2len) {
        if (part2len == 0) {
            return 1L;
        }
        long half = pow10(part2len / 2);
        long cres = half * half;
        cres %= Mod;
        if (part2len % 2 == 1) {
            cres *= 10;
            cres %= Mod;
        }
        return cres;
    }

    private int binaryfloor(List<Integer> a, int t) {
        int l = 0;
        int u = a.size() - 1;
        while (l <= u) {
            int mid = l + (u - l) / 2;
            if (a.get(mid) <= t) {
                l = mid + 1;
            } else {
                u = mid - 1;
            }
        }
        return u;
    }
}
