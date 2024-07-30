import java.util.Arrays;

public class FlipStringToMonoIncrease {
    // can also dp but prefix sum is way more straightforward
    // can also use easier approach as "MinDeletionsToMakeStringBalanced"
    public int minFlipsMonoIncr(String s) {
        int n = s.length();
        int sum = 0;
        for(int i=0; i<n; i++){
            sum += s.charAt(i)-'0';
        }
        int res = n-sum; // all 1
        // all 0 up to i
        int p1 = 0;
        for(int i=0; i<n; i++){
            p1 += s.charAt(i)-'0';
            int p2 = sum - p1;
            int cur = p1+(n-1-i-p2);
            res = Math.min(cur, res);
        }
        return res;
    }
}

