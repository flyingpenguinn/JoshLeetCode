import base.ArrayUtils;

public class NumberOfGoodSubsets {
    // note we can only pick distinct numbers, so dp on distinct numbers, not the array itself!
    private int[] primes = {2,3,5,7,11,13,17,19,23,29};
    private int[] map = new int[31];
    private int[] counter = new int[31];
    private Long[][] dp = new Long[31][1<<10];
    private int Mod = (int)(1e9+7);
    public int numberOfGoodSubsets(int[] a) {
        int n = a.length;
        for(int i=1; i<=30; ++i){
            int mask = 0;
            int cnum = i;
            for(int j=0; j<primes.length; ++j){
                if(cnum%primes[j]==0){
                    cnum /= primes[j];
                    mask |= (1<<j);
                }
                if(cnum%primes[j]==0){
                    mask = -1;
                    break;
                }
            }
            map[i]= mask;
        }
        for(int i=0; i<n; ++i){
            ++counter[a[i]];
        }
        long rt = solve(counter, 2, 0);
        rt *= pow2(counter[1]); // any of these ones will do
        rt %= Mod;
        return (int) rt;

    }

    private long pow2(int n) {
        if (n == 0) {
            return 1;
        }
        long half = pow2(n / 2);
        long rt = half * half;
        rt %= Mod;
        if (n % 2 == 1) {
            rt *= 2;
            rt %= Mod;
        }
        return rt;
    }

    private long solve(int[] a, int i, int j){
        int n = a.length;
        if(i==n){
            return j==0?0:1;
        }
        if(dp[i][j]!= null){
            return dp[i][j];
        }
        long nopick = solve(a, i+1, j);
        long pick = 0;
        int cmask = map[i];
        if( cmask != -1 && (cmask & j) ==0){
            int nmask = cmask | j;
            pick = (a[i])*solve(a, i+1, nmask);
        }
        long rt = pick+nopick;
        rt %= Mod;
        dp[i][j] = rt;
        return rt;
    }
}
