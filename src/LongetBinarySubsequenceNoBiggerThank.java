public class LongetBinarySubsequenceNoBiggerThank {
    // TODO greedy may work as well
    private Integer[][][][] dp;
    public int longestSubsequence(String s, int k) {
        String binary = Integer.toBinaryString(k);
        dp = new Integer[s.length()][binary.length()][2][2];
        return solve(s, 0, binary, 0, 0, 0);
    }

    // l: do we have the freedom to be 1 or 0
    // k: 1 appeared or not
    private int solve(String s, int i, String t, int j, int k, int l) {
        if(i==s.length()){
            return 0;
        }
        if(j==t.length()){
            return 0;
        }
        if(s.length()-i < t.length()-j){
            return s.length()-i;
        }
        if(dp[i][j][k][l] != null){
            return dp[i][j][k][l];
        }
        int res = solve(s, i+1, t, j, k, l);
        if(l==1){
            res = Math.max(1+solve(s, i+1, t, j+1, k, l), res);
        }else{
            char sc = s.charAt(i);
            if(sc=='0'){
                if(k==0){
                    res = Math.max(1+solve(s, i+1, t, j, k, l), res);
                }else{
                    char tc = t.charAt(j);
                    if(tc=='1'){
                        res = Math.max(1+solve(s, i+1, t, j+1, k, 1), res);
                    }else{
                        res = Math.max(1+solve(s, i+1, t, j+1, k, 0), res);
                    }
                }
            }else{

                if(k==0){
                    res= Math.max(1+solve(s, i+1, t, j+1, 1, 0), res);
                }else{
                    char tc = t.charAt(j);
                    if(tc=='1'){
                        res = Math.max(1+solve(s, i+1, t, j+1, k, l), res);
                    }
                }
            }
        }
        dp[i][j][k][l] = res;
        return res;
    }


    public static void main(String[] args) {
        System.out.println(new LongetBinarySubsequenceNoBiggerThank().longestSubsequence("1011", 281854076));
    }
}
