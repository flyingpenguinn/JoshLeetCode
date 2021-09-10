public class SmallestGreaterMultipleMadeOfTwoDigits {
    private long MAX = Integer.MAX_VALUE;
    private long res = MAX+1;
    public int findInteger(int k, int d1, int d2) {
        dfs(0, k, d1, d2, 0L);
        return res>MAX? -1: (int)res;
    }

    private void dfs(int i, int k, int d1, int d2, long cur){
        //  System.out.println(cur);
        if(cur>MAX){
            return;
        }
        if(i>10){
            return;
        }
        if(cur>k && cur%k==0){
            res = Math.min(res, cur);
        }
        dfs(i+1, k, d1, d2, cur*10+d1);
        dfs(i+1, k, d1, d2, cur*10+d2);
    }
}
