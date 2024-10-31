import java.util.ArrayList;
import java.util.List;

public class FindOriginalTypedStringII {
    // TODO. rewrite myself
    private static final int MOD = (int) 1e9 + 7;

    public int possibleStringCount(String word, int k) {
        int n = word.length();
        List<Integer> lens = new ArrayList<>();
        int i = 0;

        // Find lengths of runs of identical characters
        while (i < n) {
            char c = word.charAt(i);
            int cnt = 1;
            i++;
            while (i < n && word.charAt(i) == c) {
                cnt++;
                i++;
            }
            lens.add(cnt);
        }

        int m = lens.size();

        // Calculate total number of possible intended strings without length constraint
        // For each run, the number of ways is equal to its length (number of choices)
        long total = 1;
        for (int len : lens) {
            total = (total * len) % MOD;
        }

        // If the number of runs is at least k, all combinations are valid
        if (m >= k) {
            return (int) total;
        }

        // Initialize dp array to store the number of ways to achieve a certain total length
        int maxLen = 0;
        for (int len : lens) {
            maxLen = Math.max(maxLen, len);
        }
        int dpSize = k + maxLen + 2; // Maximum possible total length

        int[] dp = new int[dpSize];
        dp[0] = 1; // Base case: one way to have length 0

        // Dynamic programming to count number of ways to have total length s
        for (int len : lens) {
            int[] ndp = new int[dpSize]; // New dp array for current run
            for (int s = 0; s <= k - 1; s++) {
                if (dp[s] == 0) continue;
                // For current run, we can select from 1 to len characters
                int start = s + 1;
                int end = s + Math.min(len, k - s - 1) + 1;
                // Update ndp using prefix sum technique
                ndp[start] = (ndp[start] + dp[s]) % MOD;
                if (end < ndp.length) {
                    ndp[end] = (ndp[end] - dp[s] + MOD) % MOD;
                }
            }
            // Compute prefix sums to get the actual dp values
            int sum = 0;
            for (int s = 0; s < ndp.length; s++) {
                sum = (sum + ndp[s]) % MOD;
                dp[s] = sum;
            }
        }

        // Calculate total number of ways to have length less than k
        long excluded = 0;
        for (int s = 1; s < k; s++) {
            excluded = (excluded + dp[s]) % MOD;
        }

        // The result is total ways minus the excluded ways
        long result = (total - excluded + MOD) % MOD;
        return (int) result;
    }
}

