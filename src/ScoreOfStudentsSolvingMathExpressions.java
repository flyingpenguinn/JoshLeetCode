import java.util.HashSet;
import java.util.Set;

public class ScoreOfStudentsSolvingMathExpressions {
    // just a change on "different ways to add parenthesis"
    private Set<Integer>[][] dp;
    public int scoreOfStudents(String s, int[] answers) {
        int n = s.length();
        dp = new HashSet[n][n];

        int correct = getCorrect(s);
        solve(s, 0, n-1);
        Set<Integer> all = dp[0][n-1];
        int res = 0;
        for(int ai: answers){
            if(ai==correct){
                res +=5;
            }else if(all.contains(ai)){
                res += 2;
            }
        }
        return res;
    }

    private int getCorrect(String s){
        int n = s.length();
        int i = 0;
        int res = 0;
        int cblock = 0;
        boolean inplus = true;
        while(i<n){
            int lastnum = -1;
            if(Character.isDigit(s.charAt(i))){
                int j = i;
                while(j<n && Character.isDigit(s.charAt(j))){
                    ++j;
                }
                int curnum = Integer.valueOf(s.substring(i, j));
                if(inplus){
                    cblock += curnum;
                }else{
                    cblock *= curnum;
                }
                i=j;
            }else if(s.charAt(i)=='+'){
                res += cblock;
                cblock = 0;
                inplus = true;
                ++i;
            }else{
                inplus = false;
                ++i;
            }
        }
        res += cblock;
        return res;
    }

    private void solve(String s, int i, int j){
        if(dp[i][j] != null){
            return;
        }
        Set<Integer> cur = new HashSet<>();
        dp[i][j] = cur;
        if(i==j){
            int cr = s.charAt(i)-'0';
            cur.add(cr);
            return;
        }
        for(int k=i+1; k<j; ++k){
            if(s.charAt(k)=='+' || s.charAt(k)=='*'){
                boolean isplus = s.charAt(k)=='+';
                solve(s, i, k-1);
                solve(s, k+1, j);
                for(int li: dp[i][k-1]){
                    for(int ri: dp[k+1][j]){
                        int cr = isplus? li+ri: li*ri;
                        if(cr<=1000){
                            cur.add(cr);
                        }
                    }
                }
            }
        }
    }
}
