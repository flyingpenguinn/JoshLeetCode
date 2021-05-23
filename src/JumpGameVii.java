import java.util.ArrayDeque;
import java.util.Deque;

public class JumpGameVii {
    public boolean canReach(String s, int minJump, int maxJump) {
        Deque<Integer> dq = new ArrayDeque<>();
        int n = s.length();
        if (s.charAt(n - 1) == '1') {
            return false;
        }
        int[] dp = new int[n];

        for (int i = n - 1; i >= 0; i--) {
            while (!dq.isEmpty() && dq.peekLast() > Math.min(i + maxJump, n - 1)) {
                dq.pollLast();
            }
            if (i == n - 1) {
                dp[i] = 1;
            } else {
                dp[i] = dq.isEmpty() ? 0 : dp[dq.peekLast()];
            }
            if (i - 1 + minJump >= n) {
                continue;
            }
            int toadd = i - 1 + minJump;
            if (s.charAt(toadd) == '1') {
                continue;
            }
            while (!dq.isEmpty() && dp[dq.peekFirst()] <= dp[toadd]) {
                dq.pollFirst();
            }
            dq.offerFirst(toadd);
        }
        return dp[0] == 1;
    }
}
