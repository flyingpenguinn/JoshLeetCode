import java.util.*;

public class CountNumberOfKFreeSubsets {
    // how to solve the problem of not picking consecutive numbers? fibo !
    public long countTheNumOfKFreeSubsets(int[] a, int k) {
        int n = a.length;
        Arrays.sort(a);
        Map<Integer, List<Integer>> m = new HashMap<>();
        for (int ai : a) {
            boolean found = false;
            for (int mk : m.keySet()) {
                List<Integer> nums = m.get(mk);
                if (ai - nums.get(nums.size() - 1) == k) {
                    found = true;
                    nums.add(ai);
                    break;
                }

            }
            if (!found) {
                m.put(ai, new ArrayList<>());
                m.get(ai).add(ai);
            }
        }
        //  System.out.println(m);
        long res = 1;
        for (int mk : m.keySet()) {
            res *= solve(m.get(mk));
        }
        return res;
    }

    private long solve(List<Integer> a) {
        //    System.out.println(a);
        int n = a.size();
        if (n == 0) {
            return 1;
        }
        long[] dp = new long[n + 1];
        dp[n] = 1;
        dp[n - 1] = 2;
        for (int i = n - 2; i >= 0; --i) {
            dp[i] = dp[i + 1] + dp[i + 2];
        }
        return dp[0];
    }
}
