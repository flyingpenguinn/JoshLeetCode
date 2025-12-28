public class NumberOfBalancedIntegersInRange {
    // we do not need to store two numbers, just their delta!
    private Long[][][][][]dp;
    public long countBalanced(long low, long high) {
        return solve(high) - solve(low - 1);
    }

    private long solve(long limit) {
        char[] lc = String.valueOf(limit).toCharArray();
        int llen = lc.length;
        int[] lnum = new int[llen];
        for (int i = 0; i < llen; ++i) {
            lnum[i] = lc[i] - '0';
        }
        dp = new Long[lnum.length][2][2][2][500];
        return dosolve(lnum, 0, 0, 0, 0, 0);
    }

    private long dosolve(int[] lnum, int i, int alreadysmall, int started, int isodd, int delta) {
        int n = lnum.length;
        if (i >= n - 1 && started == 0) {
            return 0;
        }

        if (i == n) {
            if (delta==0) {
                return 1;
            } else {
                return 0;
            }
        }
        int deltawithbase = delta + 200;
        if(dp[i][alreadysmall][started][isodd][deltawithbase]!= null){
            return dp[i][alreadysmall][started][isodd][deltawithbase];
        }
        int ld = lnum[i];

        long res = 0;
        for (int digit = 0; digit <= 9; ++digit) {

            if(digit>ld && alreadysmall == 0){
                break;
            }
            int newalreadysmall = alreadysmall;
            int newstarted = started;
            int newisodd = 0;
            int newdelta = delta;
            if (digit < ld) {
                newalreadysmall = 1;
            }
            if (digit > 0) {
                newstarted = 1;
            }
            if (started == 0 && newstarted == 1) {
                newisodd = 1;
            } else if(started==1){
                newisodd = isodd ^ 1;
            }
            if(isodd==1){
                newdelta += digit;

            }else{
                newdelta -= digit;

            }
            long later = dosolve(lnum, i+1, newalreadysmall, newstarted, newisodd, newdelta);
            res += later;
        }
        dp[i][alreadysmall][started][isodd][deltawithbase] = res;
        return res;
    }


    private long countBrute(long low, long high){
        long res = 0;
        for(long i=low; i<=high; ++i){
            String si = String.valueOf(i);
            if(si.length()<2){
                continue;
            }
            long evensum = 0;
            long oddsum = 0;
            for(int j=0; j<si.length(); ++j){
                if(j%2==0) {
                    evensum += si.charAt(j)-'0';
                }else{
                    oddsum += si.charAt(j)-'0';
                }
            }
            if(evensum == oddsum){
                ++res;
            }
        }
        return res;
    }
}
