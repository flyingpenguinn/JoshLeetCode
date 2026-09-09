import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

public class DeliverBoxesFromStorageToPorts {
    private void update(Map<Integer, Integer> m, int k, int d) {
        int nv = m.getOrDefault(k, 0) + d;
        if (nv <= 0) {
            m.remove(k);
        } else {
            m.put(k, nv);
        }
    }

    public int boxDelivering(int[][] a, int portsCount, int maxBoxes, int maxWeight) {
        int n = a.length;

        int[] blocks = new int[n];
        blocks[0] = 0;
        for (int i = 1; i < n; ++i) {
            if (a[i][0] != a[i - 1][0]) {
                blocks[i] = blocks[i - 1] + 1;
            } else {
                blocks[i] = blocks[i - 1];
            }
        }
        int[] right = new int[n];
        Arrays.fill(right, -1);
        int i = 0;
        int j = 0;
        int cb = 0;
        int cw = 0;
        for (i = 0; i < n; ++i) {
            while (j < n && cb + 1 <= maxBoxes && cw + a[j][1] <= maxWeight) {
                cb += 1;
                cw += a[j][1];
                ++j;
            }
            right[i] = j - 1;
            cb -= 1;
            cw -= a[i][1];
        }
        int[] dp = new int[n + 1];
        TreeMap<Integer, Integer> m = new TreeMap<>();
        j = n - 1;

        for (i = n - 1; i >= 0; --i) {
            int end = right[i];
            while (j > end) {
                int v = blocks[j] + dp[j + 1];
                update(m, v, -1);
                --j;
            }
            dp[i] = 2 + dp[i + 1];
            if (!m.isEmpty()) {
                int minv = m.firstKey();
                int cur = minv - blocks[i] + 2;
                dp[i] = Math.min(dp[i], cur);
            }
            int cv = blocks[i] + dp[i + 1];
            update(m, cv, 1);
        }
        return dp[0];
    }
}
