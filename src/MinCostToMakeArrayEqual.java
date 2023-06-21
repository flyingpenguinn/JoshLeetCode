import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class MinCostToMakeArrayEqual {
    // we scan from the first to the last and figure out the value for current one based on what we already have
    public long minCost(int[] ia, int[] ib) {
        int n = ia.length;
        long[][] a = new long[n][2];
        for (int i = 0; i < n; ++i) {
            a[i][0] = ia[i];
            a[i][1] = ib[i];
        }

        Arrays.sort(a, (x, y) -> Long.compare(x[0], y[0]));

        long afteraccu = 0;
        long aftercostsum = 0;
        for (int i = 0; i < n; ++i) {
            afteraccu += a[i][0] * a[i][1];
            aftercostsum += a[i][1];
        }

        long beforeaccu = 0;
        long beforecostsum = 0;
        long res = (long) 2e18;

        for (int i = 0; i < n; ++i) {
            afteraccu -= a[i][0] * a[i][1];
            aftercostsum -= a[i][1];

            long beforesum = a[i][0] * beforecostsum - beforeaccu;
            long aftersum = afteraccu - aftercostsum * a[i][0];

            long cur = beforesum + aftersum;
            res = Math.min(res, cur);

            beforeaccu += a[i][0] * a[i][1];
            beforecostsum += a[i][1];
        }

        return res;
    }
}
