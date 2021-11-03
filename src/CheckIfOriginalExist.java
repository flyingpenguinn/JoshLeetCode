import java.util.HashMap;
import java.util.Map;

public class CheckIfOriginalExist {
    private Boolean[][][] dp;
    public boolean possiblyEquals(String s1, String s2) {
        int s1n = s1.length();
        int s2n = s2.length();
        dp = new Boolean[s1n+1][s2n+1][2000];
        return solve(s1.toCharArray(), s2.toCharArray(), 0, 0, 0);
    }

    private boolean isDigit(char c){
        return Character.isDigit(c);
    }

    private boolean solve(char[] s1, char[] s2, int i, int j, int k){
    //    System.out.println(k);
        boolean res = false;
        int s1n = s1.length;
        int s2n = s2.length;
        if(i==s1n && j==s2n){
            return k==0;
        }
        if(dp[i][j][k+1000] != null){
            return dp[i][j][k+1000];
        }
        if(i<s1n && isDigit(s1[i])){
            int p = i;
            int num = 0;
            while(p<s1n && isDigit(s1[p])){
                num = num*10+ (s1[p]-'0');
                ++p;
                boolean cur = solve(s1, s2, p, j, k+num);
                if(cur){
                    res= true;
                    break;
                }
            }
        }else if(j<s2n && isDigit(s2[j])){
            int p = j;
            int num = 0;
            while(p<s2n && isDigit(s2[p])){
                num = num*10+ (s2[p]-'0');
                ++p;
                boolean cur = solve(s1, s2, i, p, k-num);
                if(cur){
                    res = true;
                    break;
                }
            }
        }else{
            if(k==0 && i<s1n && j<s2n){
                res = (s1[i]!=s2[j])? false: solve(s1, s2, i+1, j+1, k);
            }
            else if(k>0 && j<s2n){
                res = solve(s1, s2, i, j+1, k-1);
            }else if(k<0 && i<s1n){
                res = solve(s1, s2, i+1, j, k+1);
            }else{
                res = false;
            }
        }
        dp[i][j][k+1000] = res;
        return res;
    }
    public static void main(String[] args) {
        System.out.println(new CheckIfOriginalExist().possiblyEquals("889v899u999v889u989v989u889u888v889u889u", "998v898v999v998v898v998v889v889u888v898u"));
    }
}
