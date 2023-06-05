public class CountOfIntegers {
    // typical digital dp
    private long Mod = (long)(1e9+7);
    private Long[][][] dp;
    public int count(String s1, String s2, int m1, int m2) {
        dp = new Long[23][500][2];
        long v1 =  solve(0, s2, 0, 0, m1, m2) ;
        dp = new Long[23][500][2];
        long v2 = solve(0, s1, 0, 0, m1, m2);
        int sums1 = 0;
        for(int i=0; i<s1.length(); ++i){
            sums1 += (s1.charAt(i)- '0');
        }
        long delta = (v1-v2) % Mod;
        if(delta<0){
            delta += Mod;
        }
        if(sums1>=m1 && sums1<=m2){
            ++delta;
            delta %= Mod;
        }
        return (int) delta;
    }

    private long solve(int i, String s, int sum, int canPickAny, int m1, int m2) {
        int n = s.length();

        if(i==n){
            return sum>=m1 && sum<=m2? 1: 0;
        }
        if(dp[i][sum][canPickAny] != null){
            return dp[i][sum][canPickAny];
        }
        long res = 0;
        for(int d= 0; d<=9; ++d){
            int sd = s.charAt(i)-'0';
            if(d>sd && canPickAny==0){
                break;
            }
            int nsum = sum+d;
            if(nsum>m2){
                break;
            }
            int newCanPick = canPickAny;
            if(d<sd){
                newCanPick = 1;
            }
            long cur = solve(i+1, s, nsum, newCanPick, m1, m2);
            res += cur;
            res %= Mod;
        }
        dp[i][sum][canPickAny] = res;
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new CountOfIntegers().count("1", "12", 1, 8));
    }
}
