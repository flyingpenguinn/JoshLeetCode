import java.util.ArrayDeque;
import java.util.Deque;

public class JumpGameVii {
    // dp[i] = 1 means it can reach the end
    // for each i, find the biggest j so that s[j]==0 and j is within i+minJump, i+maxJump, and the interval needs to be <n
    public boolean canReach(String s, int minJump, int maxJump) {

        int n = s.length();
        if (s.charAt(n - 1) == '1') {
            return false;
        }
        int[] dp = new int[n];
        Deque<Integer> dq = new ArrayDeque<>();
        for (int i = n - 1; i >= 0; i--) {
            while (!dq.isEmpty() && dq.peekLast() > Math.min(i + maxJump, n - 1)) {
                dq.pollLast();
            }
            if (i == n - 1) {
                dp[i] = 1;
            } else {
                dp[i] = dq.isEmpty() ? 0 : dp[dq.peekLast()];
            }
            int toadd = i - 1 + minJump;
            // add toadd for i-1. if it's not good enough then we dont add it
            if (toadd >= n || s.charAt(toadd)=='1') {
                continue;
            }
            // we want the max dp[j], so decreasing from n-1 to i
            while (!dq.isEmpty() && dp[toadd] >= dp[dq.peekFirst()] ) {
                dq.pollFirst();
            }
            dq.offerFirst(toadd);
        }
        return dp[0] == 1;
    }
}

class JumpGameViiSimpler{
    // we just need one 1: we can just use a counter
    public boolean canReach(String s, int minJump, int maxJump) {
        int n = s.length();
        if (s.charAt(n - 1) == '1') {
            return false;
        }
        // dp[i]: can go through i to get to the end. always false for 1 positions
        boolean[] dp = new boolean[n];
        int counts = 0; // counts of dp[i] == true in the window
        for (int i = n - 1; i >= 0; i--) {
            if(i+maxJump+1<n && dp[i+maxJump+1]){
                counts--;
            }
            if(i==n-1){
                dp[i] = true;
            }
            else if(s.charAt(i)=='0' && counts>0){
                dp[i] = true;
            }
            if(i-1+minJump <n && dp[i-1+minJump]){
                counts++;
            }
        }
        return dp[0];
    }
}