import java.util.Arrays;

public class DeleteAndEarn {
    int[] dp;

    // sort and then house robber...
    public int deleteAndEarn(int[] nums) {
        dp = new int[nums.length];
        Arrays.sort(nums);
        return best(nums, 0);
    }

    int best(int[] a, int i) {
        int n = a.length;
        if (i == n) {
            return 0;
        }
        if (dp[i] != 0) {
            return dp[i];
        }
        int j = i;
        while (j < n && a[j] == a[i]) {
            j++;
        }
        int c = j - i;
        //System.out.println(c);
        int without = best(a, j);
        while (j < n && a[j] == a[i] + 1) {
            j++;
        }
        int with = c * a[i] + best(a, j);
        dp[i] = Math.max(with, without);
        return dp[i];
    }
}
