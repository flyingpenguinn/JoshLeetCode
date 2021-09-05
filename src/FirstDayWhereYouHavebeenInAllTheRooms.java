public class FirstDayWhereYouHavebeenInAllTheRooms {
    // must go from i-1 to i. when we first reach i-1, we need to go back to a[i-1] then go back to i-1, this time going to i
    private int Mod = (int)(1e9+7);
    public int firstDayBeenInAllRooms(int[] a) {
        int n = a.length;
        long[] dp =new long[n];
        for(int i=1; i<n; ++i){
            dp[i] = (dp[i-1]+dp[i-1]-dp[a[i-1]] +2) %Mod;
            if(dp[i]<0){
                dp[i] += Mod;
            }
        }
        return (int)dp[n-1];
    }
}
