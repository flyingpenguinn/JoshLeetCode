import java.util.Arrays;

public class MaxWhiteTilesCovered {
    public int maximumWhiteTiles(int[][] a, int carpetLen) {
        Arrays.sort(a, (x, y) -> Integer.compare(x[0], y[0]));
        int n = a.length;
        int[] sum = new int[n];
        sum[0] = a[0][1] - a[0][0] + 1;
        int[] len = new int[n];
        for (int i = 1; i < n; ++i) {
            sum[i] = sum[i - 1] + a[i][1] - a[i][0] + 1;
        }
        int res = 0;
        for (int i = 0; i < n; ++i) {
            int pos = binary(a, a[i][0] + carpetLen - 1);
            int cur = 0;

            cur = (pos == 0 ? 0 : sum[pos - 1]) - (i == 0 ? 0 : sum[i - 1]);
            int rem = carpetLen - (a[pos][0] - a[i][0]);
            if (rem > 0) {
                int added = Math.min(rem, a[pos][1] - a[pos][0] + 1);
                cur += added;
            }

            res = Math.max(res, cur);
        }
        return res;
    }

    private int binary(int[][] a, int t) {
        int l = 0;
        int u = a.length - 1;
        while (l <= u) {
            int mid = l + (u - l) / 2;
            if (a[mid][0] > t) {
                u = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return u;
    }
}
