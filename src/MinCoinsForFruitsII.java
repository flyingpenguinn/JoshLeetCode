import base.ArrayUtils;

import java.util.ArrayDeque;
import java.util.Deque;

public class MinCoinsForFruitsII {
    public int minimumCoins(int[] a) {
        // dp[i]: min cost to cover 0 to i, and we MUST buy i
        // dp[i] = min(dp[j]) + a[j]
        // where j+j+1>=i-1
        int n = a.length;
        int[] dp = new int[n];
        Deque<Integer> q = new ArrayDeque<>();
        int res = (int)1e9;
        for(int i=0; i<n; ++i){
            while(!q.isEmpty() && q.getFirst()*2+1<i-1){
                q.pollFirst();
            }
            dp[i] = q.isEmpty()? a[i]: (dp[q.getFirst()] + a[i]);
            if(i+i+1>=n-1){
                res = Math.min(res, dp[i]);
            }
            while(!q.isEmpty() && dp[q.getLast()] >= dp[i]){
                q.pollLast();
            }
            q.offerLast(i);
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new MinCoinsForFruitsII().minimumCoins(ArrayUtils.read1d("[3,1,2]")));
    }
}
