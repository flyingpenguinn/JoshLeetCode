import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class JumpGameVi {
    // typical sliding window based dp...
    public int maxResult(int[] a, int k) {
        int n = a.length;
        int[] dp = new int[n];
        Arrays.fill(dp, -10000000);
        dp[n - 1] = a[n - 1];
        Deque<Integer> dq = new ArrayDeque<>();
        dq.offer(n - 1);
        // sliding window max
        for (int i = n - 2; i >= 0; i--) {
            int mj = Math.min(n - 1, i + k);
            while (!dq.isEmpty() && dq.peekFirst() > mj) {
                dq.pollFirst();
            }
            dp[i] = dp[dq.peekFirst()] + a[i];
            while (!dq.isEmpty() && dp[dq.peekLast()] <= dp[i]) {
                dq.pollLast();
            }
            dq.offer(i);
        }
        return dp[0];
    }
}
