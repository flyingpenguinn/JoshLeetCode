import base.ArrayUtils;

import java.util.*;

public class NumberOfBeautifulSubsets {
    public int beautifulSubsets(int[] a, int ik) {
        int n = a.length;
        this.dist = ik;
        Map<Integer, List<Integer>> m = new HashMap<>();
        for (int i = 0; i < n; ++i) {
            m.computeIfAbsent(a[i] % dist, k -> new ArrayList<>()).add(a[i]);
        }
        int res = 1;
        for (int k : m.keySet()) {
            List<Integer> list = m.get(k);
            Collections.sort(list);
            dp = new int[list.size()];
            Arrays.fill(dp, -1);
            int ways = solve(list, 0);
            res *= ways;
        }
        return res - 1;
    }

    private int dist = 0;
    private int[] dp;

    // like house robber, but in groups sense
    // for each number check the same values and pick a subset from them
    private int solve(List<Integer> list, int i) {
        int n = list.size();
        if (i == n) {
            return 1;
        }
        if (dp[i] != -1) {
            return dp[i];
        }

        int k = i;
        while (k < n && list.get(k) == list.get(i)) {
            ++k;
        }
        int way1 = solve(list, k);
        int len = k - i;
        int j = k;
        while (j < n && list.get(j) == list.get(i) + dist) {
            ++j;
        }
        int way2 = ((1 << len) - 1) * solve(list, j);
        System.out.println(i + " " + way1 + " " + way2);
        int res = way1 + way2;
        dp[i] = res;
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new NumberOfBeautifulSubsets().beautifulSubsets(ArrayUtils.read1d("1,1,2"), 1));
    }
}
