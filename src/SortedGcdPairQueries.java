import java.util.ArrayList;
import java.util.List;

public class SortedGcdPairQueries {
    public int[] gcdValues(int[] a, long[] qs) {
        int n = a.length;
        int maxi = 0;
        for (int ai : a) {
            maxi = Math.max(maxi, ai);
        }
        long[] divbyi = new long[maxi + 1];
        long[] fre = new long[maxi + 1];
        long[] gcdgreater = new long[maxi + 1];
        long[] exact = new long[maxi + 1];
        for (int ai : a) {
            ++fre[ai];
        }
        for (int i = 1; i <= maxi; i++) {
            for (int j = i; j <= maxi; j += i)
                divbyi[i] += fre[j];
        }
        for (int i = 1; i <= maxi; i++) {
            if (divbyi[i] >= 1)
                gcdgreater[i] = (divbyi[i] * (divbyi[i] - 1)) / 2;
        }
        for (int i = maxi; i >= 1; i--) {
            exact[i] = gcdgreater[i];
            for (int j = i + i; j <= maxi; j += i)
                exact[i] -= exact[j];
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
}
