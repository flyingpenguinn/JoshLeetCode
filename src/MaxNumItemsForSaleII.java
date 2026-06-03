import base.ArrayUtils;

import java.util.Arrays;

public class MaxNumItemsForSaleII {
    // buying each item only gives us 1 or 2. if buy 1 we'd buy the cheapest.

    public int maximumSaleItems(int[][] a, int b) {
        int n = a.length;
        int[] cnt = new int[n + 1];
        int[] divcnt = new int[n + 1];
        for (int i = 0; i < n; ++i) {
            int cf = a[i][0];
            ++cnt[cf];
        }
        for (int i = 1; i <= n; ++i) {
            for (int j = i; j <= n; j += i) {
                divcnt[i] += cnt[j];
            }
        }
        Arrays.sort(a, (x, y) -> Integer.compare(x[1], y[1]));
        int minprice = a[0][1];
        int res = 0;
        for (int i = 0; i < n; ++i) {
            int fv = a[i][0];
            int other = divcnt[fv] - 1;
            if (other == 0) {
                continue;
            }
            int pv = a[i][1];
            if (pv >= 2 * minprice) {
                break;
            }
            int tobuy = Math.min(b / pv, other);
            res += 2 * tobuy;
            b -= tobuy * pv;
        }
        res += b / minprice;
        return res;
    }

    static void main() {
        System.out.println(new MaxNumItemsForSaleII().maximumSaleItems(ArrayUtils.read("[[2,8],[1,10],[6,6],[4,12],[5,20],[5,17]]"), 35));
    }
}
