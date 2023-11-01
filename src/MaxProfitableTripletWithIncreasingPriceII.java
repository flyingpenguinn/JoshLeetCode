import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class MaxProfitableTripletWithIncreasingPriceII {

    // time = O(nlogn), space = O(n)
    final int N = 50010;
    int[] tr;
    List<Integer> nums;
    int m;

    public int maxProfit(int[] prices, int[] profits) {
        tr = new int[N];
        int n = prices.length;

        nums = new ArrayList<>();
        for (int x : prices) nums.add(x);
        nums = new ArrayList<>(new HashSet<>(nums));
        Collections.sort(nums);
        m = nums.size();

        int[] l = new int[n], r = new int[n];
        for (int i = 0; i < n; i++) {
            l[i] = sum(find(prices[i] - 1) + 1);
            add(find(prices[i]) + 1, profits[i]);
        }
        tr = new int[N];
        for (int i = n - 1; i >= 0; i--) {
            r[i] = sum2(find2(prices[i] + 1) + 1);
            add2(find2(prices[i]) + 1, profits[i]);
        }
        int res = -1;
        for (int i = 0; i < n; i++) {
            if (l[i] == 0 || r[i] == 0) continue;
            res = Math.max(res, l[i] + profits[i] + r[i]);
        }
        return res;
    }

    private int find(int x) {
        int l = 0, r = m - 1;
        while (l < r) {
            int mid = l + r + 1 >> 1;
            if (nums.get(mid) <= x) l = mid;
            else r = mid - 1;
        }
        return nums.get(r) <= x ? r : r - 1;
    }

    private int find2(int x) {
        int l = 0, r = m - 1;
        while (l < r) {
            int mid = l + r >> 1;
            if (nums.get(mid) >= x) r = mid;
            else l = mid + 1;
        }
        return nums.get(r) >= x ? r : r + 1;
    }

    private int lowbit(int x) {
        return x & -x;
    }

    private void add(int x, int c) {
        for (int i = x; i <= N; i += lowbit(i)) tr[i] = Math.max(tr[i], c);
    }

    private void add2(int x, int c) {
        for (int i = x; i > 0; i -= lowbit(i)) tr[i] = Math.max(tr[i], c);
    }

    private int sum(int x) {
        int res = 0;
        for (int i = x; i > 0; i -= lowbit(i)) res = Math.max(res, tr[i]);
        return res;
    }

    private int sum2(int x) {
        int res = 0;
        for (int i = x; i <= N; i += lowbit(i)) res = Math.max(res, tr[i]);
        return res;
    }

}
