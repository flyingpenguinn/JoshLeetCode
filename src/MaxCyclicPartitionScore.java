import java.util.Arrays;

public class MaxCyclicPartitionScore {
    // TODO Tough.. only 23 solved globally
    public long maximumScore(int[] nums, int K) {
        int n = nums.length;

        // 解法：DP（转化为买卖股票）
        // 先考虑序列不是循环的情况下怎么做。
        //
        // 首先，划分的子数组中，只有最大值和最小值是有意义的，其它值都没意义，所以问题可以转换为：选择至多 k 对下标 (l1,r1),(l2,r2),⋯,(lk,rk)，
        // 满足下标对之间不相交（即 l1<r1<l2<r2<⋯，对应子数组不重叠），求
        //   max Σ |a_{ri} − a_{li}|
        //
        // 绝对值也可以用 max 表示，即我们要求
        //   max Σ max(a_{ri} − a_{li}, a_{li} − a_{ri})
        //
        // 这个式子什么意思呢？如果我们把加 ai 看成以 ai 的价格买入一手股票，减 ai 看成以 ai 的价格卖出一手股票，
        // 这不就是 leetcode 3573. 买卖股票的最佳时机 V 吗？复杂度 O(n^2)。
        //
        // 不循环的情况下做完了，接下来考虑循环的情况。这种情况也不难，相当于我们从序列开头就有一个未关闭的交易，
        // 在 DP 中额外添加一维进行处理即可。详见参考代码。

        // flag == 0：一开始没有未关闭的交易
        // flag == 1：一开始已经有 1 手股票
        // flag == -1：一开始已经有 -1 手股票
        long a = gao(nums, K, 0);
        long b = gao(nums, K, 1);
        long c = gao(nums, K, -1);
        return Math.max(a, Math.max(b, c));
    }

    private long gao(int[] nums, int K, int flag) {
        int n = nums.length;
        final long INF = (long) 1e18;

        // f[i][k][x][y]：前 i 天进行了 k 次交易，且当前交易状态为 y 的最大利润
        // x == 0：还未处理初始交易
        // x == 1：已经处理了初始交易
        // y == 0：空仓；y == 1：持有 +1 手；y == 2：持有 -1 手
        long[][][][] f = new long[2][K + 1][2][3];

        for (int k = 0; k <= K; k++) {
            for (int x = 0; x < 2; x++) {
                Arrays.fill(f[0][k][x], -INF);
            }
        }
        f[0][0][0][0] = 0;
        if (K >= 1) {
            f[0][1][0][1] = nums[0];
            f[0][1][0][2] = -nums[0];
        }
        if (flag != 0) {
            f[0][0][1][0] = (long) nums[0] * flag;
        }

        for (int i = 1; i < n; i++) {
            int cur = i & 1;
            int pre = cur ^ 1;
            for (int k = 0; k <= K; k++) {
                for (int x = 0; x < 2; x++) {
                    Arrays.fill(f[cur][k][x], -INF);
                }
            }
            // 如果有初始交易的情况下，可以把第 i 天的股价作为初始价
            if (flag != 0) {
                f[cur][0][1][0] = (long) nums[i] * flag;
            }
            for (int k = 0; k <= K; k++) {
                for (int x = 0; x < 2; x++) {
                    // 今天不操作股票
                    for (int y = 0; y < 3; y++) {
                        f[cur][k][x][y] = Math.max(f[cur][k][x][y], f[pre][k][x][y]);
                    }
                    // 今天买入股票，结束之前的做空交易
                    if (k > 0) {
                        f[cur][k][x][1] = Math.max(f[cur][k][x][1], f[pre][k - 1][x][0] + nums[i]);
                    }
                    // 今天买入股票，开始普通交易
                    f[cur][k][x][0] = Math.max(f[cur][k][x][0], f[pre][k][x][2] + nums[i]);
                    // 今天卖出股票，结束之前的普通交易
                    if (k > 0) {
                        f[cur][k][x][2] = Math.max(f[cur][k][x][2], f[pre][k - 1][x][0] - nums[i]);
                    }
                    // 今天卖出股票，开始做空交易
                    f[cur][k][x][0] = Math.max(f[cur][k][x][0], f[pre][k][x][1] - nums[i]);
                }
            }
        }

        // 如果有初始交易，那么结尾也要对应
        // C++: ret = max_k f[n & 1 ^ 1][k][X][Y]
        int idx = ((n & 1) ^ 1);
        int X, Y;
        if (flag == 0) {
            X = 0;
            Y = 0;
        } else if (flag == 1) {
            X = 1;
            Y = 2;
        } else {
            X = 1;
            Y = 1;
        }
        long ret = -INF;
        for (int k = 0; k <= K; k++) {
            ret = Math.max(ret, f[idx][k][X][Y]);
        }
        return ret;
    }

}
