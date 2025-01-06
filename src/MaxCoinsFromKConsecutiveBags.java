import base.ArrayUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class MaxCoinsFromKConsecutiveBags {

    public long maximumCoins(int[][] ia, int k) {
        int n = ia.length;
        long[][] a = new long[n][3];
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < a[i].length; ++j) {
                a[i][j] = ia[i][j];
            }
        }
        Arrays.sort(a, (x, y) -> Long.compare(x[0], y[0]));
        int i = 0;
        int j = 0;
        long csum = 0;
        long res = 0;
        // starting at each interval start
        while (i < n) {
            while (j < n && a[j][1] <= a[i][0] + k - 1) {
                csum += (a[j][1] - a[j][0] + 1) * a[j][2];
                ++j;
            }
            long cursum = csum;
            if (j < n) {
                // calced till j-1
                long len = a[i][0] + k - 1 - a[j][0] + 1;
                if (len > 0) {
                    long extra = len * a[j][2];
                    cursum += extra;
                }
            }
            res = Math.max(res, cursum);
            csum -= (a[i][1] - a[i][0] + 1) * a[i][2];
            ++i;
        }
        Arrays.sort(a, (x, y) -> Long.compare(x[1], y[1]));
        i = n - 1;
        csum = 0;
        j = n - 1;
        // ending at each interval end
        while (i >= 0) {
            while (j >= 0 && a[j][0] >= a[i][1] - k + 1) {
                csum += (a[j][1] - a[j][0] + 1) * a[j][2];
                --j;
            }
            long cursum = csum;
            if (j >= 0) {
                // caled till j+1
                long len = a[j][1] - (a[i][1] - k + 1) + 1;
                if (len > 0) {
                    long extra = len * a[j][2];
                    cursum += extra;
                }
            }
            res = Math.max(res, cursum);
            csum -= (a[i][1] - a[i][0] + 1) * a[i][2];
            --i;
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new MaxCoinsFromKConsecutiveBags().maximumCoins(ArrayUtils.read("[[34,37,19],[44,46,10],[17,26,14],[6,10,13]]"), 14));
    }

}
