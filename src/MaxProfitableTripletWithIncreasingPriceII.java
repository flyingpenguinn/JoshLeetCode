import base.ArrayUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class MaxProfitableTripletWithIncreasingPriceII {
    // fenwick tree used to get max number instead of sum
    private final int N = 50010;
    private int[] bit;
    private List<Integer> a;

    public int maxProfit(int[] prices, int[] profits) {
        bit = new int[N];
        int n = prices.length;
        for (int i = 0; i < n; ++i) {
            prices[i] += 1;
        }

        a = new ArrayList<>();
        for (int x : prices) a.add(x);
        a = new ArrayList<>(new HashSet<>(a));
        Collections.sort(a);

        int[] l = new int[n];
        int[] r = new int[n];
        for (int i = 0; i < n; i++) {
            l[i] = sum(prices[i] - 1);
            add(prices[i], profits[i]);
        }
        bit = new int[N];
        for (int i = n - 1; i >= 0; i--) {
            r[i] = sum2(prices[i] + 1);
            add2(prices[i], profits[i]);
        }
        int res = -1;
        for (int i = 0; i < n; i++) {
            if (l[i] == 0 || r[i] == 0) {
                continue;
            }
            res = Math.max(res, l[i] + profits[i] + r[i]);
        }
        return res;
    }

    private int lowbit(int x) {
        return x & -x;
    }

    private void add(int x, int c) {
        for (int i = x; i <= N; i += lowbit(i)) {
            bit[i] = Math.max(bit[i], c);
        }
    }

    private void add2(int x, int c) {
        for (int i = x; i > 0; i -= lowbit(i)) {
            bit[i] = Math.max(bit[i], c);
        }
    }

    private int sum(int x) {
        int res = 0;
        for (int i = x; i > 0; i -= lowbit(i)) {
            res = Math.max(res, bit[i]);
        }
        return res;
    }

    private int sum2(int x) {
        int res = 0;
        for (int i = x; i <= N; i += lowbit(i)) {
            res = Math.max(res, bit[i]);
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new MaxProfitableTripletWithIncreasingPriceII().maxProfit(ArrayUtils.read1d("1,1,7"), ArrayUtils.read1d("84,46,6")));
    }
}
