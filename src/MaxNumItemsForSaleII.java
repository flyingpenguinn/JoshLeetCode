import java.util.Arrays;

public class MaxNumItemsForSaleII {


    public int maximumSaleItems(int[][] a, int b) {
        int n = a.length;
        int[] cnt = new int[n + 1];
        int[] divcnt = new int[n + 1];

        for (int i = 0; i < n; ++i) {
            int v = a[i][0];
            ++cnt[v];
        }

        for (int f = 1; f <= n; ++f) {
            for (int m = f; m <= n; m += f) {
                divcnt[f] += cnt[m];
            }
        }

        Arrays.sort(a, (x, y) -> Integer.compare(x[1], y[1]));

        long budget = b;
        long res = 0;
        long mn = a[0][1];

        for (int[] item : a) {
            long price = item[1];
            int factor = item[0];

            if (price >= 2 * mn) {
                break;
            }

            long deg = divcnt[factor] - 1L;
            if (deg <= 0) {
                continue;
            }

            long take = Math.min(deg, budget / price);
            res += take * 2;
            budget -= take * price;
        }

        res += budget / mn;
        return (int) res;
    }
}
