import java.util.HashMap;
import java.util.Map;

public class NumberOfDivisibleSubstrings {
    // simialr to leetcode 644
    private int getdigit(int cind){
        if(cind<=1){
            return 1;
        }
        int d = (int) Math.ceil(1.0*(cind-1)/3)+1;
        return (int)d;
    }

    public int countDivisibleSubstrings(String s) {
        int n = s.length();
        int res = 0;
        for(int avg = 1; avg<=9; ++avg){
            Map<Integer,Integer> m = new HashMap<>();
            m.put(0, 1);
            int sum = 0;
            for(int j=0; j<n; ++j){
                int cind = s.charAt(j)-'a';
                int d = getdigit(cind);
                sum += d-avg;
                int cv = m.getOrDefault(sum, 0);
                res += cv;
                m.put(sum, cv+1);
            }
        }
        return res;
    }
}
