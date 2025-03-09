import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MaxSubarraysAfterRemovingOneConflictintPair {

    public long maxSubarrays(int n, int[][] pairs) {
        int m = pairs.length;

        // col[i]：元素 i 有哪些记号
        @SuppressWarnings("unchecked")
        List<Integer>[] col = new ArrayList[n + 1];
        for (int i = 0; i <= n; i++) {
            col[i] = new ArrayList<>();
        }
        for (int i = 0; i < m; i++) {
            // pair[i] = [a, b]
            // In the original C++ code, we store "i+1" as the "marker ID"
            // to indicate that this conflicting pair is marker (i+1).
            int a = pairs[i][0];
            int b = pairs[i][1];
            col[a].add(i + 1);
            col[b].add(i + 1);
        }

        // f[i]：以下标 i 为结尾的，不含重复记号的子数组最长是多少
        // g[i]：以下标 i 为结尾的，含一种重复记号的子数组最长是多少
        // h[i]：重复记号具体是哪个（如果没有重复则为 0）
        int[] f = new int[n + 1];
        int[] g = new int[n + 1];
        long[] h = new long[n + 1];  // h[i] can store "marker ID," which is up to m

        // 双指针求不含重复记号的子数组
        int[] cnt = new int[m + 1];  // 统计每个记号出现次数
        Arrays.fill(cnt, 0);
        int bad = 0;   // 记录有多少种记号重复出现
        for (int i = 1, j = 1; i <= n; i++) {
            for (int x : col[i]) {
                int t = ++cnt[x];
                if (t == 2) {
                    bad++;
                }
            }
            while (j <= i && bad > 0) {
                for (int x : col[j]) {
                    int t = --cnt[x];
                    if (t == 1) {
                        bad--;
                    }
                }
                j++;
            }
            f[i] = i - j + 1;
        }

        // 双指针求最多含一种重复记号的子数组
        Arrays.fill(cnt, 0);
        bad = 0;
        long sm = 0;  // 记录重复记号的编号（只有一种重复时就等于该记号编号；没有或多于一种则会通过窗口调整）
        for (int i = 1, j = 1; i <= n; i++) {
            for (int x : col[i]) {
                int t = ++cnt[x];
                if (t == 2) {
                    bad++;
                    sm += x;  // 因为只会有一种重复记号，这里加上 x
                }
            }
            while (j <= i && bad > 1) {
                for (int x : col[j]) {
                    int t = --cnt[x];
                    if (t == 1) {
                        bad--;
                        sm -= x;  // 去掉重复
                    }
                }
                j++;
            }
            g[i] = i - j + 1;
            // 如果子数组里没有重复记号，h[i] 就是 0；如果正好有一种重复记号，h[i] 就是那个记号编号
            h[i] = sm;
        }

        // val[i]：重复记号 i 对答案的贡献
        long[] val = new long[m + 1];
        Arrays.fill(val, 0L);

        long base = 0;  // base = 所有只含“不含重复记号”的子数组之和
        for (int i = 1; i <= n; i++) {
            base += f[i];
            // 如果 h[i] = 某个记号 k，则 val[k] += (g[i] - f[i])
            // 表示：如果允许重复记号 k，那么这段子数组可以放宽到长度 g[i]
            val[(int) h[i]] += (g[i] - f[i]);
        }

        long best = 0;
        // 选择保留哪种重复记号（在所有 val[i] 中取最大值）
        for (int i = 1; i <= m; i++) {
            best = Math.max(best, val[i]);
        }
        return base + best;
    }

}
