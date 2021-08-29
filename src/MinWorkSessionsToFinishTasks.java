public class MinWorkSessionsToFinishTasks {
    private Integer[] dp;
    private boolean[] good;
    public int minSessions(int[] tasks, int t) {
        int n = tasks.length;
        dp = new Integer[1<<n];
        good = new boolean[1<<n];
        for(int i=1; i<(1<<n); ++i){
            if(sum(i, tasks)<=t){
                good[i] = true;
            }
        }
        return solve(((1<<n)-1), tasks, t);
    }

    private int solve(int avail, int[] tasks, int t){
        if(avail==0){
            return 0;
        }
        if(good[avail]){
            return 1;
        }
        if(dp[avail] != null){
            return dp[avail];
        }
        int res = Integer.MAX_VALUE;
        for(int j=avail; j>0; j=(j-1)&avail){
            if(good[j]){
                int cur = 1+solve(avail-j, tasks, t);
                res = Math.min(cur, res);
            }
        }
        dp[avail] = res;
        return res;
    }

    private int sum(int st, int[] tasks){
        int n = tasks.length;
        int res = 0;
        for(int i=0; i<n; ++i){
            if(((st>>i)&1)==1){
                res += tasks[i];
            }
        }
        return res;
    }
}
