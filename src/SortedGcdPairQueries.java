import base.ArrayUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SortedGcdPairQueries {
    // enumerate based on divisors
    public int[] gcdValues(int[] ia, long[] qs) {
        int n = ia.length;
        long[] a = new long[n];
        long maxi = 0;
        for (int i = 0; i < n; ++i) {
            a[i] = ia[i];
            maxi = Math.max(maxi, ia[i]);
        }
        long[] freq = new long[(int) (maxi + 1)];
        for (int i = 0; i <n; ++i) {
            ++freq[(int) a[i]];
        }
        long[] divby = new long[(int) (maxi + 1)];
        for (int i = 1; i <= maxi; ++i) {
            for (int j = i; j <= maxi; j += i) {
                divby[i] += freq[j];
            }
        }
        // pairs whose gcd >=i
        long[] greaterdiv = new long[(int) (maxi + 1)];
        for (int i = 1; i <= maxi; ++i) {
            if (divby[i] > 1) {
                long cur = divby[i];
                greaterdiv[i] = cur * (cur - 1) / 2;
            }
        }
        long[] exact = new long[(int) (maxi + 1)];
        for (int i = (int) maxi; i >= 1; --i) {
            exact[i] = greaterdiv[i];
            for (int j = 2 * i; j <= maxi; j += i) {
                exact[i] -= exact[j];
            }
        }
        List<long[]> sumexact = new ArrayList<>();
        long sum = 0;
        for (int i = 1; i <= maxi; ++i) {
            if (exact[i] == 0) {
                continue;
            }
            sum += exact[i];
            sumexact.add(new long[]{sum, i});
        }
        int[] res = new int[qs.length];
        int ri = 0;
        for (long q : qs) {
            int pos = binaryfirstnonsmaller(sumexact, q + 1);
            res[ri++] = (int) sumexact.get(pos)[1];
        }
        return res;
    }

    private int binaryfirstnonsmaller(List<long[]> a, long q) {
        int l = 0;
        int u = a.size() - 1;
        while (l <= u) {
            int mid = l + (u - l) / 2;
            if (a.get(mid)[0] >= q) {
                u = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new SortedGcdPairQueries().gcdValues(ArrayUtils.read1d("2,3,4"), new long[]{0, 2, 2})));
    }
}
