import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MinCostToMakeArrayPalindromic {
    // we can also check the closest palindrome of the median and use that value
    private static List<Long> allpalin = new ArrayList<>();

    public long minimumCost(int[] a) {
        if (allpalin.isEmpty()) {
            for (int len = 1; len <= 9; ++len) {
                gen(0, 0, len / 2, len % 2 == 1, 1L, true);
            }
            Collections.sort(allpalin);
        }
        Arrays.sort(a);
        int n = a.length;
        long[] prefix = new long[n];
        prefix[0] = a[0];
        for (int i = 1; i < n; ++i) {
            prefix[i] = prefix[i - 1] + a[i];
        }
        long res = (long) 1e18;
        boolean seenbigger = false;
        for (int i = 0; i < allpalin.size(); ++i) {
            long cur = allpalin.get(i);
            if (cur > a[n - 1]) {
                if (seenbigger) {
                    break;
                } else {
                    seenbigger = true;
                }
            }
            int pos = binary(a, cur);
            // the last num <= cur
            long cres = 0;
            if (pos >= 0) {
                long part1 = (pos + 1) * cur - prefix[pos];
                long part2 = prefix[n - 1] - prefix[pos] - (n - pos - 1) * cur;
                cres = part1 + part2;
            } else {
                cres = prefix[n - 1] - n * cur;
            }

            res = Math.min(res, cres);
        }
        return res;
    }

    private int binary(int[] a, long t) {
        int l = 0;
        int u = a.length - 1;
        while (l <= u) {
            int mid = l + (u - l) / 2;
            if (a[mid] <= t) {
                l = mid + 1;
            } else {
                u = mid - 1;
            }
        }
        return u;
    }

    private void gen(long start, long end, int remlen, boolean odd, long base, boolean first) {
        int sn = first ? 1 : 0;
        if (remlen == 0) {
            if (odd) {
                for (int i = sn; i <= 9; ++i) {
                    long cur = start * base * 10 + i * base + end;
                    if (cur > 0) {
                        allpalin.add(cur);
                    }
                }
            } else {
                long cur = start * base + end;
                allpalin.add(cur);
            }
            return;
        }
        for (int i = sn; i <= 9; ++i) {
            gen(start * 10 + i, i * base + end, remlen - 1, odd, base * 10, false);
        }
    }
}

class MinCostArrayEquipalindromII {
    // get median and get its closest palindrome. here we are using brute force
    public long minimumCost(int[] a) {
        Arrays.sort(a);
        int n = a.length;
        int median = a[n / 2];
        int cand1 = upper(median);
        int cand2 = lower(median);
        return Math.min(calc(a, cand1), calc(a, cand2));
    }

    private long calc(int[] a, int cand) {
        long res = 0;
        for (int ai : a) {
            res += Math.abs(cand - ai);
        }
        return res;
    }

    private int upper(int t) {
        while (!ispalin(t)) {
            ++t;
        }
        return t;
    }

    private int lower(int t) {
        while (!ispalin(t)) {
            --t;
        }
        return t;
    }

    private boolean ispalin(int n) {
        int on = n;
        int rev = 0;
        while (n > 0) {
            rev = rev * 10 + (n % 10);
            n /= 10;
        }

        return rev == on;
    }
}
