public class MinNumberOfSwapsToMakeStringAlternate {
    // sliding window:
    // type 1 means we do s+=s;
    // type 2 means we keep records of the mismatches for starting = 0 or 1. but we then use sliding window to eject the contribution head when it goes out of range
    public int minFlips(String s) {
        int n = s.length();
        s += s;
        int[] should = new int[]{0, 1};
        int[] res = new int[2];
        int rt= n+1;
        for(int i=0; i<2*n; i++){
            int cind= s.charAt(i) - '0';
            for(int j=0; j<2; j++){
                if(cind != should[j]){
                    res[j]++;
                }
            }
            if(i-n+1>=0){
                int flip = (n-1) %2;
                for(int j=0; j<2; j++){
                    rt = Math.min(rt, res[j]);
                    int cind2 = s.charAt(i-n+1) -'0';
                    if(cind2 != (should[j] ^ flip)){
                        res[j]--;
                    }
                }
            }
            for(int j=0; j<2; j++){
                should[j] ^= 1;
            }
        }
        return rt;
    }
}

class MinNumberOfSwapsDp{
    // got this in contest, though not the perfect, enlightening: we calc the changes if we start at i for 0,1. we also calc the changes if we start at 0 or 1 but end at i
    // then we try to "start" at each index
    public int minFlips(String s) {
        int n = s.length();
        int[][] rdp = new int[2][n+1];
        for(int i=n-1; i>=0; i--){
            if(s.charAt(i)=='1'){
                rdp[1][i] = rdp[0][i+1];
                rdp[0][i] = 1+rdp[1][i+1];
            }else{
                rdp[0][i] = rdp[1][i+1];
                rdp[1][i] = 1+rdp[0][i+1];
            }
        }
        int[][] dp = new int[2][n];
        for(int should = 0; should<=1; should++){
            int t = should;
            for(int i=0; i<n; i++){
                int cind = s.charAt(i)-'0';
                if(cind != t){
                    dp[should][i] = (i==0? 1: dp[should][i-1]+1);
                }else{
                    dp[should][i] = (i==0? 0: dp[should][i-1]);
                }
                t ^= 1;
            }
        }
        int res = n+1;
        for(int i=0; i<n; i++){
            int rem = n-i;
            int cur = 0;
            if(rem % 2==0){
                int way1 = rdp[0][i] + (i==0?0:dp[0][i-1]);
                int way2 = rdp[1][i] + (i==0?0:dp[1][i-1]);
                cur = Math.min(way1, way2);
            }else{
                int way1 = rdp[0][i] + (i==0?0:dp[1][i-1]);
                int way2 = rdp[1][i] + (i==0?0:dp[0][i-1]);
                cur = Math.min(way1, way2);
            }
            res = Math.min(res, cur);
        }
        return res;
    }
}
